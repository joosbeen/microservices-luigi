package com.microservices.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservices.authservice.dto.AuthUserDto;
import com.microservices.authservice.dto.TokenDto;
import com.microservices.authservice.entity.AuthUser;
import com.microservices.authservice.repository.AuthUserRepository;
import com.microservices.authservice.security.JwtProvider;

@Service
public class AuthUserService {
	
	@Autowired
	private AuthUserRepository authUserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;

	public AuthUser save(AuthUserDto dto) {
		Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
		if(user.isPresent()) return null;
		String password = passwordEncoder.encode(dto.getPassword());
		AuthUser authUser = new AuthUser();
		authUser.setUserName(dto.getUserName());
		authUser.setPassword(password);
		return authUserRepository.save(authUser);
	}
	
	public TokenDto login(AuthUserDto dto) {
		Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
		if(!user.isPresent()) return null;
		if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) 
			return new TokenDto(jwtProvider.createToken(user.get()));
		return null;
	}
	
	public TokenDto validate(String token) {
		if(!jwtProvider.validate(token)) return null;
		String username = jwtProvider.getUsernameFromToken(token);
		if(!authUserRepository.findByUserName(username).isPresent()) return null;
		return new TokenDto(username);
	}
	
	
}
