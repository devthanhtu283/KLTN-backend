package com.demo.dtos;

import java.time.LocalDateTime;

public class TestHistoryDTO {
    private int id;
    private int testID;
    private int userID;
    private LocalDateTime timeSubmit;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDateTime getTimeSubmit() {
        return timeSubmit;
    }

    public void setTimeSubmit(LocalDateTime timeSubmit) {
        this.timeSubmit = timeSubmit;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
