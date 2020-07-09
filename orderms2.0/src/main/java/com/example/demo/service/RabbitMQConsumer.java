package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.model.OrderItems;
import com.example.demo.model.Product;

import com.example.demo.model.UtilClass;



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
		
		Product pToAdd = new Product();
		pToAdd.setProductId(Integer.parseInt(parts[1]));
		pToAdd.setName(parts[2]);
		pToAdd.setPrice(Integer.parseInt(parts[3]));
		toAdd.setQuantity(Integer.parseInt(parts[4]));
		toAdd.setProduct(pToAdd); // add the product to the order line item
		toAdd.setOrderNum(Integer.parseInt(parts[5])); // the unique cart id 
		UtilClass.setCurrentCartId(Integer.parseInt(parts[5])); // set unique cart id in util class
		
		int productId = Integer.parseInt(parts[1]);
		int cartId = Integer.parseInt(parts[5]);
		String username = parts[0];
		if(orderItemsService.dupCustCartProduct(productId, cartId, username)) // if user already has product in cart, don't create again
		{
			System.out.println("there is a duplicate record with " + productId + " " + cartId + " "+ username);
		}
		else
		{
			System.out.println("no duplicate record");
			orderItemsService.createOrderItems(toAdd); // save the newly associated data to database
			System.out.println("saved to database");
		}
		
		
		
		
		
		
	}
	
	

}
