package com.loan.LoanManagement.modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.loan.LoanManagement.model.AuthResponse;

@SpringBootTest
public class AuthResponseTest {

	@InjectMocks
	AuthResponse authResponse;

	@Test
	public void authGetterSetterMethodTest() {
		authResponse = new AuthResponse();

		authResponse.setUsername("admin");
		authResponse.setRole("admin");
		authResponse.setValid(true);

		assertEquals("admin", authResponse.getUsername());
		assertEquals("admin", authResponse.getRole());
		assertEquals(true, authResponse.isValid());
	}

	@Test
	public void constructorTest() {
		authResponse = new AuthResponse();
		assertNotNull(authResponse);
		assertNotNull(new AuthResponse("user", "user", false));

	}

}
