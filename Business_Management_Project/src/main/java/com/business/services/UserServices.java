package com.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.entities.User;
import com.business.repository.UserRepo;

@Service
public class UserServices {

	@Autowired
	private UserRepo userRepo;
	
	//get All Users
	
	public List<User> getAll(){
		return userRepo.findAll();
	}
	
	//get User by 
	public User getUser(Integer id) {
		return userRepo.findById(id).get();
	}
	
	//get User By mail
	public User getUserByEmail(String email) {
		return userRepo.findByUemail(email);
	}
	
	//update user
	public void updateUser(User user,Integer id) {
		user.setU_id(id);
		userRepo.save(user);
	}
	
	//delete User
	public void deleteUser(Integer id) {
		userRepo.deleteById(id);
	}
	
	//add User
	public void addUser(User user) {
		userRepo.save(user);
	}
	
	//validating user
	@SuppressWarnings("unlikely-arg-type")
	public Boolean validateUser(String email, String password) {
		for(User us:getAll()) {
			if(us!=null && us.equals(email)&&us.equals(password)) {
				return true;
			}
		}
		return false;
	}
}
