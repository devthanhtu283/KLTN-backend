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
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public Employer setId(Integer id) {
        this.id = id;
        return this;
    }

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    public User getUser() {
        return user;
    }

    public Employer setUser(User user) {
        this.user = user;
        return this;
    }

    
    @Lob
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public Employer setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    
    @Lob
    @Column(name = "company_profile")
    public String getCompanyProfile() {
        return companyProfile;
    }

    public Employer setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
        return this;
    }

    
    @Column(name = "rating")
    public Double getRating() {
        return rating;
    }

    public Employer setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    
    @Lob
    @Column(name = "contact_info")
    public String getContactInfo() {
        return contactInfo;
    }

    public Employer setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }

    
    @Lob
    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    public Employer setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    
    @Lob
    @Column(name = "cover_img")
    public String getCoverImg() {
        return coverImg;
    }

    public Employer setCoverImg(String coverImg) {
        this.coverImg = coverImg;
        return this;
    }

    
    @Lob
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public Employer setAddress(String address) {
        this.address = address;
        return this;
    }

    
    @Lob
    @Column(name = "map_link")
    public String getMapLink() {
        return mapLink;
    }

    public Employer setMapLink(String mapLink) {
        this.mapLink = mapLink;
        return this;
    }

    
    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public Employer setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    
    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public Employer setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    
    @Lob
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public Employer setDescription(String description) {
        this.description = description;
        return this;
    }

    
    @Lob
    @Column(name = "founded_in")
    public String getFoundedIn() {
        return foundedIn;
    }

    public Employer setFoundedIn(String foundedIn) {
        this.foundedIn = foundedIn;
        return this;
    }

    
    @Lob
    @Column(name = "team_member")
    public String getTeamMember() {
        return teamMember;
    }

    public Employer setTeamMember(String teamMember) {
        this.teamMember = teamMember;
        return this;
    }

    
    @Lob
    @Column(name = "company_field")
    public String getCompanyField() {
        return companyField;
    }

    public Employer setCompanyField(String companyField) {
        this.companyField = companyField;
        return this;
    }

    
    @Lob
    @Column(name = "company_link")
    public String getCompanyLink() {
        return companyLink;
    }

    public Employer setCompanyLink(String companyLink) {
        this.companyLink = companyLink;
        return this;
    }

}