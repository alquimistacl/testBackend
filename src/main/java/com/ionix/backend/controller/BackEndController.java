package com.ionix.backend.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ionix.backend.exception.GenericSqlException;
import com.ionix.backend.exception.InternalException;
import com.ionix.backend.model.ExternalResponse;
import com.ionix.backend.model.User;
import com.ionix.backend.model.UserEntity;
import com.ionix.backend.service.ExternalApiService;
import com.ionix.backend.service.UserService;

@RestController
public class BackEndController {

	@Autowired
	private UserService userService;

	@Autowired
	private ExternalApiService externalService;

	@PostMapping("/users")
	public ResponseEntity<String> createUser(@Valid @RequestBody User user) throws GenericSqlException {
		Long userCreated = userService.addUser(user);
		return ResponseEntity.ok("User id " + userCreated + " created");
	}

	@GetMapping("/users/all")
	public ResponseEntity<List<UserEntity>> getUsers() throws GenericSqlException {
		List<UserEntity> users = userService.getUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/users")
	public ResponseEntity<UserEntity> getUserByEmail(@Email @RequestParam String email) throws GenericSqlException {
		UserEntity userByEmail = userService.getUserByEmail(email);
		return ResponseEntity.ok(userByEmail);
	}

	@PostMapping("/rutinfo/{rut}")
	public ResponseEntity<ExternalResponse> getRutInfo(@PathVariable("rut") String rut) throws InternalException {
		ExternalResponse externalResponse = externalService.getExternalResponse(rut);
		return ResponseEntity.ok(externalResponse);
	}

}
