package com.microservices.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.userservice.entity.UserEntity;
import com.microservices.userservice.repositiry.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public List<UserEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public UserEntity findbyId(Integer id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public UserEntity save(UserEntity user) {
		return repository.save(user);
	}

}
