package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Product;
import com.example.demo.model.ProductList;

@RestController
public class SearchRestController { 
	// search ms, like product catalog ms, will not a have database,
	// since the data needs to work with will all be received from
	// other microservices
	
	CopyOnWriteArrayList<Product> allProducts = new CopyOnWriteArrayList<Product>(); // a test arraylist
		// which in actuality would be received from my product catalog ms
	List<Product> allProductsArLst = new ArrayList<Product>(); // a list of all products received from product catalog ms
	static CopyOnWriteArrayList<Product> forTesting = new CopyOnWriteArrayList<Product>();
	
	
	
	
	
	// a controller to receive the list of all products from product catalog ms. We can filter these later based on search criteria
	@RequestMapping(value = "/allProducts", method = RequestMethod.GET)
	public List<Product> firstPage() {

		String urlOfReceipt = "http://localhost:8005/allProducts";
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			ResponseEntity<List<Product>> response = restTemplate.exchange(
					urlOfReceipt, 
					HttpMethod.POST,
					null,
					new ParameterizedTypeReference<List<Product>>() {});
			if(response != null && response.hasBody()){
				allProductsArLst = response.getBody();
			}
		} catch (RestClientException e) {
			
			e.printStackTrace();
		} 

		
		return allProductsArLst;
		
		
	}
	
	
		@PostMapping(path = "/allProductsSearch/{searchText}") // post a list of products based on search
		public @ResponseBody CopyOnWriteArrayList<Product> filteredProducts(@PathVariable("searchText") String searchText) {
		    
							
			allProductsArLst.stream().forEach(prod -> allProducts.add(prod)); // add list of all products to  copy on write arraylist
			
			this.addToTestArray();// comment this out when not test
			allProducts = forTesting; //comment this out when not testing
			for(Product p : allProducts) // refine products based on search
		    {
		    	System.out.println("descrip -  " + p.getInfo());
				// if search text not substring of any of product's string values
		    	if(!(p.getInfo().contains(searchText)) && (!(p.getName().contains(searchText))) /*&& (StringUtils.isNumeric(searchText) && !( p.getPrice()==Integer.parseInt(searchText)))*/)
		    	{
		    		// remove from list of all products (so that when sent back, it will only contain items which match search)
		    		allProducts.remove(p);
		    	}
		    }
			/*for(Product p : allProducts) // comment this loop out when not using for test
			{
				System.out.println("search contains: " + p);
			}*/
		    return allProducts; // return products filtered according to search criteria
		}
		
		// this is a method usefel in consuming via resttemplate
		private static HttpEntity<?> getHeaders() throws IOException {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			return new HttpEntity<>(headers);
		}
		
		public static void addToTestArray()
		{
			forTesting.add(new Product("clock", " a specialty clock", 2));
			forTesting.add(new Product("rocketship", " really fast", 20));
		}
		
}
