package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "test", schema = "jobs", indexes = {
        @Index(name = "userID", columnList = "userID")
}, uniqueConstraints = {
        @UniqueConstraint(name = "code", columnNames = {"code"})
})
public class Test implements Serializable {
    private static final long serialVersionUID = 2541590218849593500L;
    private Integer id;

    private String title;

    private String description;

    private User userID;

    private String code;

    private Set<Question> questions = new LinkedHashSet<>();

    private Set<Testhistory> testhistories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "testID")
    public Set<Testhistory> getTesthistories() {
        return testhistories;
    }

    public Test setTesthistories(Set<Testhistory> testhistories) {
        this.testhistories = testhistories;
        return this;
    }

    @OneToMany(mappedBy = "testID")
    public Set<Question> getQuestions() {
        return questions;
    }

    public Test setQuestions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Test setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public Test setTitle(String title) {
        this.title = title;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public Test setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    public User getUserID() {
        return userID;
    }

    public Test setUserID(User userID) {
        this.userID = userID;
        return this;
    }

    @Size(max = 250)
    @NotNull
    @Column(name = "code", nullable = false, length = 250)
    public String getCode() {
        return code;
    }

    public Test setCode(String code) {
        this.code = code;
        return this;
    }

}