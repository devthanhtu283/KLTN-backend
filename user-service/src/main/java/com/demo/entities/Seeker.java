package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "seeker", schema = "jobs")
public class Seeker implements Serializable {
    private static final long serialVersionUID = -6951980054783251822L;
    private Integer id;

    private User user;

    private String fullName;

    private String phone;

    private String address;

    private LocalDate dob;

    private String gender;

    private Boolean status;

    private Instant updateAt;

    private String avatar;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    public User getUser() {
        return user;
    }

    @Lob
    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    @Lob
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    @Lob
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    @Column(name = "dob")
    public LocalDate getDob() {
        return dob;
    }

    @Size(max = 250)
    @Column(name = "gender", length = 250)
    public String getGender() {
        return gender;
    }

    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    @Column(name = "update_at")
    public Instant getUpdateAt() {
        return updateAt;
    }

    @Lob
    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

}