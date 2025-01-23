package com.demo.dtos;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDTO {
	private int id;
	private String username;
	private String password;
	private Integer userType;

	private String email;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created;
	private String securityCode;
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public int isStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", userType=" + userType +
				", email='" + email + '\'' +
				", created=" + created +
				", securityCode='" + securityCode + '\'' +
				", status=" + status +
				'}';
	}
}
