package com.demo.clients;

import com.demo.dtos.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:8084/notification/", fallback = NotificationServiceImpl.class)
public interface NotificationService {

    @PostMapping(value = "sendEmail",  produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendEmail(@RequestBody Email email);
}

@Component
class NotificationServiceImpl implements NotificationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public ResponseEntity<Object> sendEmail(@RequestBody Email email) {
        // fallback
        logger.error("Send Email Failed");
        return ResponseEntity.status(500).body("Send Email Failed");
    }
}
