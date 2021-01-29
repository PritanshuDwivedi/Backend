package com.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth.exception.UserNotFoundException;
import com.auth.model.UserEntity;
import com.auth.model.UserLoginCredential;
import com.auth.reposiroty.UserRepository;

@SpringBootTest
class UserServiceTest {

	@InjectMocks
	UserService service;

	@Mock
	JwtUtil util;

	@Mock
	CustomerDetailsService custService;

	@Mock
	UserRepository repo;

	public static final String TOKEN = "token";

	UserEntity user;
	UserLoginCredential credentials;

	@BeforeEach
	void serUp() {
		user = new UserEntity("name", "pass", "admin");
		credentials = new UserLoginCredential("name", "pass");
	}

	@Test
	void loginTest() throws UserNotFoundException {
		UserDetails value = new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		Mockito.when(repo.findByUsernameAndPassword("name", "pass")).thenReturn(user);
		Mockito.when(custService.loadUserByUsername("name")).thenReturn(value);
		Mockito.when(util.generateToken(value)).thenReturn(TOKEN);
		assertEquals(TOKEN, service.login(credentials).getAuthToken());
	}

	@Test
	void loginFailTest() throws UserNotFoundException {
		Mockito.when(repo.findByUsernameAndPassword("name", "pass")).thenReturn(null);
		assertThrows(UserNotFoundException.class, () -> service.login(credentials));
	}

	@Test
	void validateTest() {
		Mockito.when(util.extractUsername(TOKEN)).thenReturn("name");
		Mockito.when(repo.findById("name")).thenReturn(Optional.of(user));
		assertNotNull(service.validate("Bearer " + TOKEN));
	}

}
