package com.ionix.users.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ionix.users.exception.GenericSqlException;
import com.ionix.users.model.User;
import com.ionix.users.model.UserEntity;
import com.ionix.users.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/users")
	public ResponseEntity<String> createUser(@Valid @RequestBody User user) throws GenericSqlException {
		Long userCreated = service.addUser(user);
		return ResponseEntity.ok("User id " + userCreated + " created");
	}

	@GetMapping("/users/all")
	public ResponseEntity<List<UserEntity>> getUsers() throws GenericSqlException {
		List<UserEntity> users = service.getUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/users")
	public ResponseEntity<UserEntity> getUserByEmail(@Email @RequestParam String email) throws GenericSqlException {
		UserEntity userByEmail = service.getUserByEmail(email);
		return ResponseEntity.ok(userByEmail);
	}
}
