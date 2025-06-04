package com.demo.events;

import com.demo.entities.Job;
import com.demo.entities.User;
import com.demo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationListener {

    @Autowired
    private NotificationService notificationService;

    @EventListener
    public void handleJobEvent(JobEvent event) {
        String eventType = event.getEventType();
        List<User> receivers = event.getReceivers();
        Job job = event.getJob();
        String jobTitle = event.getJobTitle();
        User employer = event.getUser();

        for (User receiver : receivers) {
            switch (eventType) {
                case "JOB_CREATED":
                    notificationService.createNotification(
                            receiver,
                            job,
                            "Công việc mới từ " + employer.getUsername(),
                            employer.getUsername() + " vừa đăng công việc '" + jobTitle + "'.",
                            eventType
                    );
                    break;

                case "APPLICATION_SUBMITTED":
                    notificationService.createNotification(
                            receiver,
                            job,
                            "Ứng tuyển thành công",
                            "Bạn đã ứng tuyển vào công việc '" + jobTitle + "'.",
                            eventType
                    );
                    break;

                default:
                    break;
            }
        }
    }
}