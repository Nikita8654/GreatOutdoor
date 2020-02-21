package capg.greatoutdoors.productManagement.exceptions;

public class ProductException extends Exception{
	
	String msg;

	public ProductException(String msg) {
		super();
		this.msg = msg;
		System.out.println(msg);
	}
	

}
