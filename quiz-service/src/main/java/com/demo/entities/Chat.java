package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "chat", schema = "jobs")
public class Chat implements Serializable {
    private static final long serialVersionUID = -5295771087665266213L;
    private Integer id;

    private Integer senderId;

    private String senderRole;

    private Integer receiverId;

    private String receiverRole;

    private String message;

    private Instant time;

    private Boolean status = false;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Chat setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Column(name = "sender_id", nullable = false)
    public Integer getSenderId() {
        return senderId;
    }

    public Chat setSenderId(Integer senderId) {
        this.senderId = senderId;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "sender_role", nullable = false)
    public String getSenderRole() {
        return senderRole;
    }

    public Chat setSenderRole(String senderRole) {
        this.senderRole = senderRole;
        return this;
    }

    @NotNull
    @Column(name = "receiver_id", nullable = false)
    public Integer getReceiverId() {
        return receiverId;
    }

    public Chat setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "receiver_role", nullable = false)
    public String getReceiverRole() {
        return receiverRole;
    }

    public Chat setReceiverRole(String receiverRole) {
        this.receiverRole = receiverRole;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "message", nullable = false)
    public String getMessage() {
        return message;
    }

    public Chat setMessage(String message) {
        this.message = message;
        return this;
    }

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "time", nullable = false)
    public Instant getTime() {
        return time;
    }

    public Chat setTime(Instant time) {
        this.time = time;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Chat setStatus(Boolean status) {
        this.status = status;
        return this;
    }

}