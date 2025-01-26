package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "user", schema = "jobs", uniqueConstraints = {
        @UniqueConstraint(name = "username", columnNames = {"username"})
})
public class User implements Serializable {
    private static final long serialVersionUID = -4431340165405147830L;
    private Integer id;

    private String username;

    private String password;

    private Integer userType;

    private String email;

    private LocalDate created;

    private String securityCode;

    private Integer status;

    private Employer employer;

    private Seeker seeker;

    private Set<Test> tests = new LinkedHashSet<>();

    private Set<Testhistory> testhistories = new LinkedHashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @Size(max = 250)
    @NotNull
    @Column(name = "username", nullable = false, length = 250)
    public String getUsername() {
        return username;
    }

    @Size(max = 250)
    @NotNull
    @Column(name = "password", nullable = false, length = 250)
    public String getPassword() {
        return password;
    }

    @NotNull
    @Column(name = "user_type", nullable = false)
    public Integer getUserType() {
        return userType;
    }

    @NotNull
    @Lob
    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created", nullable = false)
    public LocalDate getCreated() {
        return created;
    }

    @NotNull
    @Lob
    @Column(name = "security_code", nullable = false)
    public String getSecurityCode() {
        return securityCode;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    @OneToOne(mappedBy = "user")
    public Employer getEmployer() {
        return employer;
    }

    @OneToOne(mappedBy = "user")
    public Seeker getSeeker() {
        return seeker;
    }

    @OneToMany(mappedBy = "userID")
    public Set<Test> getTests() {
        return tests;
    }

    @OneToMany(mappedBy = "userID")
    public Set<Testhistory> getTesthistories() {
        return testhistories;
    }

}