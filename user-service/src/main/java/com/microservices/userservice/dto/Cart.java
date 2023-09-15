package com.microservices.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cart {
	private Integer id;
	private String brand;
	private String model;
	private Integer userId;
}
