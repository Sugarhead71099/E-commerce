package com.example.demo.controller;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;

import antlr.collections.List;

@RestController
public class ProductRestController {
	// this ms will not keep all products in a datbase, but in arraylist,
	// since the full product database is presumed to be maintained by 
	// the product microservice

	
	
	// the arraylist of products here would of course need to be received
	// from the product microservice
	@PostMapping(path = "/allProducts") // test works
	public @ResponseBody ArrayList<Product> savePerson() {
	    Product p = new Product();
	    p.setName("watch");
	    Product l = new Product();
	    l.setName("watch 2");
	    ArrayList<Product> al = new ArrayList<Product>();
	    al.add(p);
	    al.add(l);
	    return al;
	}
}
