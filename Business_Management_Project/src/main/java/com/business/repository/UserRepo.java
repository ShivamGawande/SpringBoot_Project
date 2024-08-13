package com.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByUemail(String uemail);
}
