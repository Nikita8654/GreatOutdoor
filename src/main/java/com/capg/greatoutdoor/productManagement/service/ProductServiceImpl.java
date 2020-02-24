package com.capg.greatoutdoor.productManagement.service;

import java.util.Map;

import com.capg.greatoutdoor.productManagement.dao.ProductDaoImpl;
import com.capg.greatoutdoor.productManagement.exceptions.InvalidInputException;
import com.capg.greatoutdoor.productManagement.exceptions.LoginException;
import com.capg.greatoutdoor.productManagement.exceptions.ProductException;
import com.capg.greatoutdoor.productManagement.model.ProductDTO;
import com.capg.greatoutdoor.productManagement.model.UserDTO;
import com.capg.greatoutdoor.productManagement.util.CurrentUserInfo;

public class ProductServiceImpl implements ProductService {

	ProductDaoImpl productDao;
	
	public ProductServiceImpl()
	{
	 productDao=new ProductDaoImpl();	
	}
	

	@Override
	public Map<String, ProductDTO> viewAllProducts() throws ProductException {
		 return productDao.viewAllProducts();
	}

	@Override
	public boolean addProduct(ProductDTO product) throws ProductException ,InvalidInputException{
			if(product==null)
				throw new ProductException("Product Cannot be null");
			if(!product.getProductName().matches("^[a-zA-Z0-9]+$")
					|| !product.getProductBrand().matches("^[a-zA-Z]*$")
					|| !product.getColour().matches("^[a-zA-Z]*$"))
			
				throw new InvalidInputException("Invalid Input");
		
			else
				return productDao.addProduct(product);
	}

	@Override
	public boolean editProduct(ProductDTO product) throws ProductException,InvalidInputException {
		if(product==null)
			throw new ProductException("Product Cannot be null");
		else if(!product.getProductName().matches("^[a-zA-Z0-9]+$")
				|| !(product.getProductBrand().matches("^[a-zA-Z]*$"))
				||!(product.getColour().matches("^[a-zA-Z]*$")))
			throw new InvalidInputException("Invalid Input");
		else
			return productDao.editProduct(product);
	}

	@Override
	public boolean deleteProduct(String productId) throws ProductException {
		if(!productId.isEmpty())
			return productDao.deleteProduct(productId);
		else
			throw new ProductException("ProductId Cannot be Empty");
	}

	@Override
	public Map<String, ProductDTO> filterByName(String input) throws ProductException {
		if(!input.isEmpty())
		{	
		    return productDao.filterByName(input);
		}
        else
        {	
        	throw new ProductException("Input Cannot be empty!");
        }
	}

	@Override
	public Map<String, ProductDTO> filterByPrice() throws ProductException {
		return productDao.filterByPrice();
	}

	@Override
	public Map<String, ProductDTO> filterByBrand(String input) throws ProductException {
		 if(!input.isEmpty())
			    return productDao.filterByBrand(input);
	        else
	        	throw new ProductException("Input Cannot be empty!");
	}

	@Override
	public Map<String, ProductDTO> searchAProduct(String input) throws ProductException {
		 if(input.isEmpty())
			 throw new ProductException("Input cannot be empty!");
		 else
		   return productDao.searchAProduct(input);
	}

	public boolean checkUserLoggedIn()
	{
		return CurrentUserInfo.isAnyOneLoggedIn();
	}
	
	public boolean validateLogIn(String username,String password)throws LoginException
	{
		if(productDao.validateAdminLogIn(username, password))
		{
			CurrentUserInfo.setAnyOneLoggedIn(true);
			CurrentUserInfo.setTypeOfUser("Admin");
			return true;
		}
		else if(productDao.validateProductMasterLogIn(username, password))
		{
			CurrentUserInfo.setAnyOneLoggedIn(true);
			CurrentUserInfo.setTypeOfUser("ProductMaster");
			return true;
		}
		else
			return false;
	}


	public boolean checkWhoLoggedIn() {
		  return CurrentUserInfo.getTypeOfUser().contentEquals("Admin");
	}


	public void logOutCurrentUser() {
		productDao.logOutCurrentUser();
		
	}
	
   public boolean createProductMaster(UserDTO user)
   {
	   productDao.createProductMaster(user);
	   return true;
   }

}
