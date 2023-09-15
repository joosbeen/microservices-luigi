package com.microservices.bikeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.bikeservice.entity.BikeEntity;
import com.microservices.bikeservice.service.BikeService;


@RestController
@RequestMapping("/api/v1/bikes")
public class BikeController {
	
	@Autowired
	private BikeService cartService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<BikeEntity> userEntities = cartService.findAll();
		if(userEntities.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(userEntities);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
		BikeEntity CartEntity = cartService.findById(id);
		if(CartEntity==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(CartEntity);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody BikeEntity user) {
		
		BikeEntity entity = cartService.save(user);
		if(entity==null)
			return ResponseEntity.internalServerError().build();
		return ResponseEntity.ok(entity);
		
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> findByUserId(@PathVariable(name = "id") Integer id) {
		List<BikeEntity> cartEntities = cartService.findByUserId(id);
		if(cartEntities.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(cartEntities);
	}
	
	

}
