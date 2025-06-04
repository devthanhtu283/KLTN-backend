package com.demo.controllers;


import com.demo.dtos.NotificationDTO;
import com.demo.entities.User;
import com.demo.helpers.ApiResponseEntity;
import com.demo.repositories.UserRepository;
import com.demo.services.JobService;
import com.demo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("job")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobService jobService;

    @GetMapping("/notification/{userId}")
    public ApiResponseEntity<Object> getNotifications(@PathVariable Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<NotificationDTO> notifications = notificationService.getAllNotifications(user);
        return !notifications.isEmpty() ? ApiResponseEntity.success(notifications, "Successfully !!!")
                : ApiResponseEntity.badRequest("No notifications found");
    }

    @GetMapping("/notification/{userId}/unread")
    public ApiResponseEntity<Object> getUnreadNotifications(@PathVariable Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<NotificationDTO> unreadNotifications = notificationService.getUnreadNotifications(user);
        return !unreadNotifications.isEmpty() ? ApiResponseEntity.success(unreadNotifications, "Successfully !!!")
                : ApiResponseEntity.badRequest("No notifications found");
    }

    @PostMapping("/notification/{notificationId}/read")
    public ApiResponseEntity<Object> markAsRead(@PathVariable Integer notificationId) {
        notificationService.markAsRead(notificationId);
        return ApiResponseEntity.success(notificationId, "Successfully !!!");
    }
}