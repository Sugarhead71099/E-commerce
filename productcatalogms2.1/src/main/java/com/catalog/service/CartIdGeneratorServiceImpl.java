package com.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalog.model.CartIdGenerator;
import com.catalog.repository.CartIdGeneratorRepository;

@Service
public class CartIdGeneratorServiceImpl implements CartIdGeneratorService {

	@Autowired
	private CartIdGeneratorRepository cartIdGeneratorRepository;

	@Override
	public int createCartIdGenerator(CartIdGenerator cartIdGenerator) {
		cartIdGeneratorRepository.save(cartIdGenerator);
		return cartIdGenerator.getUniqueCartId();
		
	}
	
	
}
