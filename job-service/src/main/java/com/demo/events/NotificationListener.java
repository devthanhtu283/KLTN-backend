package com.demo.events;

import com.demo.entities.Job;
import com.demo.entities.User;
import com.demo.repositories.EmployeeRepository;
import com.demo.repositories.UserRepository;
import com.demo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationListener {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @EventListener
    public void handleJobEvent(JobEvent event) {
        String eventType = event.getEventType();
        List<User> receivers = event.getReceivers();
        Job job = event.getJob();
        String jobTitle = event.getJobTitle();
        User employer = event.getUser();
        String companyName = employeeRepository.findById(employer.getId()).get().getCompanyName();

        for (User receiver : receivers) {
            switch (eventType) {
                case "JOB_CREATED":
                    notificationService.createNotification(
                            receiver,
                            job,
                            "Công việc mới từ " + companyName,
                            companyName + " vừa đăng công việc " + jobTitle + ".",
                            eventType
                    );
                    break;
                    
                default:
                    break;
            }
        }
    }
}