package com.microservices.bikeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.bikeservice.entity.BikeEntity;
import com.microservices.bikeservice.repository.BikeRepository;

@Service
public class BikeServiceImpl implements BikeService {
	
	@Autowired
	private BikeRepository repository;

	@Override
	public List<BikeEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public BikeEntity findById(Integer id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public BikeEntity save(BikeEntity user) {
		return repository.save(user);
	}

	@Override
	public List<BikeEntity> findByUserId(Integer id) {
		return repository.findByUserId(id);
	}	

}
