package com.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.entities.Orders;
import com.business.entities.User;
import com.business.repository.OrderRepo;

@Service
public class OrderServices {
	
	@Autowired
	private OrderRepo orderRepo;
	
	//get All orders
	public List<Orders> getAllOrders(){
		return orderRepo.findAll();
	}
	
	//add order
	public void saveOrder(Orders order) {
		orderRepo.save(order);
	}
	
	//update Order
	public void updateOrder(Orders order, Integer id) {
		order.setOid(id);
		orderRepo.save(order);
	}
	
	//delete order
	public void deleteOrder(Integer id) {
		orderRepo.deleteById(id);
	}
	
	//get order history by user
	public List<Orders> getOrderByUser(User user){
		return orderRepo.findByUser(user);
	}
	
}
