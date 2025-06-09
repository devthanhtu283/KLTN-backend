package com.demo.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.demo.controllers.ChatHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatHandler chatHandler;
    private final NotificationWebSocketHandler notificationWebSocketHandler;
    private final WebSocketHandshakeInterceptor handshakeInterceptor;

    public WebSocketConfig(ChatHandler chatHandler,
                           WebSocketHandshakeInterceptor handshakeInterceptor,
                           NotificationWebSocketHandler notificationWebSocketHandler) {
        this.chatHandler = chatHandler;
        this.handshakeInterceptor = handshakeInterceptor;
        this.notificationWebSocketHandler = notificationWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "/ws-chat")
                .addInterceptors(handshakeInterceptor)
                .setAllowedOrigins("*");

        registry.addHandler(notificationWebSocketHandler, "/notifications-websocket")
                .addInterceptors(handshakeInterceptor)
                .setAllowedOrigins("*");
    }
}

