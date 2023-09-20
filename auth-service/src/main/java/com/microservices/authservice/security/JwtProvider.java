package com.microservices.authservice.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microservices.authservice.entity.AuthUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 2.- Generardor de token
 * 
 * @author Jose
 *
 */
@Component
public class JwtProvider {

	@Value("${jwt.secret}")
	private String secret;

	@PostConstruct
	protected void init() {
		secret = Base64.getEncoder().encodeToString(secret.getBytes());
	}

	/**
	 * Generar el token
	 * 
	 * @param authUser
	 * @return String
	 */
	public String createToken(AuthUser authUser) {
		Map<String, Object> claims = new HashMap<>();
		claims = Jwts.claims().setSubject(authUser.getUserName());
		claims.put("id", authUser.getId());
		Date now = new Date();
		Date exp = new Date(now.getTime() + 36000000);
		return Jwts.builder() // construir token
				.setClaims(claims).setIssuedAt(now).setExpiration(exp).signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	/**
	 * Validar token
	 * 
	 * @param token String
	 * @return switch Boolean
	 */
	public boolean validate(String token) {
		try {
			Jwts.parser() // decodificar el token
					.setSigningKey(secret).parseClaimsJws(token);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Obtener el username del token
	 * @param token String 
	 * @return username String token valido, null tokern invalido.
	 */
	public String getUsernameFromToken(String token) {
		try {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		} catch (Exception e) {
			return null;
		}
	}

}
