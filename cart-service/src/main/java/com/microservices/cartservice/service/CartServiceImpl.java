package com.microservices.cartservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.cartservice.entity.CartEntity;
import com.microservices.cartservice.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository repository;

	@Override
	public List<CartEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public CartEntity findById(Integer id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public CartEntity save(CartEntity user) {
		return repository.save(user);
	}

	@Override
	public List<CartEntity> findByUserId(Integer id) {
		return repository.findByUserId(id);
	}
	
}
