from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from py_eureka_client import eureka_client
import asyncio
from fastapi.middleware.cors import CORSMiddleware
from openai import OpenAI
from fastapi import File, UploadFile, Form
from fastapi.responses import JSONResponse
import pdfplumber  # Hỗ trợ đọc PDF dạng scan
import fitz  # PyMuPDF - Thư viện đọc PDF tốt nhất
from pdfminer.high_level import extract_text
import re
from typing import Dict, List

app = FastAPI()

# Cấu hình CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:4200"],  # Cho phép Angular kết nối
    allow_credentials=True,
    allow_methods=["*"],  # Cho phép tất cả phương thức (GET, POST, v.v.)
    allow_headers=["*"],  # Cho phép tất cả headers
)

# Khởi tạo client OpenAI
client = OpenAI(
    base_url="http://localhost:11434/v1",  # URL của mô hình AI
    api_key="ollma"  # API key (nếu cần)
)

# Lưu trữ lịch sử trò chuyện
conversation_history = {}

# Hàm khởi tạo Eureka Client
async def init_eureka():
    await eureka_client.init_async(
        eureka_server="http://localhost:9999",  # Địa chỉ Eureka Server
        app_name="recommendation-service",      # Tên ứng dụng của bạn
        instance_port=8000,                     # Port mà API của bạn chạy
        instance_host="localhost"               # Host mà API của bạn chạy
    )

# Khởi tạo Eureka Client khi ứng dụng khởi động
@app.on_event("startup")
async def startup_event():
    asyncio.create_task(init_eureka())

# Định nghĩa model cho tin nhắn
class ChatMessage(BaseModel):
    message: str
    user_id: str  # Thêm trường user_id để phân biệt người dùng

# Hàm xử lý tin nhắn từ chatbot
def process_message(user_message: str, user_id: str) -> str:
    try:
        # Kiểm tra xem user_id đã có trong lịch sử trò chuyện chưa
        if user_id not in conversation_history:
            # Khởi tạo lịch sử trò chuyện với ngữ cảnh ban đầu
            conversation_history[user_id] = [
                {
                    "role": "system",
                    "content": user_message
                }
            ]

        # Thêm tin nhắn của người dùng vào lịch sử trò chuyện
        conversation_history[user_id].append({"role": "user", "content": user_message})

        # Gửi toàn bộ lịch sử trò chuyện đến mô hình AI
        response = client.chat.completions.create(
            model="gemma2:9b",  # Tên mô hình
            messages=conversation_history[user_id]
        )

        # Lấy phản hồi từ mô hình AI
        bot_reply = response.choices[0].message.content

        # Thêm phản hồi của bot vào lịch sử trò chuyện
        conversation_history[user_id].append({"role": "assistant", "content": bot_reply})

        return bot_reply
    except Exception as e:
        # Log lỗi và trả về thông báo lỗi
        print(f"Lỗi khi xử lý tin nhắn: {e}")
        return "Xin lỗi, có lỗi xảy ra. Vui lòng thử lại sau."

# Định nghĩa endpoint POST cho chatbot
@app.post("/python/chat")
def chat(chat_message: ChatMessage):
    user_message = chat_message.message
    user_id = chat_message.user_id

    if not user_message:
        raise HTTPException(status_code=400, detail="Tin nhắn không được để trống")

    try:
        # Xử lý tin nhắn và trả về phản hồi từ bot
        bot_response = process_message(user_message, user_id)
        return {"message": bot_response}
    except Exception as e:
        # Log lỗi và trả về thông báo lỗi
        print(f"Lỗi trong endpoint /python/chat: {e}")
        raise HTTPException(status_code=500, detail="Lỗi máy chủ nội bộ")

# Các endpoint khác
@app.get("/python/hello")
def read_hello():
    return {"message": "Xin chào, đây là API của tôi!"}

@app.get("/python/bye")
def read_bye():
    return {"message": "Bye Bye!!"}

# 🔹 Trích xuất nội dung từ PDF chính xác
def extract_text_from_pdf(file_path: str) -> str:
    text = ""

    try:
        # Dùng pdfplumber trước
        with pdfplumber.open(file_path) as pdf:
            for page in pdf.pages:
                page_text = page.extract_text()
                if page_text:
                    text += page_text + "\n"

        # Nếu pdfplumber không lấy được, thử PyMuPDF (fitz)
        if not text.strip():
            doc = fitz.open(file_path)
            for page in doc:
                text += page.get_text("text") + "\n"

    except Exception as e:
        print(f"Lỗi khi đọc PDF: {e}")
        raise HTTPException(status_code=500, detail="Lỗi khi đọc nội dung PDF")

    return text.strip() if text.strip() else "Không tìm thấy nội dung trong PDF"

# 🔹 Tách từng phần cụ thể (Skills, Education, Projects, Certificates)
def extract_info(cv_text: str) -> Dict:
    extracted_info = {}

    extracted_info["education"] = extract_section(cv_text, ["education", "học vấn"])
    extracted_info["skills"] = extract_section(cv_text, ["skills", "skill"])
    extracted_info["certificate"] = extract_section(cv_text, ["certificate", "certification", "certifications"])
    extracted_info["projects"] = extract_section(cv_text, ["project", "projects"])

    return extracted_info

# 🔹 Hàm trích xuất nội dung theo từng mục trong CV
def extract_section(text: str, start_keywords: List[str]) -> str:
    lines = text.split("\n")
    extracted_section = []
    capture = False

    for line in lines:
        if any(keyword.lower() in line.lower() for keyword in start_keywords):
            capture = True
            continue
        if capture and line.strip() == "":
            break
        if capture:
            extracted_section.append(line.strip())

    return "\n".join(extracted_section) if extracted_section else "Không tìm thấy"

# 🔹 API Upload CV - Đọc PDF và trích xuất thông tin
@app.post("/upload-cv")
async def upload_cv(file: UploadFile = File(...)):
    """
    Endpoint để upload CV dạng file PDF, đọc nội dung và tách thông tin quan trọng.
    """
    try:
        filename = file.filename.lower()

        # Kiểm tra định dạng file
        if not filename.endswith(".pdf"):
            raise HTTPException(status_code=400, detail="Chỉ hỗ trợ file PDF")

        # Lưu file tạm
        file_path = f"./temp_{filename}"
        with open(file_path, "wb") as f:
            f.write(await file.read())

        # Đọc nội dung PDF
        extracted_text = extract_text_from_pdf(file_path)

        # Phân tích nội dung và tách thông tin quan trọng
        extracted_data = extract_info(extracted_text)

        return JSONResponse(
            content={
                "filename": filename,
                "message": "Upload và trích xuất PDF thành công!",
                "extracted_data": extracted_data,
            },
            status_code=200
        )

    except Exception as e:
        print(f"Lỗi khi upload file: {e}")
        raise HTTPException(status_code=500, detail="Lỗi máy chủ khi xử lý tệp tin")