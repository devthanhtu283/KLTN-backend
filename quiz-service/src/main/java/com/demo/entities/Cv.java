package com.demo.entities;// default package
// Generated Jan 27, 2025, 4:56:06 PM by Hibernate Tools 4.3.6.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

/**
 * Cv generated by hbm2java
 */
@Entity
@Table(name = "cv", catalog = "jobs")
public class Cv implements java.io.Serializable {

	private Integer id;
	private Seeker seeker;
	private String name;
	private String skills;
	private String experience;
	private String type;
	private String education;
	private String certification;
	private boolean status;
	private String offerSalary;
	private String jobDeadline;
	private String linkedIn;
	private String linkGit;
	private Date uploadAt;
	private Set<Matches> matcheses = new HashSet<Matches>(0);

	public Cv() {
	}

	public Cv(Seeker seeker, String name, String type, boolean status, Date uploadAt) {
		this.seeker = seeker;
		this.name = name;
		this.type = type;
		this.status = status;
		this.uploadAt = uploadAt;
	}

	public Cv(Seeker seeker, String name, String skills, String experience, String type, String education,
              String certification, boolean status, String offerSalary, String jobDeadline, String linkedIn,
              String linkGit, Date uploadAt, Set<Matches> matcheses) {
		this.seeker = seeker;
		this.name = name;
		this.skills = skills;
		this.experience = experience;
		this.type = type;
		this.education = education;
		this.certification = certification;
		this.status = status;
		this.offerSalary = offerSalary;
		this.jobDeadline = jobDeadline;
		this.linkedIn = linkedIn;
		this.linkGit = linkGit;
		this.uploadAt = uploadAt;
		this.matcheses = matcheses;
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
	@JoinColumn(name = "seeker_id", nullable = false)
	public Seeker getSeeker() {
		return this.seeker;
	}

	public void setSeeker(Seeker seeker) {
		this.seeker = seeker;
	}

	@Column(name = "name", nullable = false, length = 65535)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "skills", length = 65535)
	public String getSkills() {
		return this.skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	@Column(name = "experience", length = 65535)
	public String getExperience() {
		return this.experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	@Column(name = "type", nullable = false, length = 65535)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "education", length = 65535)
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "certification", length = 65535)
	public String getCertification() {
		return this.certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column(name = "offer_salary", length = 65535)
	public String getOfferSalary() {
		return this.offerSalary;
	}

	public void setOfferSalary(String offerSalary) {
		this.offerSalary = offerSalary;
	}

	@Column(name = "job_deadline", length = 65535)
	public String getJobDeadline() {
		return this.jobDeadline;
	}

	public void setJobDeadline(String jobDeadline) {
		this.jobDeadline = jobDeadline;
	}

	@Column(name = "linked_in", length = 65535)
	public String getLinkedIn() {
		return this.linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	@Column(name = "link_git", length = 65535)
	public String getLinkGit() {
		return this.linkGit;
	}

	public void setLinkGit(String linkGit) {
		this.linkGit = linkGit;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upload_at", nullable = false, length = 19)
	public Date getUploadAt() {
		return this.uploadAt;
	}

	public void setUploadAt(Date uploadAt) {
		this.uploadAt = uploadAt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cv")
	public Set<Matches> getMatcheses() {
		return this.matcheses;
	}

	public void setMatcheses(Set<Matches> matcheses) {
		this.matcheses = matcheses;
	}

}
