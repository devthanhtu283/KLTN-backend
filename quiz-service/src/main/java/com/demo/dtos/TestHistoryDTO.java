package com.demo.dtos;

public class TestHistoryDTO {
    private int id;
    private int testID;
    private int userID;

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




    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
