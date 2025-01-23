package com.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "employermembership", schema = "jobs")
public class Employermembership implements Serializable {
    private static final long serialVersionUID = 8786985530958564391L;
    private Integer id;

    private Integer employerId;

    private Integer membershipId;

    private Instant startDate;

    private Instant endDate;

    private Instant renewalDate;

    private Boolean status = false;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Employermembership setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Column(name = "employer_id", nullable = false)
    public Integer getEmployerId() {
        return employerId;
    }

    public Employermembership setEmployerId(Integer employerId) {
        this.employerId = employerId;
        return this;
    }

    @NotNull
    @Column(name = "membership_id", nullable = false)
    public Integer getMembershipId() {
        return membershipId;
    }

    public Employermembership setMembershipId(Integer membershipId) {
        this.membershipId = membershipId;
        return this;
    }

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "start_date", nullable = false)
    public Instant getStartDate() {
        return startDate;
    }

    public Employermembership setStartDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    @NotNull
    @Column(name = "end_date", nullable = false)
    public Instant getEndDate() {
        return endDate;
    }

    public Employermembership setEndDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    @NotNull
    @Column(name = "renewal_date", nullable = false)
    public Instant getRenewalDate() {
        return renewalDate;
    }

    public Employermembership setRenewalDate(Instant renewalDate) {
        this.renewalDate = renewalDate;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Employermembership setStatus(Boolean status) {
        this.status = status;
        return this;
    }

}