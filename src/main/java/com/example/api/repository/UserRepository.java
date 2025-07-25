package com.example.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.User;

public interface UserRepository extends JpaRepository<User,Integer >{

	
	Optional<User> findByEmail(String email);
}
