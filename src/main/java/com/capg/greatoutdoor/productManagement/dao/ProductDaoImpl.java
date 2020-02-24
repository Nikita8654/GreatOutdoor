package com.capg.greatoutdoor.productManagement.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.capg.greatoutdoor.productManagement.exceptions.InvalidInputException;
import com.capg.greatoutdoor.productManagement.exceptions.LoginException;
import com.capg.greatoutdoor.productManagement.exceptions.ProductException;
import com.capg.greatoutdoor.productManagement.model.ProductDTO;
import com.capg.greatoutdoor.productManagement.model.UserDTO;
import com.capg.greatoutdoor.productManagement.util.AdminRepo;
import com.capg.greatoutdoor.productManagement.util.CurrentUserInfo;
import com.capg.greatoutdoor.productManagement.util.ProductMasterRepo;
import com.capg.greatoutdoor.productManagement.util.ProductRepo;

public class ProductDaoImpl implements ProductDao {

	 Map<String, ProductDTO> productsList;
	 
	 public ProductDaoImpl() {
		this.productsList=ProductRepo.getProductRepo();
	}
	 
	@Override
	public Map<String, ProductDTO> viewAllProducts() throws ProductException {
		if(productsList.isEmpty())
			throw new ProductException("No product exists!");
		else
			return productsList;
	}

	@Override
	public boolean addProduct(ProductDTO product) throws ProductException,InvalidInputException {
		if(productsList.containsKey(product.getProductId()))
			throw new ProductException("Product already exists!");
		else
		{
			productsList.put(product.getProductId(),product);
			ProductRepo.addingProductByProductMaster(product);
			return true;
		}
		
	}

	@Override
	public boolean editProduct(ProductDTO product) throws ProductException,InvalidInputException {
		if(productsList.containsKey(product.getProductId()))
		{
			productsList.put(product.getProductId(), product);
			ProductRepo.editProductByProductMaster(product);
			return true;
		}	 
		else
		{
		 throw new ProductException("There is no such product to edit!!");
		}
	}

	@Override
	public boolean deleteProduct(String productId) throws ProductException {
		 if(productsList.containsKey(productId))
		   {
			   productsList.remove(productId);
			   ProductRepo.deleteProductByProductMaster(productId);
			   return true;
		   }
		   else
		   {
			   throw new ProductException("No Such Product Exist!");   
		   }
	}

	@Override
	public Map<String, ProductDTO> filterByName(String input) throws ProductException{
		Collection<ProductDTO> list;	
		   list=viewAllProducts().values();
		   if(list==null)
			   throw new ProductException("No Elements in Repository");
	      List<ProductDTO> output=list.stream().filter(p->p.getProductName().toLowerCase().contentEquals(input.toLowerCase())).collect(Collectors.toList());
		 
			Map<String,ProductDTO> o = new LinkedHashMap<>();
		    
		    for(ProductDTO p:output)
		    {
		     o.put(p.getProductId(), p);	
		    }
		   if(o.isEmpty())
			   throw new ProductException("No Such Thing to Filter!");
		   else
			   return o;
	}

	@Override
	public Map<String, ProductDTO> filterByPrice() throws ProductException {
		
		Collection<ProductDTO> list;	
		   list=viewAllProducts().values();
		   if(list==null)
			   throw new ProductException("No Elements in Repository");
      List<ProductDTO> output=list.stream().sorted((a,b)->(a.getPrice()>b.getPrice())?1:-1).collect(Collectors.toList());
	    
		Map<String,ProductDTO> o = new LinkedHashMap<>();
	    
	    for(ProductDTO p:output)
	    {
	     o.put(p.getProductId(), p);	
	    }
	    
	    return o;
	}

	@Override
	public Map<String, ProductDTO> filterByBrand(String input) throws ProductException {
		
		Collection<ProductDTO> list;	
		   list=viewAllProducts().values();
		   if(list==null)
			   throw new ProductException("No Elements in Repository");
		List<ProductDTO> output=list.stream().filter(p->p.getProductBrand().toLowerCase().contentEquals(input.toLowerCase())).collect(Collectors.toList());
	    
		Map<String,ProductDTO> o = new LinkedHashMap<>();
	    for(ProductDTO p:output)
	    {
	     o.put(p.getProductId(), p);	
	    }
	    if(o.isEmpty())
			   throw new ProductException("No Such Thing to Filter!");
		   else
			   return o;
	}

	@Override
	public Map<String, ProductDTO> searchAProduct(String input) throws ProductException {
		

        Map<String,ProductDTO> out=new HashMap<>();
		List<ProductDTO> output=viewAllProducts().values().stream().
		filter(p->
		p.getProductName().toLowerCase().contains(input.toLowerCase()) || p.getProductBrand().toLowerCase().contains(input.toLowerCase()) || p.getProductId().contains(input.toLowerCase()) || p.getSpecification().toLowerCase().contains(input.toLowerCase())
      ).collect(Collectors.toList());
		if(output.isEmpty())
			throw new ProductException("No Such Product Exist !!");
		else
		{
			for(ProductDTO po:output)
				out.put(po.getProductId(), po);
	     return out;
		}
	}

	public boolean validateAdminLogIn(String username,String password)
	{
		Map<String,UserDTO> admindata=AdminRepo.getAdminData();
		if(admindata.containsKey(username))
		{
			 return admindata.get(username).getPassword().contentEquals(password);
		}
		return false;
	}
	
	public boolean validateProductMasterLogIn(String username,String password)throws LoginException
	{
		Map<String,UserDTO> productMasterData=ProductMasterRepo.getProductMaster();
		
		if(productMasterData.isEmpty())
			throw new LoginException("No ProductMaster available!\n Please create a Product Master.\nPlease Login to Admin and create one!");
		else
		if(productMasterData.containsKey(username))
		{
			return productMasterData.get(username).getPassword().contentEquals(password);
		}
		return false;
	}
	
public void logOutCurrentUser() {
		
		CurrentUserInfo.setAnyOneLoggedIn(false);
		CurrentUserInfo.setTypeOfUser(null);
	}

	
	public void createProductMaster(com.capg.greatoutdoor.productManagement.model.UserDTO user)
	   {
		  ProductMasterRepo.setProductMaster(user);
	   }
}
