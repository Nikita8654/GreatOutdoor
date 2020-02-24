package com.capg.greatoutdoor.productManagement.util;

import java.util.HashMap;
import java.util.Map;

import com.capg.greatoutdoor.productManagement.model.AddressDTO;
import com.capg.greatoutdoor.productManagement.model.UserDTO;

public class AdminRepo {

	static Map<String,UserDTO> adminData=new HashMap<String,UserDTO>();

	static {
		adminData.put("bob123",new UserDTO("bob123","admin","1234567890","bob@gmail.com","admin",new AddressDTO("jack12","bdo12","Greater Noida","U.P.","201306")));
		
	}
	public static Map<String, UserDTO> getAdminData() {
		return adminData;
	}

}
