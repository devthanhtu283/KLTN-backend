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
@Table(name = "membership", schema = "jobs")
public class Membership implements Serializable {
    private static final long serialVersionUID = 3716896064358012161L;
    private Integer id;

    private String name;

    private Double price;

    private String description;

    private String duration;

    private Boolean status = false;

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
    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    @NotNull
    @Lob
    @Column(name = "duration", nullable = false)
    public String getDuration() {
        return duration;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

}