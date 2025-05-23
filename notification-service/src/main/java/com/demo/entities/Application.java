package com.demo.entities;// default package
// Generated Jan 27, 2025, 4:56:06 PM by Hibernate Tools 4.3.6.Final

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



/**
 * Application generated by hbm2java
 */
@Entity
@Table(name = "application", catalog = "jobs")
public class Application implements java.io.Serializable {

	private Integer id;
	private Job job;
	private Seeker seeker;
	private Date appliedAt;
	private int status;
	private Set<Interview> interviews = new HashSet<Interview>(0);

	public Application() {
	}

	public Application(Job job, Seeker seeker, Date appliedAt, int status) {
		this.job = job;
		this.seeker = seeker;
		this.appliedAt = appliedAt;
		this.status = status;
	}

	public Application(Job job, Seeker seeker, Date appliedAt, int status, Set<Interview> interviews) {
		this.job = job;
		this.seeker = seeker;
		this.appliedAt = appliedAt;
		this.status = status;
		this.interviews = interviews;
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
	@JoinColumn(name = "job_id", nullable = false)
	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seeker_id", nullable = false)
	public Seeker getSeeker() {
		return this.seeker;
	}

	public void setSeeker(Seeker seeker) {
		this.seeker = seeker;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "applied_at", nullable = false, length = 19)
	public Date getAppliedAt() {
		return this.appliedAt;
	}

	public void setAppliedAt(Date appliedAt) {
		this.appliedAt = appliedAt;
	}

	@Column(name = "status", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
	public Set<Interview> getInterviews() {
		return this.interviews;
	}

	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}

}
