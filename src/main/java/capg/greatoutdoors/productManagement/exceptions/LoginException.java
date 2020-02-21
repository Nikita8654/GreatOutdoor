package capg.greatoutdoors.productManagement.exceptions;

public class LoginException extends Exception {
String msg;

	public LoginException(String msg) {
		super();
		this.msg = msg;
	}
	
}
