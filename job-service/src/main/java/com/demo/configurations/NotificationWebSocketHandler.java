package com.demo.configurations;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationWebSocketHandler extends TextWebSocketHandler {

    // Lưu trữ các session theo userId
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Lấy userId từ query parameter (ví dụ: ws://localhost:8080/notifications-websocket?userId=1)
        String userId = session.getUri().getQuery().split("=")[1];
        sessions.put(userId, session);
        System.out.println("WebSocket connection established for userId: " + userId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = session.getUri().getQuery().split("=")[1];
        sessions.remove(userId);
        System.out.println("WebSocket connection closed for userId: " + userId);
    }

    // Gửi thông báo đến userId cụ thể
    public void sendNotification(String userId, String message) throws Exception {
        WebSocketSession session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }
}