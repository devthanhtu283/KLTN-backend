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
@Table(name = "payment", schema = "jobs")
public class Payment implements Serializable {
    private static final long serialVersionUID = -788117863824020374L;
    private Integer id;

    private Integer employerMembershipId;

    private Integer employerId;

    private String paymentType;

    private Double amount;

    private String paymentMethod;

    private Boolean status = false;

    private Integer transactionId;

    private String description;

    private Instant time;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @Column(name = "employer_membership_id")
    public Integer getEmployerMembershipId() {
        return employerMembershipId;
    }

    @NotNull
    @Column(name = "employer_id", nullable = false)
    public Integer getEmployerId() {
        return employerId;
    }

    @NotNull
    @Lob
    @Column(name = "payment_type", nullable = false)
    public String getPaymentType() {
        return paymentType;
    }

    @NotNull
    @Column(name = "amount", nullable = false)
    public Double getAmount() {
        return amount;
    }

    @NotNull
    @Lob
    @Column(name = "payment_method", nullable = false)
    public String getPaymentMethod() {
        return paymentMethod;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    @NotNull
    @Column(name = "transaction_id", nullable = false)
    public Integer getTransactionId() {
        return transactionId;
    }

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    @NotNull
    @Column(name = "time", nullable = false)
    public Instant getTime() {
        return time;
    }

}