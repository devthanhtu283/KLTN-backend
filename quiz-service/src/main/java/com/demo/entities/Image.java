package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "image", schema = "jobs")
public class Image implements Serializable {
    private static final long serialVersionUID = -4238223986211622091L;
    private Integer id;

    private String name;

    private Integer userId;

    private Integer userType;

    private Boolean status = false;

    private LocalDate created;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @NotNull
    @Lob
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @NotNull
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    @NotNull
    @Column(name = "user_type", nullable = false)
    public Integer getUserType() {
        return userType;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    @NotNull
    @Column(name = "created", nullable = false)
    public LocalDate getCreated() {
        return created;
    }

}