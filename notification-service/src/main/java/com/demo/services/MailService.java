package com.demo.services;

public interface MailService {

    public boolean send(String from, String to, String subject, String content);

    public void sendNewJobEmail(String toEmail, String jobTitle, String companyName, String jobLink);

    public void sendMatchJobEmail(String toEmail, String jobTitle, String companyName, String jobLink);

}
