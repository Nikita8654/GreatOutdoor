package com.capg.greatoutdoor.productManagement.exceptions;

public class LoginException extends Exception {
String msg;

	public LoginException(String msg) {
		super();
		this.msg = msg;
	}
	
}
