package com.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ECOMMERCE_SHIPMENT")
public class Shipment {
	@Id
	@Column(name = "SHIPMENT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long shipmentId;
	@Column(name = "ORDER_ID")
	private long orderId;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "SHIPPED_DATE")
	private String shippedDate;
	@Column(name = "DELIVERED_DATE")
	private String deliveredDate;
	
	public long getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(long shipmentId) {
		this.shipmentId = shipmentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(String shippedDate) {
		this.shippedDate = shippedDate;
	}
	public String getDeliveredDate() {
		return deliveredDate;
	}
	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	@Override
	public String toString() {
		return "Shipment [shipmentId=" + shipmentId + ", orderId=" + orderId + ", status=" + status + ", shippedDate="
				+ shippedDate + ", deliveredDate=" + deliveredDate + "]";
	}

}
