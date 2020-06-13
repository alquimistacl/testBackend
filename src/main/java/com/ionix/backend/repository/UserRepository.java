package com.ionix.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.ionix.backend.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
}
