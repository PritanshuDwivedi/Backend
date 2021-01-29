package com.auth.service;

import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/* This is the JwtUtil Service class used to generate token and validate the token */
@Service
@Slf4j
public class JwtUtil {

	private static final String SECRET_KEY = "${secret.key}";

	/* This Method is used to extract the user name from token */
	public String extractUsername(String token) {

		final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		log.debug(claims.getSubject());
		return claims.getSubject();
	}

	/* This Method is used to generate token using SignatureAlgorithm.HS256 */
	public String generateToken(UserDetails userDetails) {

		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))// token for 30 mins
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	/* This method is used to validate the token */
	
	public Boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;

		}
	}
}