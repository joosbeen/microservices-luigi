package com.microservices.bikeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.bikeservice.entity.BikeEntity;

public interface BikeRepository extends JpaRepository<BikeEntity, Integer>{
	
	List<BikeEntity> findByUserId(Integer id);

}
