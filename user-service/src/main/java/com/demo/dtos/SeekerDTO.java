package com.demo.dtos;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SeekerDTO {
	private int id;
	private String fullName;
	private String phone;
	private String address;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dob;
	private boolean status;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Timestamp updateAt;
	private String gender;
	private String avatar;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Timestamp getUpdateAt() {
		return updateAt;
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@Override
	public String toString() {
		return "SeekerDTO [id=" + id + ", fullName=" + fullName + ", phone=" + phone + ", address=" + address + ", dob="
				+ dob + ", status=" + status + ", updateAt=" + updateAt + ", gender=" + gender + ", avatar=" + avatar
				+ "]";
	}
	
}
