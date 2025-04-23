package com.demo.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "review", catalog = "jobs")
public class Review implements java.io.Serializable {

    private Integer id;
    private Seeker seeker;
    private Employer employer;
    private Integer rating;
    private boolean satisfied;
    private String goodMessage;
    private String reason;
    private String improve;
    private Timestamp createdAt;
    private boolean status;

    public Review() {
    }

    public Review(Seeker seeker, Employer employer, Integer rating, boolean satisfied,
                  String goodMessage, String reason, String improve, Timestamp createdAt, boolean status) {
        this.seeker = seeker;
        this.employer = employer;
        this.rating = rating;
        this.satisfied = satisfied;
        this.goodMessage = goodMessage;
        this.reason = reason;
        this.improve = improve;
        this.createdAt = createdAt;
        this.status = status;
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
    @JoinColumn(name = "seeker_id", nullable = false)
    public Seeker getSeeker() {
        return this.seeker;
    }

    public void setSeeker(Seeker seeker) {
        this.seeker = seeker;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    public Employer getEmployer() {
        return this.employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Column(name = "rating", nullable = false)
    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Column(name = "satisfied", nullable = false)
    public boolean isSatisfied() {
        return this.satisfied;
    }

    public void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
    }

    @Column(name = "good_message", columnDefinition = "TEXT")
    public String getGoodMessage() {
        return this.goodMessage;
    }

    public void setGoodMessage(String goodMessage) {
        this.goodMessage = goodMessage;
    }

    @Column(name = "reason", columnDefinition = "TEXT")
    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Column(name = "improve", columnDefinition = "TEXT")
    public String getImprove() {
        return this.improve;
    }

    public void setImprove(String improve) {
        this.improve = improve;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "status", nullable = false)
    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
