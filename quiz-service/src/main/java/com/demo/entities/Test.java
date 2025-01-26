package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "test", schema = "jobs", indexes = {
        @Index(name = "userID", columnList = "userID")
}, uniqueConstraints = {
        @UniqueConstraint(name = "code", columnNames = {"code"})
})
public class Test implements Serializable {
    private static final long serialVersionUID = -9189056788231156471L;
    private Integer id;

    private String title;

    private String description;

    private User userID;

    private String code;

    private Set<Question> questions = new LinkedHashSet<>();

    private Set<Testhistory> testhistories = new LinkedHashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @NotNull
    @Lob
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    public User getUserID() {
        return userID;
    }

    @Size(max = 250)
    @NotNull
    @Column(name = "code", nullable = false, length = 250)
    public String getCode() {
        return code;
    }

    @OneToMany(mappedBy = "testID")
    public Set<Question> getQuestions() {
        return questions;
    }

    @OneToMany(mappedBy = "testID")
    public Set<Testhistory> getTesthistories() {
        return testhistories;
    }

}