package com.demo.entities;// default package
// Generated Jan 27, 2025, 4:56:06 PM by Hibernate Tools 4.3.6.Final

import jakarta.persistence.*;

/**
 * Skill generated by hbm2java
 */
@Entity
@Table(name = "skill", catalog = "jobs")
public class Skill implements java.io.Serializable {

	private Integer id;
	private String name;
	private Integer skillParentId;
	private boolean status;

	public Skill() {
	}

	public Skill(String name, boolean status) {
		this.name = name;
		this.status = status;
	}

	public Skill(String name, Integer skillParentId, boolean status) {
		this.name = name;
		this.skillParentId = skillParentId;
		this.status = status;
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

	@Column(name = "name", nullable = false, length = 65535)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "skill_parent_id")
	public Integer getSkillParentId() {
		return this.skillParentId;
	}

	public void setSkillParentId(Integer skillParentId) {
		this.skillParentId = skillParentId;
	}

	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
