package com.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.entities.Product;
import com.business.repository.ProductRepo;

@Service
public class ProductServices {

	@Autowired
	private ProductRepo productRepo;
	
	//add product
	public void addProduct(Product p) {
		productRepo.save(p);
	}
	
	//get All product
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	//get single product
	public Product getProduct(Integer id) {
		return productRepo.findById(id).get();
	}
	
	//update product
	public void updateProduct(Product p, Integer id) {
		p.setPid(id);
		Product prod=productRepo.findById(id).get();
		if(prod.getPid()==id) {
			productRepo.save(p);
		}
	}
	
	//delete Product
	public void deleteProduct(Integer id) {
		productRepo.deleteById(id);
	}
	
	//Get Product By Name
	public Product getProductByName(String name) {
		Product product=productRepo.findByPname(name);
		if(product!=null) {
			return product;
		}
		return null;
	}
	
}

