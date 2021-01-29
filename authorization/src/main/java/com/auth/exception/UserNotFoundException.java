package com.auth.exception;

public class UserNotFoundException extends Exception{
	
	private static final long serialVersionUID = 8216706431976314232L;

	public UserNotFoundException(String msg) {
		super(msg);
	}
}
