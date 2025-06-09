package com.demo.events;

import com.demo.entities.Chat;
import com.demo.entities.Job;
import com.demo.entities.User;
import com.demo.repositories.EmployeeRepository;
import com.demo.repositories.UserRepository;
import com.demo.services.NotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @Autowired
    private NotiService notificationService;
    @Autowired
    private UserRepository userRepository;

    @EventListener
    public void handleChatEvent(ChatEvent event) {
        Chat chat = event.getChat();
        User sender = userRepository.findById(chat.getUserBySenderId().getId()).get();
        System.out.println(sender);
        User receiver = userRepository.findById(chat.getUserByReceiverId().getId()).get();
        try {
            notificationService.createNotification(
                    receiver,
                    new Job(),
                    "Tin nhắn mới từ tài khoản <span class='sender-name'>" + sender.getUsername() + "</span>",
                    "Tài khoản <span class='sender-name'>" + sender.getUsername() + "</span> đã gửi tin nhắn: \"" + chat.getMessage() + "\"",
                    "CHAT_MESSAGE"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}