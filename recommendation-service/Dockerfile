# 1. Image nhẹ dùng Python 3.10
FROM python:3.10-slim

# 2. Tạo thư mục app
WORKDIR /app

# 3. Copy toàn bộ source vào container
COPY . .

# 4. Cài thư viện từ requirements.txt
RUN pip install --upgrade pip
RUN pip install --no-cache-dir -r requirements.txt
RUN python -m spacy download en_core_web_sm


# 5. Chạy file main.py
CMD ["python", "main.py"]
