package com.capg.greatoutdoor.productManagement.service;

import java.util.Map;

import com.capg.greatoutdoor.productManagement.exceptions.InvalidInputException;
import com.capg.greatoutdoor.productManagement.exceptions.ProductException;
import com.capg.greatoutdoor.productManagement.model.ProductDTO;

public interface ProductService {

	Map<String,ProductDTO> viewAllProducts()throws ProductException;
	boolean addProduct(ProductDTO product)throws ProductException,InvalidInputException;
	boolean editProduct(ProductDTO prodcut)throws ProductException,InvalidInputException;
	boolean deleteProduct(String productId)throws ProductException;
	Map<String,ProductDTO> filterByName(String input)throws ProductException;
	Map<String,ProductDTO> filterByPrice()throws ProductException;
	Map<String,ProductDTO> filterByBrand(String input)throws ProductException;
	Map<String,ProductDTO> searchAProduct(String input)throws ProductException;
   
}
