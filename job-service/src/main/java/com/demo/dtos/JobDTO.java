package com.demo.dtos;

import com.demo.entities.Employer;
import com.demo.entities.Experience;
import com.demo.entities.Location;
import com.demo.entities.Worktype;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class JobDTO {
    private Integer id;
    private Integer employerId;
    private Integer experienceId;
    private Integer locationId;
    private Integer worktypeId;
    private String employerName;
    private String experienceName;
    private String employerLogo;
    private String locationName;
    private String worktypeName;
    private String title;
    private String description;
    private String required;
    private String address;
    private String salary;
    private boolean status;
    private Date postedAt;
    private Date postedExpired;
    private String requiredSkills;
    private String member;

    public Integer getId() {
        return id;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public Integer getExperienceId() {
        return experienceId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public Integer getWorktypeId() {
        return worktypeId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRequired() {
        return required;
    }

    public String getAddress() {
        return address;
    }

    public String getSalary() {
        return salary;
    }

    public boolean isStatus() {
        return status;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public Date getPostedExpired() {
        return postedExpired;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public String getMember() {
        return member;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public void setExperienceId(Integer experienceId) {
        this.experienceId = experienceId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public void setWorktypeId(Integer worktypeId) {
        this.worktypeId = worktypeId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public void setPostedExpired(Date postedExpired) {
        this.postedExpired = postedExpired;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getExperienceName() {
        return experienceName;
    }

    public void setExperienceName(String experienceName) {
        this.experienceName = experienceName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getWorktypeName() {
        return worktypeName;
    }

    public void setWorktypeName(String worktypeName) {
        this.worktypeName = worktypeName;
    }

    public String getEmployerLogo() {
        return employerLogo;
    }

    public void setEmployerLogo(String emplloyerLogo) {
        this.employerLogo = emplloyerLogo;
    }
}
