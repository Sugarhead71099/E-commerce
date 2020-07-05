package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.model.OrderItems;
import com.example.demo.model.Product;
import com.example.demo.model.User;



@Component
public class RabbitMQConsumer {
	
	@Autowired
	OrderItemsService orderItemsService;
	
	
	@RabbitListener(queues="${as.rabbitmq.queue.userproduct}") //executes by default
	public void receivedMessage(String userProductStr) {
		
		
		System.out.println("Listener Received String  " + userProductStr);
		
		//parse the incoming string for data on product and user
		String[] parts = userProductStr.split(","); // split by comma delimiter
		
		OrderItems toAdd = new OrderItems();
		User uToAdd = new User();
		uToAdd.setUsername(parts[0]);
		toAdd.setUser(uToAdd); // add the user to the order line item
		Product pToAdd = new Product();
		pToAdd.setProductId(Integer.parseInt(parts[1]));
		pToAdd.setName(parts[2]);
		pToAdd.setPrice(Integer.parseInt(parts[3]));
		toAdd.setQuantity(Integer.parseInt(parts[4]));
		toAdd.setProduct(pToAdd); // add the product to the order line item
		
		//set dummies since null values not allowed and we don't need to know this information
		
		orderItemsService.createOrderItems(toAdd); // save the newly associated data to database
		
		System.out.println("saved to database");
	}
	

}
