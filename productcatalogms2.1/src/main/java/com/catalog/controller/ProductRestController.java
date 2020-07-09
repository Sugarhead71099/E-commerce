package com.catalog.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.catalog.model.CartIdGenerator;
import com.catalog.model.Product;
import com.catalog.service.CartIdGeneratorService;



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
	
	// receives a list of all products
	@RequestMapping(value = "/allAvailableProducts", method = RequestMethod.GET)
	public List<Product> receiveProducts() {

		String urlOfReceipt = "http://localhost:8010/productms/products";
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			ResponseEntity<List<Product>> response = restTemplate.exchange(
					urlOfReceipt, 
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<Product>>() {});
			if(response != null && response.hasBody()){
				allProducts = (ArrayList<Product>) response.getBody(); //make session attribute, possibly
				System.out.println("supposedly received");
			}
		} catch (RestClientException e) {
			
			e.printStackTrace();
		} 
		
		return allProducts;
	}
	
}
