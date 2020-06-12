package com.ionix.users.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
public class User {

	@NotEmpty(message = "Name is mandatory")
	private String name;

	@NotEmpty(message = "Username is mandatory")
	private String username;

	@Email
	@NotEmpty(message = "Email is mandatory")
	private String email;

	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [getName()=" + getName() + ", getUsername()=" + getUsername() + ", getEmail()=" + getEmail()
				+ ", getPhone()=" + getPhone() + "]";
	}

}
