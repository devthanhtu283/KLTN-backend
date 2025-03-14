package com.demo.services;

public interface RedisService {

    // Lưu dữ liệu vào cache với TTL (time to live)
    public void saveToCache(String key, Object value, long timeoutMinutes);

    // Lấy dữ liệu từ cache
    public Object getFromCache(String key);

    // Xóa một key trong Redis (dùng khi cần xóa thủ công)
    public void deleteKey(String key);
}
