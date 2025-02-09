package com.rohan.expense_tracker.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtility {
	
	private final String keyString = "suvIEAeK6SxC23FeRfFJarRSpCzHhO+kyWSVINT+U5k=";
	private final Long expirationTime = 1000l * 60l * 20l;
	
	// Generate token
	
	public String createToken(Map<String, Object> claims, String subject) {
		
		String jwtToken = Jwts.builder()
							.claims(claims)
							.subject(subject)
							.header().empty().add("typ", "JWT")
							.and()
							.issuedAt(new Date(System.currentTimeMillis()))
							.expiration(new Date(System.currentTimeMillis() + expirationTime))
							.signWith(getSigningKey())
							.compact();
		
		return jwtToken;
		
	}
	
	public String generateToken(String username) {
		
		Map<String, Object> claims = new HashMap<>();
		
		return createToken(claims, username);
		
	}
	
	
	
	// Extracting data from token
	
	private Claims extractAllClaims(String token) {
		
		final Claims claims = Jwts.parser()
								.verifyWith(getSigningKey())
								.build()
								.parseSignedClaims(token)
								.getPayload();
		
		return claims;
		
	}
	
	private SecretKey getSigningKey() {
		
		return Keys.hmacShaKeyFor(keyString.getBytes());
		
	}
	
	public String extractUsername(String token) {
		
		return extractAllClaims(token).getSubject();
		
	}
	
	public Date extractExpiration(String token) {
		
		return extractAllClaims(token).getExpiration();
		
	}
	
	public Boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
		
	}
	
	public Boolean validateToken(String token) {
		
		return !isTokenExpired(token);
		
	}
	
}
