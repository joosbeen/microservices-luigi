package com.microservices.userservice.service;

import java.util.List;

import com.microservices.userservice.dto.Bike;
import com.microservices.userservice.dto.Cart;
import com.microservices.userservice.entity.UserEntity;

public interface UserService {
	
	List<UserEntity> findAll();
	
	UserEntity findbyId(Integer id);
	
	UserEntity save(UserEntity user);
	
	List<Cart> findCarsByUserId(Integer userId);
	
	List<Bike> findBikesByUserId(Integer userId);

}
