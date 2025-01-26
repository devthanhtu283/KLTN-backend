package com.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "employer", schema = "jobs")
public class Employer implements Serializable {
    private static final long serialVersionUID = -4253252035367705706L;
    private Integer id;

    private User user;

    private String companyName;

    private String companyProfile;

    private Double rating;

    private String contactInfo;

    private String logo;

    private String coverImg;

    private String address;

    private String mapLink;

    private Double amount;

    private Boolean status;

    private String description;

    private String foundedIn;

    private String teamMember;

    private String companyField;

    private String companyLink;

    @Id
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
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    @Lob
    @Column(name = "company_profile")
    public String getCompanyProfile() {
        return companyProfile;
    }

    @Column(name = "rating")
    public Double getRating() {
        return rating;
    }

    @Lob
    @Column(name = "contact_info")
    public String getContactInfo() {
        return contactInfo;
    }

    @Lob
    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    @Lob
    @Column(name = "cover_img")
    public String getCoverImg() {
        return coverImg;
    }

    @Lob
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    @Lob
    @Column(name = "map_link")
    public String getMapLink() {
        return mapLink;
    }

    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    @Lob
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Lob
    @Column(name = "founded_in")
    public String getFoundedIn() {
        return foundedIn;
    }

    @Lob
    @Column(name = "team_member")
    public String getTeamMember() {
        return teamMember;
    }

    @Lob
    @Column(name = "company_field")
    public String getCompanyField() {
        return companyField;
    }

    @Lob
    @Column(name = "company_link")
    public String getCompanyLink() {
        return companyLink;
    }

}