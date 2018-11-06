package com.leasewithease.rest.authentication;

public class InvalidCredentialsException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException() {
		super("Invalid credentials provided.");
	}
}