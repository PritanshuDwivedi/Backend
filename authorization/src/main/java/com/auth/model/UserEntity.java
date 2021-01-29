package com.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserEntity {

	@Id
	@Column(name="username" ,length=20)
	private String username;
	
	@Column(name="password",length=20)
	private String password;
	
	
	@Column(name = "role")
	private String role;

}
