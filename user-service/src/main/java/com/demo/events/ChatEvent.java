package com.demo.events;

import com.demo.entities.Chat;
import org.springframework.context.ApplicationEvent;

public class ChatEvent extends ApplicationEvent {
    private final Chat chat;

    public ChatEvent(Object source, Chat chat) {
        super(source);
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }
}