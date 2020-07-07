package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.User;
import com.ecommerce.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{user_id}")
	public User findById(@PathVariable("user_id") long user_id) {
		return userService.findById(user_id);
	}
	
	@GetMapping("/user/username/{username}")
	public User findByUsername(@PathVariable("username") String username) {
		return userService.findByUsername(username);
	}
}
