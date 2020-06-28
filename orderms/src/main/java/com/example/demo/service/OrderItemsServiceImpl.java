package com.example.demo.service;

import java.util.List;

import com.example.demo.model.OrderItems;
import com.example.demo.repository.OrderItemsRepository;

public class OrderItemsServiceImpl implements OrderItemsService {

	private OrderItemsRepository orderItemsRepository;
	
	@Override
	public List<OrderItems> getAllOrderItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createOrderItems(OrderItems orderItems) {
		
		orderItemsRepository.save(orderItems);
	}

}
