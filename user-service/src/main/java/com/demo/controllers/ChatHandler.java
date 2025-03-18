package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<WebSocketSession> sessions = new ArrayList<>();

//    @Autowired
//    private ChatService chatService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("New session connected: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Chuyển đổi tin nhắn JSON thành đối tượng ChatDTO
//        ChatDTO chatMessage = objectMapper.readValue(message.getPayload(), ChatDTO.class);
//        System.out.println("Received message from client: " + chatMessage.getContent());
//
//        // Lưu tin nhắn vào cơ sở dữ liệu
//        chatService.save(chatMessage);

        // Chuyển đổi lại thành JSON để gửi cho các phiên khác
//        String responseMessage = objectMapper.writeValueAsString(chatMessage);

        // Gửi tin nhắn đến tất cả các client khác (ngoại trừ chính client gửi tin nhắn này)
        for (WebSocketSession s : sessions) {
            if (s.isOpen() && s != session) {
                s.sendMessage(message);
            }
        }

        // Nếu chỉ có một client, vẫn cần gửi phản hồi để client có thể hiển thị tin nhắn
        if (sessions.size() == 1) {
            session.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Session disconnected: " + session.getId());
    }
}
