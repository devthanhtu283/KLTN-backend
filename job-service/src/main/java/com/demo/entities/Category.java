package com.demo.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category", catalog = "jobs")
public class Category implements java.io.Serializable {

    private int id;
    private String categoryName;
    private String subcategoryName;
    private Boolean status;
    private Set<Job> jobs = new HashSet<Job>(0);

    public Category() {
    }

    public Category(String categoryName, String subcategoryName, boolean status) {
        this.categoryName = categoryName;
        this.subcategoryName = subcategoryName;
        this.status = status;
    }

    public Category(String categoryName, String subcategoryName, boolean status, Set<Job> jobs) {
        this.categoryName = categoryName;
        this.subcategoryName = subcategoryName;
        this.status = status;
        this.jobs = jobs;
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

    @Column(name = "category_name", nullable = false, length = 65535)
    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Column(name = "subcategory_name", nullable = false, length = 65535)
    public String getSubcategoryName() {
        return this.subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    @Column(name = "status", nullable = false)
    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    public Set<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
}
