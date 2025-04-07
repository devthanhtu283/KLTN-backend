package com.demo.dtos;

import com.demo.entities.Employermembership;

import java.util.HashSet;
import java.util.Set;

public class MembershipDTO {

    private Integer id;
    private String name;
    private double price;
    private String description;
    private String duration;
    private boolean status;
    private Integer typeFor; // Thêm cột type_for kiểu Integer

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getTypeFor() {
        return typeFor;
    }

    public void setTypeFor(Integer typeFor) {
        this.typeFor = typeFor;
    }
}
