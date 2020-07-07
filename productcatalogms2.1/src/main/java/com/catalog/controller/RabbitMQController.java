package com.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catalog.model.CartIdGenerator;
import com.catalog.model.User;
import com.catalog.service.CartIdGeneratorService;
import com.catalog.service.RabbitMQSender;


@RestController
//@RequestMapping(value="/rabbit")
public class RabbitMQController {

	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@Autowired
	CartIdGeneratorService cartIdGeneratorService;
	
	User activeUser = null;

	
	int cartId = -1; // this is a dummy cart id, which will actually be a unique number
	// gen'd upon receiving joseph's new active user. we can achieve a unique cart id 
	// each time we receive this by creating an dummy cart objectand storing in a database,
	// then we can use the @Id it generates for us as a property of that OrderItem.
	//  it goes without saying that wehen we send all orderitems in the cart to the cart ms,
	//   we can simply send those which have a cartId property equal to cartId
	
	// this should be called when add to cart button is clicked. 	
	@GetMapping(value="/sendUserAndProductToOrderMS") //example of a working url for this: http://localhost:8005/sendUserAndProductToOrderMS/?username=waldo&productId=2&name=toy%20dinosaur&price=9&quantity=2
	public String producer(@RequestParam("username") String username, @RequestParam("productId") String productId,
			@RequestParam("name") String name, @RequestParam("price") String price, @RequestParam("quantity") String quantity) {
		
		
		//System.out.println("enter rabbit send controller");
		
		String userProductStr = new String();
		userProductStr += /*username*/ activeUser.getUsername();
		userProductStr += "," + productId; // comma is delimiter
		userProductStr += "," + name;
		userProductStr += "," + price; //productId, name, and price are attributes of product
		userProductStr += "," + quantity;
		userProductStr += "," + cartId; // also send the cart this orderitem belongs to
			
		rabbitMQSender.send(userProductStr); 
		
		return "Message has been sent to the queue";
	}
	
	
	// receive the active user using resttemplate
		@RequestMapping(value = "/receivingUser"/*, method = RequestMethod.GET*/)
		public void genCartForUser() {

			// add code here to receive the active user using a resttemplate
			User user = new User(); // this is dummy for use until resttemplate added
			
			// generate a new cart id for the active user
			CartIdGenerator cGen = new CartIdGenerator();
			cartId = cartIdGeneratorService.createCartIdGenerator(cGen);
			System.out.println("cart id gen supposedly saved to db");
			System.out.println("cart id is " + cartId);
			
		}
}
