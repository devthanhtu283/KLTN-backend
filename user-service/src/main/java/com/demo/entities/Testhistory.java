package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "testhistory", schema = "jobs", indexes = {
        @Index(name = "testID", columnList = "testID"),
        @Index(name = "userID", columnList = "userID")
})
public class Testhistory implements Serializable {
    private static final long serialVersionUID = 794184357902061089L;
    private Integer id;

    private Integer testID;

    private Integer userID;

    private Instant timeSubmit;

    private Integer score;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "userID", nullable = false)
    public Integer getUserID() {
        return userID;
    }

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "timeSubmit", nullable = false)
    public Instant getTimeSubmit() {
        return timeSubmit;
    }

    @NotNull
    @Column(name = "score", nullable = false)
    public Integer getScore() {
        return score;
    }

}