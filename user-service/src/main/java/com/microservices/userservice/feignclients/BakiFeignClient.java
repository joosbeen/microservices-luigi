package com.microservices.userservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.userservice.dto.Bike;


@FeignClient(name = "bike-service", path = "/api/v1/bikes", url = "http://localhost:8003")
public interface BakiFeignClient {

	@PostMapping()
	Bike save(@RequestBody Bike bike);
	
	@GetMapping("/user/{id}")
	List<Bike> findByUserId(@PathVariable(name = "id") Integer id);

}
