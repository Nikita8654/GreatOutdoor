package capg.greatoutdoor.productManagement.dto;

public class AddressDTO {
	
	private String retailerId;
	private String buildingNo;
	private String city;
	private String state;
	private String zipCode;
	
	
	public AddressDTO(String retailerId, String buildingNo, String city, String state, String zipCode) {
		super();
		this.retailerId = retailerId;
		this.buildingNo = buildingNo;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	public String getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
