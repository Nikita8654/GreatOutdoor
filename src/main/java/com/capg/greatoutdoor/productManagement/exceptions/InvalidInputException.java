package com.capg.greatoutdoor.productManagement.exceptions;

public class InvalidInputException extends Exception {
	String msg;
	public InvalidInputException(String msg)
	{
		super();
		this.msg = msg;
		System.out.println(msg);
	}
}
