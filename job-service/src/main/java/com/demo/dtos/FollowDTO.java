package com.demo.dtos;

import java.util.Date;

public class FollowDTO {
    Integer id;
    Integer employerId;
    String employerName;
    Integer seekerId;
    String seekerName;
    boolean status;
    Date created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public Integer getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(Integer seekerId) {
        this.seekerId = seekerId;
    }

    public String getSeekerName() {
        return seekerName;
    }

    public void setSeekerName(String seekerName) {
        this.seekerName = seekerName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "FollowDTO{" +
                "id=" + id +
                ", employerId=" + employerId +
                ", employerName='" + employerName + '\'' +
                ", seekerId=" + seekerId +
                ", seekerName='" + seekerName + '\'' +
                ", status=" + status +
                ", created=" + created +
                '}';
    }
}
