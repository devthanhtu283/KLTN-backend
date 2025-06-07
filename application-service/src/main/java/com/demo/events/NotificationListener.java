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
                case "APPLICATION_ACCEPTED":
                    notificationService.createNotification(
                            receiver,
                            job,
                            "Ứng tuyển thành công",
                            "Hồ sơ của bạn ứng tuyển vào vị trí " + jobTitle + " đã được duyệt.",
                            eventType
                    );
                    break;
                case "APPLICATION_SEEN":
                    notificationService.createNotification(
                            receiver,
                            job,
                            "Hồ sơ đã được xem",
                            "Hồ sơ của bạn ứng tuyển vào vị trí " + jobTitle + " đã được xem.",
                            eventType
                    );
                    break;
                case "APPLICATION_REJECTED":
                    notificationService.createNotification(
                            receiver,
                            job,
                            "Hồ sơ đã bị từ chối",
                            "Hồ sơ của bạn ứng tuyển vào vị trí " + jobTitle + " đã bị từ chối. Đừng nản lòng hãy cố gắng lên nhé !!",
                            eventType
                    );
                    break;
                default:
                    break;
            }
        }
    }
}