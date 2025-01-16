package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "seeker", schema = "jobs")
public class Seeker implements Serializable {
    private static final long serialVersionUID = -4044971807442193829L;
    private Integer id;

    private User user;

    private String fullName;

    private String phone;

    private String address;

    private LocalDate dob;

    private Boolean status = false;

    private Instant updateAt;

    private String avatar;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Seeker setId(Integer id) {
        this.id = id;
        return this;
    }

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public Seeker setUser(User user) {
        this.user = user;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public Seeker setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "phone", nullable = false)
    public String getPhone() {
        return phone;
    }

    public Seeker setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public Seeker setAddress(String address) {
        this.address = address;
        return this;
    }

    @NotNull
    @Column(name = "dob", nullable = false)
    public LocalDate getDob() {
        return dob;
    }

    public Seeker setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Seeker setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @NotNull
    @Column(name = "update_at", nullable = false)
    public Instant getUpdateAt() {
        return updateAt;
    }

    public Seeker setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "avatar", nullable = false)
    public String getAvatar() {
        return avatar;
    }

    public Seeker setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

}