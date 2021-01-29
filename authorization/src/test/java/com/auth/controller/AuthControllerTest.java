package com.auth.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.auth.model.AuthResponse;
import com.auth.model.UserLoginCredential;
import com.auth.model.UserToken;
import com.auth.service.UserService;

@SpringBootTest
class AuthControllerTest {

	@InjectMocks
	AuthController controller;

	@Mock
	UserService service;

	@Mock
	UserLoginCredential user;

	@Test
	void healthTest() {
		assertEquals(200, controller.check());
	}

	@Test
	void loginTest() throws Exception {
		user = new UserLoginCredential("admin", "pass");
		Mockito.when(service.login(user)).thenReturn(new UserToken());
		assertEquals(HttpStatus.OK, controller.login(user).getStatusCode());
	}

	@Test
	void validateTest() {
		String token = "token";
		Mockito.when(service.validate(token)).thenReturn(new AuthResponse());
		assertEquals(HttpStatus.OK, controller.getValidity(token).getStatusCode());
	}
}
