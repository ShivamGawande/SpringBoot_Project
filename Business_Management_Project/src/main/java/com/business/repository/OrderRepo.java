package com.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business.entities.Orders;
import com.business.entities.User;

public interface OrderRepo extends JpaRepository<Orders, Integer> {
	List<Orders> findByUser(User user);
}
