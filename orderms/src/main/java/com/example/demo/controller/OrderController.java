package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.OrderItems;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.OrderItemsService;

@Controller
public class OrderController {
	// this ms will need to receive a user and a product and a quantity, then
	// save that as an order items object. it has a database of all orderitems
	
	// it should also be able to send all orderitems in the cart of the current user
	// to the cart service 
	
	OrderItemsService orderItemsService;
	static int orderItemId = 1;
	
	// dummies for test
	User receivedUser = new User();
	Product receivedProduct = new Product();
	int receivedQuantity = 5;
	
	
	
	// controller to add new order item to the database
	@RequestMapping("/addItem")
	public void addItem()
	{
		// elaborating on dummies
		receivedUser.setAddress("a");
		receivedUser.setEmail("a");
		receivedUser.setPassword("hi");
		receivedUser.setPhone(123);
		receivedUser.setUserId(89);
		receivedUser.setUsername("a");
		receivedProduct.setInfo("hi");
		receivedProduct.setName("name");
		receivedProduct.setPrice(3);
		receivedProduct.setProductId(1);
		
		
		OrderItems toAdd = new OrderItems();
		toAdd.setProduct(receivedProduct);
		toAdd.setUser(receivedUser);
		toAdd.setQuantity(receivedQuantity);
		toAdd.setOrderNum(1);
		toAdd.setOrderId(orderItemId);
		orderItemId++;
		//orderItemsService.createOrderItems(toAdd); throwing null ptr exception
	}
	
}
