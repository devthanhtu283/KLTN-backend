package com.demo.entities;// default package
// Generated Jan 27, 2025, 4:56:06 PM by Hibernate Tools 4.3.6.Final

import jakarta.persistence.*;

import java.util.Date;

/**
 * Payment generated by hbm2java
 */
@Entity
@Table(name = "payment", catalog = "jobs")
public class Payment implements java.io.Serializable {

	private Integer id;
	private Employer employer;
	private Employermembership employermembership;
	private String paymentType;
	private double amount;
	private String paymentMethod;
	private boolean status;
	private int transactionId;
	private String description;
	private Date time;

	public Payment() {
	}

	public Payment(Employer employer, String paymentType, double amount, String paymentMethod, boolean status,
                   int transactionId, String description, Date time) {
		this.employer = employer;
		this.paymentType = paymentType;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.transactionId = transactionId;
		this.description = description;
		this.time = time;
	}

	public Payment(Employer employer, Employermembership employermembership, String paymentType, double amount,
                   String paymentMethod, boolean status, int transactionId, String description, Date time) {
		this.employer = employer;
		this.employermembership = employermembership;
		this.paymentType = paymentType;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.transactionId = transactionId;
		this.description = description;
		this.time = time;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employer_id", nullable = false)
	public Employer getEmployer() {
		return this.employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employer_membership_id")
	public Employermembership getEmployermembership() {
		return this.employermembership;
	}

	public void setEmployermembership(Employermembership employermembership) {
		this.employermembership = employermembership;
	}

	@Column(name = "payment_type", nullable = false, length = 65535)
	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name = "amount", nullable = false, precision = 22, scale = 0)
	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name = "payment_method", nullable = false, length = 65535)
	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column(name = "transaction_id", nullable = false)
	public int getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	@Column(name = "description", nullable = false, length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", nullable = false, length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
