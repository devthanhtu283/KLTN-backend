package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user", schema = "jobs", uniqueConstraints = {
        @UniqueConstraint(name = "username", columnNames = {"username"})
})
public class User implements Serializable {
    private static final long serialVersionUID = 5886079084834717110L;
    private Integer id;

    private String username;

    private String password;

    private Integer userType;

    private String email;

    private Date created;

    private String securityCode;

    private Integer status;

    private Employer employer;

    private Seeker seeker;

    private Set<Test> tests = new LinkedHashSet<>();

    private Set<Testhistory> testhistories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userID")
    public Set<Testhistory> getTesthistories() {
        return testhistories;
    }

    public User setTesthistories(Set<Testhistory> testhistories) {
        this.testhistories = testhistories;
        return this;
    }

    @OneToMany(mappedBy = "userID")
    public Set<Test> getTests() {
        return tests;
    }

    public User setTests(Set<Test> tests) {
        this.tests = tests;
        return this;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    public Seeker getSeeker() {
        return seeker;
    }

    public User setSeeker(Seeker seeker) {
        this.seeker = seeker;
        return this;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    public Employer getEmployer() {
        return employer;
    }

    public User setEmployer(Employer employer) {
        this.employer = employer;
        return this;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    @Size(max = 250)
    @NotNull
    @Column(name = "username", nullable = false, length = 250)
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Size(max = 250)
    @NotNull
    @Column(name = "password", nullable = false, length = 250)
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotNull
    @Column(name = "user_type", nullable = false)
    public Integer getUserType() {
        return userType;
    }

    public User setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @ColumnDefault("current_timestamp()")
    @Column(name = "created", nullable = false)
    public Date getCreated() {
        return created;
    }

    public User setCreated(Date created) {
        this.created = created == null ? null : created;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "security_code", nullable = false)
    public String getSecurityCode() {
        return securityCode;
    }

    public User setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public User setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", securityCode='" + securityCode + '\'' +
                ", status=" + status +

                ", tests=" + tests +
                ", testhistories=" + testhistories +
                '}';
    }
}