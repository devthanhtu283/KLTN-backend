package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
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

    @NotNull
    @Column(name = "sender_id", nullable = false)
    public Integer getSenderId() {
        return senderId;
    }

    @NotNull
    @Lob
    @Column(name = "sender_role", nullable = false)
    public String getSenderRole() {
        return senderRole;
    }

    @NotNull
    @Column(name = "receiver_id", nullable = false)
    public Integer getReceiverId() {
        return receiverId;
    }

    @NotNull
    @Lob
    @Column(name = "receiver_role", nullable = false)
    public String getReceiverRole() {
        return receiverRole;
    }

    @NotNull
    @Lob
    @Column(name = "message", nullable = false)
    public String getMessage() {
        return message;
    }

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "time", nullable = false)
    public Instant getTime() {
        return time;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

}