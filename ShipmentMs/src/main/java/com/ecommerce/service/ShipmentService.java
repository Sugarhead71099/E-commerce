package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Shipment;
import com.ecommerce.repository.ShipmentRepository;

@Service
public class ShipmentService {
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	public Shipment findById(long shipment_id) {
		return shipmentRepository.findById(shipment_id).get();
	}
	
	public Shipment findByOrderId(long order_id) {
		return shipmentRepository.findByOrderId(order_id);
	}
}
