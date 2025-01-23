package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "job", schema = "jobs")
public class Job implements Serializable {
    private static final long serialVersionUID = 7216694917501191789L;
    private Integer id;

    private Integer employerId;

    private String title;

    private String description;

    private String required;

    private String address;

    private String location;

    private String salary;

    private Boolean status = false;

    private Instant postedAt;

    private Instant postedExpired;

    private String experienceRequired;

    private Integer employmentTypeId;

    private Integer positionLevelId;

    private Integer fieldId;

    private String requiredSkills;

    private String member;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Job setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Column(name = "employer_id", nullable = false)
    public Integer getEmployerId() {
        return employerId;
    }

    public Job setEmployerId(Integer employerId) {
        this.employerId = employerId;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public Job setTitle(String title) {
        this.title = title;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public Job setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "required", nullable = false)
    public String getRequired() {
        return required;
    }

    public Job setRequired(String required) {
        this.required = required;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public Job setAddress(String address) {
        this.address = address;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "location", nullable = false)
    public String getLocation() {
        return location;
    }

    public Job setLocation(String location) {
        this.location = location;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "salary", nullable = false)
    public String getSalary() {
        return salary;
    }

    public Job setSalary(String salary) {
        this.salary = salary;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Job setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @NotNull
    @Column(name = "posted_at", nullable = false)
    public Instant getPostedAt() {
        return postedAt;
    }

    public Job setPostedAt(Instant postedAt) {
        this.postedAt = postedAt;
        return this;
    }

    @NotNull
    @Column(name = "posted_expired", nullable = false)
    public Instant getPostedExpired() {
        return postedExpired;
    }

    public Job setPostedExpired(Instant postedExpired) {
        this.postedExpired = postedExpired;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "experience_required", nullable = false)
    public String getExperienceRequired() {
        return experienceRequired;
    }

    public Job setExperienceRequired(String experienceRequired) {
        this.experienceRequired = experienceRequired;
        return this;
    }

    @NotNull
    @Column(name = "employment_type_id", nullable = false)
    public Integer getEmploymentTypeId() {
        return employmentTypeId;
    }

    public Job setEmploymentTypeId(Integer employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
        return this;
    }

    @NotNull
    @Column(name = "position_level_id", nullable = false)
    public Integer getPositionLevelId() {
        return positionLevelId;
    }

    public Job setPositionLevelId(Integer positionLevelId) {
        this.positionLevelId = positionLevelId;
        return this;
    }

    @NotNull
    @Column(name = "field_id", nullable = false)
    public Integer getFieldId() {
        return fieldId;
    }

    public Job setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "required_skills", nullable = false)
    public String getRequiredSkills() {
        return requiredSkills;
    }

    public Job setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "member", nullable = false)
    public String getMember() {
        return member;
    }

    public Job setMember(String member) {
        this.member = member;
        return this;
    }

}