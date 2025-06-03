package com.demo.configurations;

import com.demo.configurations.NotificationWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final NotificationWebSocketHandler notificationWebSocketHandler;
    private final WebSocketHandshakeInterceptor handshakeInterceptor;

    public WebSocketConfig(NotificationWebSocketHandler notificationWebSocketHandler, WebSocketHandshakeInterceptor handshakeInterceptor) {
        this.notificationWebSocketHandler = notificationWebSocketHandler;
        this.handshakeInterceptor = handshakeInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(notificationWebSocketHandler, "/notifications-websocket")
                .addInterceptors(handshakeInterceptor)
                .setAllowedOrigins("*");
    }
}