package com.ecommerce.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
	public abstract Product findByName(String name);
}
