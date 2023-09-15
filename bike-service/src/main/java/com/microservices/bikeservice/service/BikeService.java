package com.microservices.bikeservice.service;

import java.util.List;

import com.microservices.bikeservice.entity.BikeEntity;

public interface BikeService {
	
	List<BikeEntity> findAll();
	
	BikeEntity findById(Integer id);
	
	BikeEntity save(BikeEntity user);
	
	List<BikeEntity> findByUserId(Integer id);

}
