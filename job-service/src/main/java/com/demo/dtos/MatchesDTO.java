package com.demo.dtos;

import java.util.Date;

public class MatchesDTO {

    private Integer id;
    private Integer cvId;
    private Integer seekerId;  // Sửa "SeekerId" thành "seekerId" để tuân theo quy tắc camelCase
    private Integer jobId;
    private String jobName;
    private String matchedSkill;
    private Date timeMatches;
    private boolean status;
    private Double accuracy;
    private String label;

    // Các trường từ JobDTO
    private Integer employerId;
    private Integer experienceId;
    private Integer locationId;
    private Integer worktypeId;
    private Integer categoryId;
    private String employerName;
    private String experienceName;
    private String employerLogo;
    private String locationName;
    private String worktypeName;
    private String categoryName;
    private String title;
    private String description;
    private String required;
    private String address;
    private String salary;
    private Date postedAt;
    private Date postedExpired;
    private String requiredSkills;
    private String member;

    // Getters và Setters cho các trường hiện có
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public Integer getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(Integer seekerId) {
        this.seekerId = seekerId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getMatchedSkill() {
        return matchedSkill;
    }

    public void setMatchedSkill(String matchedSkill) {
        this.matchedSkill = matchedSkill;
    }

    public Date getTimeMatches() {
        return timeMatches;
    }

    public void setTimeMatches(Date timeMatches) {
        this.timeMatches = timeMatches;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Getters và Setters cho các trường từ JobDTO
    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public Integer getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Integer experienceId) {
        this.experienceId = experienceId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getWorktypeId() {
        return worktypeId;
    }

    public void setWorktypeId(Integer worktypeId) {
        this.worktypeId = worktypeId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public String getEmployerLogo() {
        return employerLogo;
    }

    public void setEmployerLogo(String employerLogo) {
        this.employerLogo = employerLogo;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public Date getPostedExpired() {
        return postedExpired;
    }

    public void setPostedExpired(Date postedExpired) {
        this.postedExpired = postedExpired;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accurary) {
        this.accuracy = accurary;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}