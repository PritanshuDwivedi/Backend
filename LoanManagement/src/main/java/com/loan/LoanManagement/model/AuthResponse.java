package com.loan.LoanManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Containes the response received by Auth Microservice
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
	//column
	private String username;
	
	//column
	private String role;
	
	//column
	private boolean isValid;
}
