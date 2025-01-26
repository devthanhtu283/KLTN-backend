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
@Table(name = "skill", schema = "jobs")
public class Skill implements Serializable {
    private static final long serialVersionUID = -7901089397130710176L;
    private Integer id;

    private String name;

    private Integer skillParentId;

    private Boolean status = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "skill_parent_id")
    public Integer getSkillParentId() {
        return skillParentId;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

}