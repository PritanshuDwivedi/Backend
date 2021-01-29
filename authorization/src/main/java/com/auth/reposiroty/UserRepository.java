package com.auth.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.auth.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	public UserEntity findByUsernameAndPassword(String name , String pass);

}
