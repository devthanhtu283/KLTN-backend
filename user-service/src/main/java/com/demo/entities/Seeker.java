package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "seeker", schema = "jobs")
public class Seeker implements Serializable {
    private static final long serialVersionUID = -4044971807442193829L;
    private Integer id;

    private User user;

    private String fullName;

    private String phone;

    private String address;

    private Date dob;

    private String gender;

    private Boolean status = false;

    private Timestamp updateAt;

    private String avatar;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public Seeker setId(Integer id) {
        this.id = id;
        return this;
    }

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    public User getUser() {
        return user;
    }

    public Seeker setUser(User user) {
        this.user = user;
        return this;
    }

    
    @Lob
    @Column(name = "full_name", nullable = true)
    public String getFullName() {
        return fullName;
    }

    public Seeker setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    
    @Lob
    @Column(name = "phone", nullable = true)
    public String getPhone() {
        return phone;
    }

    public Seeker setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    
    @Lob
    @Column(name = "address", nullable = true)
    public String getAddress() {
        return address;
    }

    public Seeker setAddress(String address) {
        this.address = address;
        return this;
    }

    
    @Column(name = "dob", nullable = true)
    public Date getDob() {
        return dob;
    }

    public Seeker setDob(Date dob) {
        this.dob = dob;
        return this;
    }

    
    @Lob
    @Column(name = "gender", nullable = true)
    public String getGender() {
        return gender;
    }

    public Seeker setGender(String gender) {
        this.gender = gender;
        return this;
    }

    
    @Column(name = "status", nullable = true)
    public Boolean getStatus() {
        return status;
    }

    public Seeker setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    
    @Column(name = "update_at", nullable = true)
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public Seeker setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    
    @Lob
    @Column(name = "avatar", nullable = true)
    public String getAvatar() {
        return avatar;
    }

    public Seeker setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    @Override
    public String toString() {
        return "Seeker{" +
                "id=" + id +

                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", status=" + status +
                ", updateAt=" + updateAt +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}