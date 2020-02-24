package com.capg.greatoutdoor.productManagement.util;

import java.util.HashMap;

import com.capg.greatoutdoor.productManagement.model.AddressDTO;
import com.capg.greatoutdoor.productManagement.model.UserDTO;

public class ProductMasterRepo {

	public static HashMap<String, UserDTO> productMasterHashMap=new HashMap<String, UserDTO>();
	 static
	   {
		 productMasterHashMap.put("jack", new UserDTO("jack","8654","9866772522","bob134@gmail.com","ProductMaster",new AddressDTO("101","B1001","Noida","Uttar Pradesh","201306")));
	   }
	
	public static void setProductMaster(UserDTO user)
	{
		productMasterHashMap.put(user.getUserId(),user);
		productMasterHashMap.forEach((k,v)->{
			System.out.println("Product Master Username:"+k+"\n"+v.getEmail()+"\n"+v.getPassword());
			System.out.println();
		});
	}
	
	public static HashMap<String, UserDTO> getProductMaster()
	{
		return productMasterHashMap;
	}
}
