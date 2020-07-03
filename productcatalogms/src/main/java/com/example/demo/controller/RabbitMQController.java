package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.service.RabbitMQSender;


@RestController
@RequestMapping(value="/rabbit")
public class RabbitMQController {

	@Autowired
	RabbitMQSender rabbitMQSender;
	
	// this should be called when add to cart button is clicked. 
		// (it sends user and product to order ms so it can associate the two)
	//example of a working url for this: http://localhost:8005/rabbit/sendUserAndProductToOrderMS/?username=waldo&productId=2&name=toy%20dinosaur&price=9&quantity=2
	@GetMapping(value="/sendUserAndProductToOrderMS")
	public String producer(@RequestParam("username") String username, @RequestParam("productId") String productId,
			@RequestParam("name") String name, @RequestParam("price") String price, @RequestParam("quantity") String quantity) {
		
		String userProductStr = new String();
		userProductStr += username;
		userProductStr += "," + productId; // comma is delimiter
		userProductStr += "," + name;
		userProductStr += "," + price; //productId, name, and price are attributes of product
		userProductStr += "," + quantity;
			
		rabbitMQSender.send(userProductStr); 
		
		return "Message has been sent to the queue";
	}
	
}
