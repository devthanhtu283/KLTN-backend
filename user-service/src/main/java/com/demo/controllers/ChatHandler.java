package com.demo.controllers;

import com.demo.dtos.ChatDTO;

import com.demo.services.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Autowired
    private ChatService chatService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("New session connected: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Chuyển đổi tin nhắn JSON thành đối tượng ChatDTO
        ChatDTO chatMessage = objectMapper.readValue(message.getPayload(), ChatDTO.class);
        System.out.println("Received message from client: " + chatMessage.getMessage());

        // Lưu tin nhắn vào cơ sở dữ liệu
        ChatDTO savedMessage = chatService.save(chatMessage);

        // Chuyển đổi lại thành JSON để gửi cho các client
        String responseMessage = objectMapper.writeValueAsString(savedMessage);

        // Gửi tin nhắn đến tất cả các client (bao gồm cả client gửi tin nhắn)
        for (WebSocketSession s : sessions) {
            if (s.isOpen()) {
                s.sendMessage(new TextMessage(responseMessage));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Session disconnected: " + session.getId());
    }
}