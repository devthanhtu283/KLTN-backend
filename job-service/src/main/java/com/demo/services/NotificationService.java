package com.demo.services;

import com.demo.dtos.NotificationDTO;
import com.demo.entities.Notification;
import com.demo.entities.User;

import java.util.List;

public interface NotificationService {
    NotificationDTO createNotification(User user, String title, String content, String type);

    List<NotificationDTO> getUnreadNotifications(User user);

    List<NotificationDTO> getAllNotifications(User user);

    void markAsRead(Integer notificationId);
}
