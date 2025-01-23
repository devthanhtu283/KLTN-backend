package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "answer", schema = "jobs", indexes = {
        @Index(name = "questionID", columnList = "questionID")
})
public class Answer implements Serializable {
    private static final long serialVersionUID = 1794574224414129470L;
    private Integer id;

    private Question questionID;

    private String content;

    private Boolean isCorrect;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Answer setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "questionID", nullable = false)
    public Question getQuestionID() {
        return questionID;
    }

    public Answer setQuestionID(Question questionID) {
        this.questionID = questionID;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public Answer setContent(String content) {
        this.content = content;
        return this;
    }

    @Column(name = "is_correct")
    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public Answer setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
        return this;
    }

}