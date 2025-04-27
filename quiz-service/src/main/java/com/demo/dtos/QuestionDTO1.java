package com.demo.dtos;

import java.util.Set;
import java.util.HashSet;

public class QuestionDTO1 {
    private Integer id;
    private Integer testId;
    private int questionType;
    private String content;
    private Set<AnswerDTO1> answers;

    // Default constructor
    public QuestionDTO1() {
        this.answers = new HashSet<>();
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<AnswerDTO1> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerDTO1> answers) {
        this.answers = answers != null ? answers : new HashSet<>();
    }
}