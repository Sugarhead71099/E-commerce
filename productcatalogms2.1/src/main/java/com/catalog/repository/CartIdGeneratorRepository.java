package com.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catalog.model.CartIdGenerator;


@Repository
public interface CartIdGeneratorRepository extends JpaRepository<CartIdGenerator, Integer> {

}
