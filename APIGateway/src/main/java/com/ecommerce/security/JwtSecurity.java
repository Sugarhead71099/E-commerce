package com.ecommerce.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ecommerce.beans.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtSecurity {
	
	@Value("{secret}")
	String secret;
	@Autowired
	UserMicroserviceProxy userMicroserviceProxy;
	
	public String generateToken(String username, String password) {
		User user = userMicroserviceProxy.findByUsername(username);
		if (user == null || !password.equals(user.getPassword())) {
			return null;
		}
		JwtBuilder builder = Jwts.builder()
				.setId(String.valueOf(user.getUserId()))
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, secret);
		String token = builder.compact();
		System.out.println(token);
		return token;
	}
	
	public Long parseTokenGetId(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
		
		long user_id = Long.valueOf(claims.getId());
		return user_id;
	}
}
