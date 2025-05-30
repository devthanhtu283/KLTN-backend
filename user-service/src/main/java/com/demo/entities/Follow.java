package com.demo.entities;// default package
// Generated Jan 27, 2025, 4:56:06 PM by Hibernate Tools 4.3.6.Final

import jakarta.persistence.*;

import java.util.Date;

/**
 * Follow generated by hbm2java
 */
@Entity
@Table(name = "follow", catalog = "jobs")
public class Follow implements java.io.Serializable {

	private Integer id;
	private Employer employer;
	private Seeker seeker;
	private boolean status;
	private Date created;

	public Follow() {
	}

	public Follow(Employer employer, Seeker seeker, boolean status, Date created) {
		this.employer = employer;
		this.seeker = seeker;
		this.status = status;
		this.created = created;
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
	@JoinColumn(name = "seeker_id", nullable = false)
	public Seeker getSeeker() {
		return this.seeker;
	}

	public void setSeeker(Seeker seeker) {
		this.seeker = seeker;
	}

	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created", nullable = false, length = 10)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
