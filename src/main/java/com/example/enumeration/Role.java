package com.example.enumeration;

public enum Role {
	ADMIN,
	USER,
	GUEST;

	@Override
	public String toString() {
		return "ROLE_" + super.toString();
	}
}
