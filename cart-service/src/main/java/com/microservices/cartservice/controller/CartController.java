package com.microservices.cartservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.cartservice.entity.CartEntity;
import com.microservices.cartservice.service.CartService;


@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<CartEntity> userEntities = cartService.findAll();
		if(userEntities.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(userEntities);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
		CartEntity CartEntity = cartService.findById(id);
		if(CartEntity==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(CartEntity);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody CartEntity user) {
		
		CartEntity entity = cartService.save(user);
		if(entity==null)
			return ResponseEntity.internalServerError().build();
		return ResponseEntity.ok(entity);
		
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> findByUserId(@PathVariable(name = "id") Integer id) {
		List<CartEntity> cartEntities = cartService.findByUserId(id);
		if(cartEntities.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(cartEntities);
	}
	
	

}
