package com.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business.entities.Product;


public interface ProductRepo extends JpaRepository<Product, Integer> {
	public Product findByPname(String pname);
}
