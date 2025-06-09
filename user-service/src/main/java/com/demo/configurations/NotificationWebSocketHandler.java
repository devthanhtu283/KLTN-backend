package com.demo.configurations;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.*;

@Component
public class NotificationWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    // Dùng ScheduledExecutorService để retry gửi socket
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static final int MAX_RETRIES = 5;
    private static final long RETRY_INTERVAL_MS = 1000;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String query = session.getUri().getQuery();
        if (query != null && query.startsWith("userId=")) {
            String userId = query.split("=")[1];
            sessions.put(userId, session);
            System.out.println("✅ WebSocket connected for userId: " + userId);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String query = session.getUri().getQuery();
        if (query != null && query.startsWith("userId=")) {
            String userId = query.split("=")[1];
            sessions.remove(userId);
            System.out.println("🔌 WebSocket disconnected for userId: " + userId);
        }
        System.out.println("💬 Chat socket connected for: " + session.getUri());
    }

    public void sendNotification(String userId, String message) {
        sendNotificationWithRetry(userId, message, 0);
    }

    private void sendNotificationWithRetry(String userId, String message, int attempt) {
        WebSocketSession session = sessions.get(userId);

        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
                System.out.println("✅ Notification sent to userId: " + userId + " on attempt " + attempt);
            } catch (Exception e) {
                System.out.println("❌ Error sending to userId: " + userId + " - " + e.getMessage());
            }
        } else {
            if (attempt < MAX_RETRIES) {
                System.out.println("⏳ Retry " + (attempt + 1) + " for userId: " + userId + " in " + RETRY_INTERVAL_MS + "ms");
                scheduler.schedule(() ->
                        sendNotificationWithRetry(userId, message, attempt + 1), RETRY_INTERVAL_MS, TimeUnit.MILLISECONDS);
            } else {
                System.out.println("❌ Failed to send after " + MAX_RETRIES + " attempts to userId: " + userId);
            }
        }
    }
}
