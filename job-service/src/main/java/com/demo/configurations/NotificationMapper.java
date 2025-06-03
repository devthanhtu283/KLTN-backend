package com.demo.configurations;

import com.demo.dtos.NotificationDTO;
import com.demo.entities.Notification;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationMapper {

    public static NotificationDTO toDTO(Notification notification) {
        if (notification == null) {
            return null;
        }

        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setUserID(notification.getUser().getId());
        dto.setTitle(notification.getTitle());
        dto.setContent(notification.getContent());
        dto.setRead(notification.isRead());
        dto.setCreatedAt(notification.getCreatedAt());
        dto.setType(notification.getType());
        return dto;
    }

    public static List<NotificationDTO> toDTOList(List<Notification> notifications) {
        return notifications.stream()
                .map(NotificationMapper::toDTO)
                .collect(Collectors.toList());
    }
}