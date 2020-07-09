package com.ecommerce.security;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.beans.User;

@FeignClient("userms")
public interface UserMicroserviceProxy {

	@GetMapping("/user/username/{username}")
	public User findByUsername(@PathVariable("username") String username);
}