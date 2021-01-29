package com.auth.service;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth.model.UserEntity;
import com.auth.reposiroty.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerDetailsServiceTest {

	@InjectMocks
	CustomerDetailsService service;

	@Mock
	UserRepository userDao;

	@Mock
	UserEntity user;

	@Mock
	UserDetails userDetails;

	private static final String ID = "ID";

	@BeforeEach
	void setUp() {
		user = new UserEntity("name", "pass", "admin");
	}

	@Test
	void loadUserByUsernameTest() {
		Mockito.when(userDao.findById(ID)).thenReturn(Optional.of(user));
		userDetails = service.loadUserByUsername(ID);
		Mockito.verify(userDao, Mockito.atLeast(1)).findById(ID);
	}

}
