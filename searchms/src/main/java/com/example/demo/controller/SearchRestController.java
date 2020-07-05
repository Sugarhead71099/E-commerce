package com.example.demo.controller;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;

@RestController
public class SearchRestController { 
	// search ms, like product catalog ms, will not a have database,
	// since the data needs to work with will all be received from
	// other microservices
	
	CopyOnWriteArrayList<Product> allProducts = new CopyOnWriteArrayList<Product>(); // a test arraylist
		// which in actuality would be received from my product catalog ms
	
	
	
		@PostMapping(path = "/allProductsSearch/{searchText}") // post a list of products based on search
		public @ResponseBody CopyOnWriteArrayList<Product> savePerson(@PathVariable("searchText") String searchText) {
		    
			// below lines only for testing rest controller
			Product test1 = new Product();
			Product test2 = new Product();
			test1.setName("dali clock");
			test2.setName("boring clock");
			allProducts.add(test1);
			allProducts.add(test2);
			
			
			for(Product p : allProducts)
		    {
		    	// if search text not substring of any of product's string values
		    	if(/*p.getInfo().contains(searchText) ||*/ p.getName().contains(searchText) /*|| String.valueOf(p.getPrice()).contains(searchText)*/ == false)
		    	{
		    		// remove from list of all products (so that when sent back, it will only contain items which match search)
		    		allProducts.remove(p);
		    	}
		    }
		    return allProducts;
		}
}
