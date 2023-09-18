package com.microservices.cartservice.service;

import java.util.List;

import com.microservices.cartservice.entity.CartEntity;

public interface CartService {
	
	List<CartEntity> findAll();
	
	CartEntity findById(Integer id);
	
	CartEntity save(CartEntity user);
	
	List<CartEntity> findByUserId(Integer id);

}
