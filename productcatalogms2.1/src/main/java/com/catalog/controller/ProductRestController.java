package com.catalog.controller;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.catalog.model.CartIdGenerator;
import com.catalog.model.Product;
import com.catalog.service.CartIdGeneratorService;

import antlr.collections.List;

@RestController
public class ProductRestController {
	// this ms will not keep all products in a datbase, but in arraylist,
	// since the full product database is presumed to be maintained by 
	// the product microservice
	ArrayList<Product> allProducts = new ArrayList<Product>(); //array list of all products. this will get filled when we receive from product ms
	
	
	
	
	// the arraylist of products here would of course need to be received
	// from the product microservice
	@PostMapping(path = "/allProducts") // test works. posts all products to response body
	public @ResponseBody ArrayList<Product>  savePerson() {
	    
		Product p = new Product();
	    p.setName("watch");
	    Product l = new Product();
	    l.setName("watch 2");
	    allProducts.add(p);
	    allProducts.add(l);
	   
	   return allProducts;
	  
	}
	
	// this will be consumed by a rest template on the side of search ms
	@RequestMapping(value = "/sendAllProductsToSearch", method = RequestMethod.GET)
	public ArrayList<Product> firstPage() {

		return allProducts;
	}
	
	
}
