package com.demo.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "notifications", catalog = "jobs")
public class Notification implements java.io.Serializable {

    private Integer id;
    private User user;
    private String title;
    private String content;
    private boolean isRead = false;
    private Timestamp createdAt;
    private String type;

    public Notification() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "is_read", nullable = false)
    public boolean isRead() {
        return this.isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, length = 19)
    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "type", nullable = false, length = 50)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
