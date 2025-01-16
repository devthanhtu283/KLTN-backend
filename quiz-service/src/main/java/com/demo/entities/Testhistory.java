package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "testhistory", schema = "jobs", indexes = {
        @Index(name = "testID", columnList = "testID"),
        @Index(name = "userID", columnList = "userID")
})
public class Testhistory implements Serializable {
    private static final long serialVersionUID = 2392311558408054079L;
    private Integer id;

    private Test testID;

    private User userID;

    private Instant timeSubmit;

    private Integer score;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Testhistory setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "testID", nullable = false)
    public Test getTestID() {
        return testID;
    }

    public Testhistory setTestID(Test testID) {
        this.testID = testID;
        return this;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    public User getUserID() {
        return userID;
    }

    public Testhistory setUserID(User userID) {
        this.userID = userID;
        return this;
    }

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "timeSubmit", nullable = false)
    public Instant getTimeSubmit() {
        return timeSubmit;
    }

    public Testhistory setTimeSubmit(Instant timeSubmit) {
        this.timeSubmit = timeSubmit;
        return this;
    }

    @NotNull
    @Column(name = "score", nullable = false)
    public Integer getScore() {
        return score;
    }

    public Testhistory setScore(Integer score) {
        this.score = score;
        return this;
    }

}