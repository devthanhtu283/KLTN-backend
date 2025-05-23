package com.demo.entities;// default package
// Generated Jan 27, 2025, 4:56:06 PM by Hibernate Tools 4.3.6.Final

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Test generated by hbm2java
 */
@Entity
@Table(name = "test", catalog = "jobs", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Test implements java.io.Serializable {

	private Integer id;
	private User user;
	private String title;
	private String description;
	private String code;
	private Set<Testhistory> testhistories = new HashSet<Testhistory>(0);
	private Set<Question> questions = new HashSet<Question>(0);

	public Test() {
	}

	public Test(User user, String title, String description, String code) {
		this.user = user;
		this.title = title;
		this.description = description;
		this.code = code;
	}

	public Test(User user, String title, String description, String code, Set<Testhistory> testhistories,
                Set<Question> questions) {
		this.user = user;
		this.title = title;
		this.description = description;
		this.code = code;
		this.testhistories = testhistories;
		this.questions = questions;
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
	@JoinColumn(name = "userID", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "title", nullable = false, length = 65535)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", nullable = false, length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "code", unique = true, nullable = false, length = 250)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
	public Set<Testhistory> getTesthistories() {
		return this.testhistories;
	}

	public void setTesthistories(Set<Testhistory> testhistories) {
		this.testhistories = testhistories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
	public Set<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

}
