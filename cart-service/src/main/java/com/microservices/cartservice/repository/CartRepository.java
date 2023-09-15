package com.microservices.cartservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.cartservice.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Integer>{
	
	List<CartEntity> findByUserId(Integer id);

}
