package capg.greatoutdoor.productManagement.util;

import java.util.HashMap;
import java.util.Map;

import capg.greatoutdoor.productManagement.dto.AddressDTO;
import capg.greatoutdoor.productManagement.dto.UserDTO;

public class AdminRepo {

	static Map<String,UserDTO> adminData=new HashMap<String,UserDTO>();

	static {
		adminData.put("bob123",new UserDTO("bob123","admin","1234567890","bob@gmail.com","admin",new AddressDTO("jack12","bdo12","Greater Noida","U.P.","201306")));
		
	}
	public static Map<String, UserDTO> getAdminData() {
		return adminData;
	}

}
