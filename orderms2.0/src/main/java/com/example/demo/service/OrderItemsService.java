package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import com.example.demo.model.OrderItems;



public interface OrderItemsService {
List<OrderItems> getAllOrderItems();

void createOrderItems(OrderItems orderItems);

void testThis();

}
