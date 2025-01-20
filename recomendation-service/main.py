from fastapi import FastAPI
from py_eureka_client import eureka_client
import asyncio

app = FastAPI()

# Hàm khởi tạo Eureka Client
async def init_eureka():
    await eureka_client.init_async(
        eureka_server="http://localhost:9999",  # Địa chỉ Eureka Server
        app_name="recommendation-service",              # Tên ứng dụng của bạn
        instance_port=8000,                     # Port mà API của bạn chạy
        instance_host="localhost"               # Host mà API của bạn chạy
    )

# Khởi tạo Eureka Client khi ứng dụng khởi động
@app.on_event("startup")
async def startup_event():
    asyncio.create_task(init_eureka())

# Định nghĩa một endpoint GET tại "/python/hello"
@app.get("/python/hello")
def read_hello():
    return {"message": "Xin chào, đây là API của tôi!"}

# Định nghĩa một endpoint GET tại "/python/bye"
@app.get("/python/bye")
def read_bye():
    return {"message": "Bye Bye!!"}