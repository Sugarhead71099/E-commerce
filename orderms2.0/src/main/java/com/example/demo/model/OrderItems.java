
package com.example.demo.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.Nullable;

@Entity
public class OrderItems {

	@Id
	int orderId; // a unique item in an order 
	@Nullable
	int orderNum; // order items with the same orderNum are in the same cart

	@Embedded
	User user;

	@Embedded
	Product product;
	int quantity;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderItems [orderId=" + orderId + ", orderNum=" + orderNum + ", user=" + user + ", product=" + product
				+ ", quantity=" + quantity + "]";
	}
	
	
}
