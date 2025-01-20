package com.demo.entities;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "seeker", catalog = "jobs")
public class Seeker implements java.io.Serializable {

    @Id
    private int id;

    @OneToOne
    @MapsId // Sử dụng ID của User làm ID của Seeker
    @JoinColumn(name = "id") // Khóa ngoại trỏ đến ID của User
    private User user;

    @Column(name = "full_name", length = 65535) // Cho phép full_name có giá trị null
    private String fullName;

    @Column(name = "phone", length = 65535) // Cho phép phone có giá trị null
    private String phone;

    @Column(name = "address", length = 65535) // Cho phép address có giá trị null
    private String address;

    @Column(name = "dob", length = 10) // Cho phép dob có giá trị null
    private Date dob;

    @Column(name = "status") // Cho phép status có giá trị null
    private boolean status; // Đổi thành Boolean để dễ dàng hỗ trợ giá trị null

    @Column(name = "update_at", length = 19) // Cho phép updateAt có giá trị null
    private Timestamp updateAt;

    @Column(name = "gender", length = 10) // Cho phép dob có giá trị null
    private String gender;
    
    @Column(name = "avatar", length = 65535) // Cho phép avatar có giá trị null
    private String avatar;

    public Seeker() {
    }

    public Seeker(User user, String fullName, String phone, String address, Date dob, boolean status, Timestamp updateAt, String gender,
            String avatar) {
        this.user = user;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.status = status;
        this.updateAt = updateAt;
        this.gender = gender;
        this.avatar = avatar;
    }

    // Getters và setters cho các thuộc tính
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

    public Timestamp getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getStatus() {
		return status;
	}

	public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    @PrePersist
    @PreUpdate
    public void updateTimestamps() {
        this.updateAt = new Timestamp(System.currentTimeMillis()); // Tự động cập nhật thời gian hiện tại
    }


	@Override
	public String toString() {
		return "Seeker [id=" + id + ", user=" + user + ", fullName=" + fullName + ", phone=" + phone + ", address="
				+ address + ", dob=" + dob + ", status=" + status + ", updateAt=" + updateAt + ", gender=" + gender
				+ ", avatar=" + avatar + "]";
	}
}
