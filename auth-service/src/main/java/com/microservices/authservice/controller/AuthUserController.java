package com.microservices.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.authservice.dto.AuthUserDto;
import com.microservices.authservice.dto.TokenDto;
import com.microservices.authservice.entity.AuthUser;
import com.microservices.authservice.service.AuthUserService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthUserController {
	
	@Autowired
	private AuthUserService authUserService;

	@PostMapping("/login")
	private ResponseEntity<TokenDto> login(@RequestBody AuthUserDto dto) {
		TokenDto tokenDto = authUserService.login(dto);
		if(tokenDto==null) return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(tokenDto);
	}

	@PostMapping("/validate")
	private ResponseEntity<TokenDto> validate(@RequestParam String token) {
		TokenDto tokenDto = authUserService.validate(token);
		if(tokenDto==null) return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(tokenDto);		
	}
	
	@PostMapping("/register")
	private ResponseEntity<AuthUser> create(@RequestBody AuthUserDto dto) {
		AuthUser authUser = authUserService.save(dto);
		if(authUser==null) return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(authUser);				
	}

}
