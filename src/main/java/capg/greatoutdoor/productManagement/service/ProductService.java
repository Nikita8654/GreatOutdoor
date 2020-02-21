package capg.greatoutdoor.productManagement.service;

import java.util.Map;

import capg.greatoutdoor.productManagement.dto.ProductDTO;
import capg.greatoutdoors.productManagement.exceptions.ProductException;

public interface ProductService {

	Map<String,ProductDTO> viewAllProducts()throws ProductException;
	boolean addProduct(ProductDTO product)throws ProductException;
	boolean editProduct(ProductDTO prodcut)throws ProductException;
	boolean deleteProduct(String productId)throws ProductException;
	Map<String,ProductDTO> filterByName(String input)throws ProductException;
	Map<String,ProductDTO> filterByPrice()throws ProductException;
	Map<String,ProductDTO> filterByBrand(String input)throws ProductException;
	Map<String,ProductDTO> searchAProduct(String input)throws ProductException;
   
}
