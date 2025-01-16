package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

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

    public Payment setId(Integer id) {
        this.id = id;
        return this;
    }

    @Column(name = "employer_membership_id")
    public Integer getEmployerMembershipId() {
        return employerMembershipId;
    }

    public Payment setEmployerMembershipId(Integer employerMembershipId) {
        this.employerMembershipId = employerMembershipId;
        return this;
    }

    @NotNull
    @Column(name = "employer_id", nullable = false)
    public Integer getEmployerId() {
        return employerId;
    }

    public Payment setEmployerId(Integer employerId) {
        this.employerId = employerId;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "payment_type", nullable = false)
    public String getPaymentType() {
        return paymentType;
    }

    public Payment setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    @NotNull
    @Column(name = "amount", nullable = false)
    public Double getAmount() {
        return amount;
    }

    public Payment setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "payment_method", nullable = false)
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Payment setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Payment setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @NotNull
    @Column(name = "transaction_id", nullable = false)
    public Integer getTransactionId() {
        return transactionId;
    }

    public Payment setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public Payment setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    @Column(name = "time", nullable = false)
    public Instant getTime() {
        return time;
    }

    public Payment setTime(Instant time) {
        this.time = time;
        return this;
    }

}