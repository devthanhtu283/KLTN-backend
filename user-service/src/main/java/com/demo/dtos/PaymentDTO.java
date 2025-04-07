package com.demo.dtos;

import com.demo.entities.Employer;
import com.demo.entities.Employermembership;

import java.util.Date;

public class PaymentDTO {
    private Integer id;
    private int employerMembershipId;
    private String paymentType;
    private double amount;
    private String paymentMethod;
    private boolean status;
    private int transactionId;
    private String description;
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEmployerMembershipId() {
        return employerMembershipId;
    }

    public void setEmployerMembershipId(int employerMembershipId) {
        this.employerMembershipId = employerMembershipId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
