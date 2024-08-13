package com.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.entities.Admin;
import com.business.repository.AdminRepo;

@Service
public class AdminServices {

	@Autowired
	private AdminRepo adminRepo;
	
	//get All Admins
	public List<Admin> getAll(){
		List<Admin> all = adminRepo.findAll();
		return all;
	}
	
	//Get Single Admin
	public Admin getAdmin(Integer id) {
		Admin admin = adminRepo.findById(id).get();
		return admin;
	}
	
	//Upadte Admin
	public void update(Admin admin, Integer id) {
		for(Admin ad : getAll()) {
			if(ad.getAdminId()==id) {
				adminRepo.save(admin);
			}
			else {
				System.out.println("id not valid");
			}
		}
	}
	
	//delet User
	public void deleteUser(Integer id) {
		adminRepo.deleteById(id);
	}
	
	//add admin
	public void addAdmin(Admin admin) {
		adminRepo.save(admin);
	}
	
	//Validate Admin Login
	public Boolean validatingLogin(String email,String password) {
		Admin admin = adminRepo.findByAdminEmail(email);
		if(admin!=null && admin.getAdminPassword().equals(password)) {
			return true;
		}
		return false;
	}
}
