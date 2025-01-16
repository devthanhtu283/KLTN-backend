package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "question", schema = "jobs", indexes = {
        @Index(name = "testID", columnList = "testID")
})
public class Question implements Serializable {
    private static final long serialVersionUID = -164104230768525810L;
    private Integer id;

    private Test testID;

    private Integer questionType;

    private String content;

    private Set<Answer> answers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "questionID")
    public Set<Answer> getAnswers() {
        return answers;
    }

    public Question setAnswers(Set<Answer> answers) {
        this.answers = answers;
        return this;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Question setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "testID", nullable = false)
    public Test getTestID() {
        return testID;
    }

    public Question setTestID(Test testID) {
        this.testID = testID;
        return this;
    }

    @NotNull
    @Column(name = "question_type", nullable = false)
    public Integer getQuestionType() {
        return questionType;
    }

    public Question setQuestionType(Integer questionType) {
        this.questionType = questionType;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public Question setContent(String content) {
        this.content = content;
        return this;
    }

}