package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.OrderItems;
import com.example.demo.repository.OrderItemsRepository;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

	@Autowired
	private OrderItemsRepository orderItemsRepository;
	
	@Override
	public List<OrderItems> getAllOrderItems() {
		return orderItemsRepository.findAll();
	}
	
	// checks if the customer already has the item in the cart
	@Override
	public boolean dupCustCartProduct(int productId, int cartId, String u/*sername*/ )
	{
		boolean result = false; // set to false unless we find it
		
		
		
		List<OrderItems> allItems = orderItemsRepository.findAll();
		allItems.stream()/*.forEach(obj -> System.out.println("objects within objects:" +obj.getUser().getUsername() + obj.getProduct().getProductId()));*/
		.filter(obj -> 
		
		
		(obj.getOrderNum() == cartId && obj.getProduct().getProductId() == productId /*&& obj.getUser().getUsername() == username*/));
		
		if (!allItems.isEmpty())
			result = true;
		
		return result;
	}

	@Override
	public void createOrderItems(OrderItems orderItems) {
		
		orderItemsRepository.save(orderItems);
		
		
	}
	


	

}
