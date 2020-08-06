package com.example.enumeration;

public enum StatusCode {
	SUCCESS(0, "Success"),
	FAILURE(-1, "Failure");

	int code;
	String message;

	StatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
