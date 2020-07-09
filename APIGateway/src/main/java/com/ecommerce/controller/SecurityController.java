package com.ecommerce.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.security.JwtSecurity;

@RestController
public class SecurityController {
	
	@Autowired(required = false)
	JwtSecurity jwtSecurity;
	
	@PostMapping("/authenticate")
	public String getAuenticationToken(@PathParam("username") String username, @PathParam("password") String password) {
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			return "{\"result\":\"username and passowrd are required!\"}";
		}
		String token = jwtSecurity.generateToken(username, password);
		if (token == null) {
			return "{\"result\":\"bad username or password\"}";
		} else {
			return "{\"result\":\"token generated\", \"token\":\""+token+"\"}";
		}
	}
}
