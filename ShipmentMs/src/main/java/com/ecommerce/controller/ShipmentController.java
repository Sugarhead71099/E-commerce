package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Shipment;
import com.ecommerce.service.ShipmentService;

@RestController
public class ShipmentController {
	
	@Autowired
	private ShipmentService shipmentService;
	
	@GetMapping("/shipment/{shipment_id}")
	public Shipment findById(@PathVariable("shipment_id") long shipment_id) {
		return shipmentService.findById(shipment_id);
	}
	
	@GetMapping("/shipment/orderid/{order_id}")
	public Shipment findByOrderId(@PathVariable("order_id") long order_id) {
		return shipmentService.findByOrderId(order_id);
	}
}
