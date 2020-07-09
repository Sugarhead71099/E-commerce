package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.OrderItems;



public interface OrderItemsService {
List<OrderItems> getAllOrderItems();

void createOrderItems(OrderItems orderItems);

public boolean dupCustCartProduct(int productId, int cartId, String username );

}
