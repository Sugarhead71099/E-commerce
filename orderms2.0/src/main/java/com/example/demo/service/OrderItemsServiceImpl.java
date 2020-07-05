package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OrderItems;
import com.example.demo.repository.OrderItemsRepository;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

	@Autowired
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
	
	@Override
	public void testThis()
	{
		
	}

}
