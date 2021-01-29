package com.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth.model.UserEntity;
import com.auth.reposiroty.UserRepository;

import lombok.extern.slf4j.Slf4j;

/* This is the CustomerDetailsService class to get user details */
@Service
@Slf4j
public class CustomerDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userDao;

	// This method is finding the given username in database and returning the User

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.debug("Finding user by username - " + username);
		UserEntity user = userDao.findById(username).get();
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
