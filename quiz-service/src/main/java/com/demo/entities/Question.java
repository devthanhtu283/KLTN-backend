package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "question", schema = "jobs", indexes = {
        @Index(name = "testID", columnList = "testID")
})
public class Question implements Serializable {
    private static final long serialVersionUID = -7234494582136438391L;
    private Integer id;

    private Integer testID;

    private Integer questionType;

    private String content;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @NotNull
    @Column(name = "testID", nullable = false)
    public Integer getTestID() {
        return testID;
    }

    @NotNull
    @Column(name = "question_type", nullable = false)
    public Integer getQuestionType() {
        return questionType;
    }

    @NotNull
    @Lob
    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

}