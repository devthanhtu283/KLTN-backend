from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from py_eureka_client import eureka_client
import asyncio
from fastapi.middleware.cors import CORSMiddleware
from openai import OpenAI

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