package com.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
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

    @NotNull
    @Column(name = "employer_id", nullable = false)
    public Integer getEmployerId() {
        return employerId;
    }

    @NotNull
    @Column(name = "membership_id", nullable = false)
    public Integer getMembershipId() {
        return membershipId;
    }

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "start_date", nullable = false)
    public Instant getStartDate() {
        return startDate;
    }

    @NotNull
    @Column(name = "end_date", nullable = false)
    public Instant getEndDate() {
        return endDate;
    }

    @NotNull
    @Column(name = "renewal_date", nullable = false)
    public Instant getRenewalDate() {
        return renewalDate;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

}