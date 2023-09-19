package com.microservices.userservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.userservice.dto.Bike;
import com.microservices.userservice.dto.Cart;
import com.microservices.userservice.entity.UserEntity;
import com.microservices.userservice.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<UserEntity> userEntities = userService.findAll();
		if (userEntities.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(userEntities);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
		UserEntity userEntity = userService.findbyId(id);
		if (userEntity == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(userEntity);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserEntity user) {

		UserEntity entity = userService.save(user);
		if (entity == null)
			return ResponseEntity.internalServerError().build();
		return ResponseEntity.ok(entity);
	}

	@CircuitBreaker(name = "cartsCB", fallbackMethod = "fallbackSaveCart")
	@PostMapping("carts/{userId}")
	public ResponseEntity<Cart> saveCart(@PathVariable(name = "userId") Integer userId, @RequestBody Cart cart) {
		UserEntity userEntity = userService.findbyId(userId);
		if (userEntity == null)
			return ResponseEntity.notFound().build();
		cart.setUserId(userId);
		cart = userService.save(cart);
		return ResponseEntity.ok(cart);
	}

	@CircuitBreaker(name = "cartsCB", fallbackMethod = "fallbackFindCarsByUserId")
	@GetMapping("/carts/{userId}")
	public ResponseEntity<?> findCarsByUserId(@PathVariable(name = "userId") Integer userId) {
		UserEntity userEntity = userService.findbyId(userId);
		if (userEntity == null)
			return ResponseEntity.notFound().build();
		List<Cart> carts = userService.findCarsByUserId(userId);
		return ResponseEntity.ok(carts);
	}

	@CircuitBreaker(name = "bikesCB", fallbackMethod = "fallbackSaveBike")
	@PostMapping("bikes/{userId}")
	public ResponseEntity<Bike> saveBike(@PathVariable(name = "userId") Integer userId, @RequestBody Bike bike) {
		UserEntity userEntity = userService.findbyId(userId);
		if (userEntity == null)
			return ResponseEntity.notFound().build();
		bike.setUserId(userId);
		bike = userService.save(bike);
		return ResponseEntity.ok(bike);
	}

	@CircuitBreaker(name = "bikesCB", fallbackMethod = "fallbackFindBikesByUserId")
	@GetMapping("/bikes/{userId}")
	public ResponseEntity<?> findBikesByUserId(@PathVariable(name = "userId") Integer userId) {
		UserEntity userEntity = userService.findbyId(userId);
		if (userEntity == null)
			return ResponseEntity.notFound().build();
		List<Bike> bikes = userService.findBikesByUserId(userId);
		return ResponseEntity.ok(bikes);
	}

	@CircuitBreaker(name = "vehiclesCB", fallbackMethod = "fallbackFindVehicles")
	@GetMapping("vehicles/{userId}")
	public ResponseEntity<Map<String, Object>> findVehicles(@PathVariable(name = "userId") Integer userId) {
		UserEntity userEntity = userService.findbyId(userId);
		if (userEntity == null)
			return ResponseEntity.notFound().build();
		Map<String, Object> map = userService.findUserVehicles(userId);
		return ResponseEntity.ok(map);
	}
	
	private ResponseEntity<Cart> fallbackSaveCart(@PathVariable(name = "userId") Integer userId, @RequestBody Cart cart) {
		return new ResponseEntity("Error al guardar el auto.", HttpStatus.OK);
	}
	
	private ResponseEntity<?> fallbackFindCarsByUserId(@PathVariable(name = "userId") Integer userId) {
		return new ResponseEntity("Error al buscar los autos del usuario.", HttpStatus.OK);
	}

	private ResponseEntity<Bike> fallbackSaveBike(@PathVariable(name = "userId") Integer userId, @RequestBody Bike bike) {
		return new ResponseEntity("Error al guardar la motocicleta.", HttpStatus.OK);
	}
	
	public ResponseEntity<?> fallbackFindBikesByUserId(@PathVariable(name = "userId") Integer userId) {
		return new ResponseEntity("Error al buscar las motocicletas del usuario.", HttpStatus.OK);
	}

}
