package com.microservices.authservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 1.- COnfigurar el codificador de password
 * @author Jose
 *
 */
@Configuration
public class PasswordEncoderConfig {
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
