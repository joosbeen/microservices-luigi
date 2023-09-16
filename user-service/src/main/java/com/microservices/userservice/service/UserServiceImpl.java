package com.microservices.userservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.userservice.dto.Bike;
import com.microservices.userservice.dto.Cart;
import com.microservices.userservice.entity.UserEntity;
import com.microservices.userservice.feignclients.BakiFeignClient;
import com.microservices.userservice.feignclients.CartFeignClient;
import com.microservices.userservice.repositiry.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CartFeignClient cartFeignClient;
	@Autowired
	private BakiFeignClient bakiFeignClient;

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

	@Override
	public List<Cart> findCarsByUserId(Integer userId) {
		List<Cart> carts = restTemplate.getForObject("http://localhost:8002/api/v1/carts/user/"+userId, List.class);
		return carts;
	}

	@Override
	public List<Bike> findBikesByUserId(Integer userId) {
		List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/api/v1/bikes/user/"+userId, List.class);
		return bikes;
	}

	@Override
	public Cart save(Cart cart) {
		return cartFeignClient.save(cart);
	}

	@Override
	public Bike save(Bike bike) {
		return bakiFeignClient.save(bike);
	}

	@Override
	public Map<String, Object> findUserVehicles(Integer id) {
		UserEntity entity = repository.findById(id).orElse(null);
		List<Cart> carts = cartFeignClient.findByUserId(id);
		List<Bike> bikes = bakiFeignClient.findByUserId(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", entity);
		map.put("cars", carts);
		map.put("bikes", bikes);
		return map;
	}
	
}
