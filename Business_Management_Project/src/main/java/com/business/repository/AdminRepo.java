package com.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business.entities.Admin;


public interface AdminRepo extends JpaRepository<Admin, Integer>{

	public Admin findByAdminEmail(String adminEmail);

	
}
