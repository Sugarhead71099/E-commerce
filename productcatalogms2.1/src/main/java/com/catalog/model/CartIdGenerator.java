package com.catalog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CartIdGenerator {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	int uniqueCartId;
	

	public int getUniqueCartId() {
		return uniqueCartId;
	}

	public void setUniqueCartId(int uniqueCartId) {
		this.uniqueCartId = uniqueCartId;
	}
	
	
}
