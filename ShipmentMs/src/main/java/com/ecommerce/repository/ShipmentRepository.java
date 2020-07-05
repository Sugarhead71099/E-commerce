package com.ecommerce.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Shipment;

@Repository
@Transactional
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
	public abstract Shipment findByOrderId(long order_id);
}
