package com.ionix.users.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ionix.users.exception.GenericSqlException;
import com.ionix.users.model.User;
import com.ionix.users.model.UserEntity;
import com.ionix.users.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public Long addUser(User user) throws GenericSqlException {
		UserEntity entity = new UserEntity();
		entity.setEmail(user.getEmail());
		entity.setName(user.getName());
		entity.setPhone(user.getPhone());
		entity.setUsername(user.getUsername());

		UserEntity savedUser = new UserEntity();
		try {
			savedUser = repository.save(entity);
		} catch (Exception e) {
			throw new GenericSqlException(e.getMessage());
		}

		return savedUser.getId();
	}

	public List<UserEntity> getUsers() throws GenericSqlException {
		List<UserEntity> users = new ArrayList<>();
		try {
			repository.findAll().forEach(users::add);
		} catch (Exception e) {
			throw new GenericSqlException(e.getMessage());
		}

		return users;
	}

	public UserEntity getUserByEmail(String email) throws GenericSqlException {
		UserEntity userFound = new UserEntity();
		try {
			userFound = repository.findByEmail(email);
		} catch (Exception e) {
			throw new GenericSqlException(e.getMessage());
		}
		return userFound;
	}
}
