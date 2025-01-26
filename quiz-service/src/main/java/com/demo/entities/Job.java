package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "job", schema = "jobs")
public class Job implements Serializable {
    private static final long serialVersionUID = 3536184896600617300L;
    private Integer id;

    private Integer employerId;

    private String title;

    private String description;

    private String required;

    private String address;

    private Integer locationId;

    private String salary;

    private Boolean status = false;

    private Instant postedAt;

    private Instant postedExpired;

    private Integer experienceId;

    private String requiredSkills;

    private String member;

    private Integer workTypeId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @NotNull
    @Column(name = "employer_id", nullable = false)
    public Integer getEmployerId() {
        return employerId;
    }

    @NotNull
    @Lob
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    @NotNull
    @Lob
    @Column(name = "required", nullable = false)
    public String getRequired() {
        return required;
    }

    @NotNull
    @Lob
    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    @NotNull
    @Column(name = "location_id", nullable = false)
    public Integer getLocationId() {
        return locationId;
    }

    @NotNull
    @Lob
    @Column(name = "salary", nullable = false)
    public String getSalary() {
        return salary;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    @NotNull
    @Column(name = "posted_at", nullable = false)
    public Instant getPostedAt() {
        return postedAt;
    }

    @NotNull
    @Column(name = "posted_expired", nullable = false)
    public Instant getPostedExpired() {
        return postedExpired;
    }

    @NotNull
    @Column(name = "experience_id", nullable = false)
    public Integer getExperienceId() {
        return experienceId;
    }

    @NotNull
    @Lob
    @Column(name = "required_skills", nullable = false)
    public String getRequiredSkills() {
        return requiredSkills;
    }

    @NotNull
    @Lob
    @Column(name = "member", nullable = false)
    public String getMember() {
        return member;
    }

    @NotNull
    @Column(name = "work_type_id", nullable = false)
    public Integer getWorkTypeId() {
        return workTypeId;
    }

}