package com.demo.entities;

import jakarta.persistence.Entity;


public class Email {

    public String from;
    public String to;
    public String subject;
    public String content;
    public Email() {
        super();
    }
    public Email(String from, String to, String subject, String content) {
        super();
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }



}