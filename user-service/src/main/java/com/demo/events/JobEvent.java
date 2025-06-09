//package com.demo.events;
//
//import com.demo.entities.Job;
//import com.demo.entities.User;
//import org.springframework.context.ApplicationEvent;
//
//import java.util.List;
//
//public class JobEvent extends ApplicationEvent {
//    private List<User> receivers;
//    private final User user;
//    private final Job job;
//    private final String jobTitle;
//    private final String eventType;
//
//    public JobEvent(Object source, List<User> receivers, Job job, User user, String jobTitle, String eventType) {
//        super(source);
//        this.receivers = receivers;
//        this.user = user;
//        this.job = job;
//        this.jobTitle = jobTitle;
//        this.eventType = eventType;
//    }
//
//    public List<User> getReceivers() {
//        return receivers;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public String getJobTitle() {
//        return jobTitle;
//    }
//
//    public String getEventType() {
//        return eventType;
//    }
//
//    public Job getJob() {
//        return job;
//    }
//}
