package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findById(long user_id) {
		return userRepository.findById(user_id).get();
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
