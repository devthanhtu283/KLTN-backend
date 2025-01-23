package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "field", schema = "jobs")
public class Field implements Serializable {
    private static final long serialVersionUID = -4268337654813396291L;
    private Integer id;

    private String name;

    private Integer fieldParentId;

    private Boolean status = false;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Field setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public Field setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Column(name = "field_parent_id", nullable = false)
    public Integer getFieldParentId() {
        return fieldParentId;
    }

    public Field setFieldParentId(Integer fieldParentId) {
        this.fieldParentId = fieldParentId;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Field setStatus(Boolean status) {
        this.status = status;
        return this;
    }

}