package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.OrderItems;
import com.example.demo.model.Product;

import com.example.demo.model.UtilClass;
import com.example.demo.service.OrderItemsService;
import com.example.demo.service.RabbitMQConsumer;

@Controller
public class OrderController {
	// this ms will need to receive a user and a product and a quantity, then
	// save that as an order items object. it has a database of all orderitems
	// its RabbitMQConsumer service does some passive listening action
	
	@Autowired
	OrderItemsService orderItemsService;
	
	
	
	//for when the user wants to go to cart and view all items in their order. then we send
	// items to cart ms using resttemplate
	@RequestMapping("/goToCart")
	public @ResponseBody List<OrderItems>  goToCartMS() {
	    List<OrderItems> parentType = orderItemsService.getAllOrderItems();
		return   parentType.stream().filter(item -> item.getOrderNum() == UtilClass.getCurrentCartId()).collect(Collectors.toList());
	 
	}
	
	
}
