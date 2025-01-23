package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "skill", schema = "jobs")
public class Skill implements Serializable {
    private static final long serialVersionUID = -7901089397130710176L;
    private Integer id;

    private String name;

    private Integer skillParentId;

    private Boolean status = false;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Skill setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public Skill setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "skill_parent_id")
    public Integer getSkillParentId() {
        return skillParentId;
    }

    public Skill setSkillParentId(Integer skillParentId) {
        this.skillParentId = skillParentId;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Skill setStatus(Boolean status) {
        this.status = status;
        return this;
    }

}