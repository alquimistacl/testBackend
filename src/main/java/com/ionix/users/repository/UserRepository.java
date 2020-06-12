package com.ionix.users.repository;

import org.springframework.data.repository.CrudRepository;

import com.ionix.users.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
}
