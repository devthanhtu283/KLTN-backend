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
@Table(name = "answer", schema = "jobs", indexes = {
        @Index(name = "questionID", columnList = "questionID")
})
public class Answer implements Serializable {
    private static final long serialVersionUID = -6995262951883366958L;
    private Integer id;

    private Integer questionID;

    private String content;

    private Boolean isCorrect;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @NotNull
    @Column(name = "questionID", nullable = false)
    public Integer getQuestionID() {
        return questionID;
    }

    @NotNull
    @Lob
    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    @Column(name = "is_correct")
    public Boolean getIsCorrect() {
        return isCorrect;
    }

}