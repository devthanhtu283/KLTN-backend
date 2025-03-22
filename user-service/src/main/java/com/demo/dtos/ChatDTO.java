package com.demo.dtos;

import com.demo.entities.Chat;

import java.util.Date;

public class ChatDTO {
    private Integer id;
    private Integer senderId;
    private Integer receiverId;
    private String senderRole;
    private String receiverRole;
    private String message;
    private Date time;
    private boolean status;

    // Constructor mặc định
    public ChatDTO() {
    }

    // Constructor để ánh xạ từ entity Chat
    public ChatDTO(Chat chat) {
        this.id = chat.getId();
        this.senderId = chat.getUserBySenderId() != null ? chat.getUserBySenderId().getId() : null;
        this.receiverId = chat.getUserByReceiverId() != null ? chat.getUserByReceiverId().getId() : null;
        this.senderRole = chat.getSenderRole();
        this.receiverRole = chat.getReceiverRole();
        this.message = chat.getMessage();
        this.time = chat.getTime();
        this.status = chat.isStatus();
    }

    // Getters và Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderRole() {
        return senderRole;
    }

    public void setSenderRole(String senderRole) {
        this.senderRole = senderRole;
    }

    public String getReceiverRole() {
        return receiverRole;
    }

    public void setReceiverRole(String receiverRole) {
        this.receiverRole = receiverRole;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}