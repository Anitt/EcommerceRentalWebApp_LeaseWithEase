package com.leasewithease.rest.model;

public class UserLogin {
	private String email;
	private String passwordHash;
	private boolean isLessor;

	public String getEmail() {
		return email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public boolean isLessor() {
		return isLessor;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setIsLessor(boolean isLessor) {
		this.isLessor = isLessor;
	}
}