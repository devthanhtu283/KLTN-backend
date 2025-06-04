package com.demo.services;

import com.demo.configurations.NotificationMapper;
import com.demo.configurations.NotificationWebSocketHandler;
import com.demo.configurations.RedisPublisher;
import com.demo.dtos.JobDTO;
import com.demo.dtos.NotificationDTO;
import com.demo.dtos.UserDTO;
import com.demo.entities.Job;
import com.demo.entities.Notification;
import com.demo.entities.User;
import com.demo.repositories.NotificationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisPublisher redisPublisher;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NotificationWebSocketHandler webSocketHandler;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String UNREAD_NOTIFICATIONS_KEY = "unread_notifications:";

    @Override
    public NotificationDTO createNotification(User user, Job job, String title, String content, String type) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setTitle(title);
        notification.setContent(content);
        notification.setType(type);
        notification.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        notification.setJobId(job.getId());

        notificationRepository.save(notification);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        JobDTO jobDTO = modelMapper.map(job, JobDTO.class);
        NotificationDTO notificationDTO = modelMapper.map(notification, NotificationDTO.class);
        // Lưu vào Redis (chỉ nếu chưa đọc)
        String redisKey = UNREAD_NOTIFICATIONS_KEY + userDTO.getId();
        redisTemplate.opsForList().leftPush(redisKey, notificationDTO);
        redisTemplate.expire(redisKey, 7, TimeUnit.DAYS);

        try {
            // Gửi qua WebSocket thuần
            String message = objectMapper.writeValueAsString(notificationDTO);
            webSocketHandler.sendNotification(String.valueOf(user.getId()), message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        redisPublisher.publish("notifications:" + userDTO.getId(), notificationDTO);

        return modelMapper.map(notification, NotificationDTO.class);
    }

    @Override
    public List<NotificationDTO> getUnreadNotifications(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        String redisKey = UNREAD_NOTIFICATIONS_KEY + userDTO.getId();
        List<Object> unreadNotifications = redisTemplate.opsForList().range(redisKey, 0, -1);
        List<Notification> result = new ArrayList<>();

        if (unreadNotifications != null) {
            for (Object obj : unreadNotifications) {
                result.add(modelMapper.map(obj, Notification.class));
            }
        }

        if (result.isEmpty()) {
            result = notificationRepository.findUnreadByUser(userDTO.getId());
            if (!result.isEmpty()) {
                for (Notification notification : result) {
                    redisTemplate.opsForList().leftPush(redisKey, notification);
                }
                redisTemplate.expire(redisKey, 7, TimeUnit.DAYS);
            }
        }

        // Chuyển sang DTO
        return NotificationMapper.toDTOList(result);
    }

    @Override
    public List<NotificationDTO> getAllNotifications(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        List<Notification> notifications = notificationRepository.findAllByUserOrderByCreatedAtDesc(userDTO.getId());
        return NotificationMapper.toDTOList(notifications);
    }

    @Override
    public void markAsRead(Integer notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);

        // Xóa khỏi Redis
        String redisKey = UNREAD_NOTIFICATIONS_KEY + notification.getUser().getId();
        List<Object> unreadNotifications = redisTemplate.opsForList().range(redisKey, 0, -1);
        if (unreadNotifications != null) {
            for (Object obj : unreadNotifications) {
                Notification n = modelMapper.map(obj, Notification.class);
                if (n.getId().equals(notificationId)) {
                    System.out.println("Found matching notification, removing...");
                    Long removedCount = redisTemplate.opsForList().remove(redisKey, 1, obj);
                    System.out.println("Removed " + removedCount + " entries from Redis for notification ID " + notificationId);
                    break;
                }
            }
        } else {
            System.out.println("No unread notifications found in Redis for key " + redisKey);
        }
    }
}
