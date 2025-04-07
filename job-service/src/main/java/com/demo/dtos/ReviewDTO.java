package com.demo.dtos;

import java.sql.Timestamp;
import java.util.Date;

public class ReviewDTO {

    private Integer id;
    private Integer seekerId;
    private Integer employerId;
    private Integer rating;
    private boolean satisfied;
    private String goodMessage;
    private String reason;
    private String improve;
    private Timestamp createdAt;
    private boolean status;

    public ReviewDTO() {
        
    }

    public ReviewDTO(Integer id, Integer seekerId, Integer employerId, Integer rating, boolean satisfied, String goodMessage, String reason, String improve, Timestamp createdAt, boolean status) {
        this.id = id;
        this.seekerId = seekerId;
        this.employerId = employerId;
        this.rating = rating;
        this.satisfied = satisfied;
        this.goodMessage = goodMessage;
        this.reason = reason;
        this.improve = improve;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(Integer seekerId) {
        this.seekerId = seekerId;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public boolean isSatisfied() {
        return satisfied;
    }

    public void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
    }

    public String getGoodMessage() {
        return goodMessage;
    }

    public void setGoodMessage(String goodMessage) {
        this.goodMessage = goodMessage;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getImprove() {
        return improve;
    }

    public void setImprove(String improve) {
        this.improve = improve;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
