package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class UtilClass { // performs some utilities for exchaning info b/t classes in the microservice
	static int currentCart;
	
	public static int getCurrentCartId()
	{
		
		//System.out.println("current cart " + currentCart);
		return currentCart;
	}
	
	public static void setCurrentCartId(int num)
	{
		currentCart = num;
	}
	
}
