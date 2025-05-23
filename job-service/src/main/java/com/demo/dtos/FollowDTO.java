package com.demo.dtos;

import java.util.Date;

public class FollowDTO {
    Integer id;
    Integer employerId;
    String employerName;
    Integer seekerId;
    String seekerName;
    String address;
    String logo;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    @java.lang.Override
    public java.lang.String toString() {
        return "FollowDTO{" +
                "id=" + id +
                ", employerId=" + employerId +
                ", employerName='" + employerName + '\'' +
                ", seekerId=" + seekerId +
                ", seekerName='" + seekerName + '\'' +
                ", address='" + address + '\'' +
                ", logo='" + logo + '\'' +
                ", status=" + status +
                ", created=" + created +
                '}';
    }
}
