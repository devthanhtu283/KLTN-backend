package com.demo.dtos;

import java.util.Set;

public class TestDTO1 {
    private Integer id;
    private Integer userId;
    private String title;
    private String description;
    private String code;
    private Integer time;
    private Set<QuestionDTO1> questions;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Set<QuestionDTO1> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionDTO1> questions) {
        this.questions = questions;
    }
}