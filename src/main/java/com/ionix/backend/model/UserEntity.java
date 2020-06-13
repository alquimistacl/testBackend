package com.ionix.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(name = "email_uk", columnNames = { "email" }),
		@UniqueConstraint(name = "name_email_uk", columnNames = { "name","email" }),
		@UniqueConstraint(name = "username_uk", columnNames = { "username" }) })
@Entity
public class UserEntity extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
