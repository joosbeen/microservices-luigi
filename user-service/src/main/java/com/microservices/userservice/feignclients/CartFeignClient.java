package com.microservices.userservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.userservice.dto.Cart;


@FeignClient(name = "cart-service", path = "/api/v1/carts", url = "http://localhost:8002")
//@RequestMapping("/api/v1/carts")
public interface CartFeignClient {
	
	@PostMapping()
	Cart save(@RequestBody Cart cart);
	
	@GetMapping("/user/{id}")
	List<Cart> findByUserId(@PathVariable(name = "id") Integer id);
		

}
