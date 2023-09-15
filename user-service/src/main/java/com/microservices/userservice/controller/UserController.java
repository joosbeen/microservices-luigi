package com.microservices.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.userservice.entity.UserEntity;
import com.microservices.userservice.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<UserEntity> userEntities = userService.findAll();
		if(userEntities.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(userEntities);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
		UserEntity userEntity = userService.findbyId(id);
		if(userEntity==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(userEntity);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserEntity user) {
		
		UserEntity entity = userService.save(user);
		if(entity==null)
			return ResponseEntity.internalServerError().build();
		return ResponseEntity.ok(entity);
		
	}
	
	

}
