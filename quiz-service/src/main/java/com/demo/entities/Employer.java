package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

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

    private Boolean status = false;

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

    public Employer setId(Integer id) {
        this.id = id;
        return this;
    }

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public Employer setUser(User user) {
        this.user = user;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "company_name", nullable = false)
    public String getCompanyName() {
        return companyName;
    }

    public Employer setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "company_profile", nullable = false)
    public String getCompanyProfile() {
        return companyProfile;
    }

    public Employer setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
        return this;
    }

    @NotNull
    @Column(name = "rating", nullable = false)
    public Double getRating() {
        return rating;
    }

    public Employer setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "contact_info", nullable = false)
    public String getContactInfo() {
        return contactInfo;
    }

    public Employer setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "logo", nullable = false)
    public String getLogo() {
        return logo;
    }

    public Employer setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "cover_img", nullable = false)
    public String getCoverImg() {
        return coverImg;
    }

    public Employer setCoverImg(String coverImg) {
        this.coverImg = coverImg;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public Employer setAddress(String address) {
        this.address = address;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "map_link", nullable = false)
    public String getMapLink() {
        return mapLink;
    }

    public Employer setMapLink(String mapLink) {
        this.mapLink = mapLink;
        return this;
    }

    @NotNull
    @Column(name = "amount", nullable = false)
    public Double getAmount() {
        return amount;
    }

    public Employer setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Employer setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public Employer setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "founded_in", nullable = false)
    public String getFoundedIn() {
        return foundedIn;
    }

    public Employer setFoundedIn(String foundedIn) {
        this.foundedIn = foundedIn;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "team_member", nullable = false)
    public String getTeamMember() {
        return teamMember;
    }

    public Employer setTeamMember(String teamMember) {
        this.teamMember = teamMember;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "company_field", nullable = false)
    public String getCompanyField() {
        return companyField;
    }

    public Employer setCompanyField(String companyField) {
        this.companyField = companyField;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "company_link", nullable = false)
    public String getCompanyLink() {
        return companyLink;
    }

    public Employer setCompanyLink(String companyLink) {
        this.companyLink = companyLink;
        return this;
    }

}