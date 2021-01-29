package com.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth.reposiroty.UserRepository;

import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;

@SpringBootTest
public class JwtUtilTest {

	@InjectMocks
	JwtUtil jwtUtil;

	UserDetails userdetails;

	@Mock
	UserRepository userservice;

	@Mock
	Claims claim;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		userdetails = new User("admin", "admin", new ArrayList<>());
	}

	@Test
	public void generateTokenTest() {
		String generateToken = jwtUtil.generateToken(userdetails);
		assertNotNull(generateToken);
	}

	@Test
	public void validateTokenTest() {
		String generateToken = jwtUtil.generateToken(userdetails);
		Boolean validateToken = jwtUtil.validateToken(generateToken);
		assertEquals(true, validateToken);
	}

	@Test
	public void validateTokenWithNameTest() {
		String generateToken = jwtUtil.generateToken(userdetails);
		Boolean validateToken = jwtUtil.validateToken(generateToken);
		assertEquals(true, validateToken);
	}

	@Test
	public void extractUserNameTest() {
		String generateToken = jwtUtil.generateToken(userdetails);
		assertEquals("admin", jwtUtil.extractUsername(generateToken));

	}

}