package com.capg.greatoutdoor.productManagement.dao;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capg.greatoutdoor.productManagement.exceptions.InvalidInputException;
import com.capg.greatoutdoor.productManagement.exceptions.LoginException;
import com.capg.greatoutdoor.productManagement.exceptions.ProductException;
import com.capg.greatoutdoor.productManagement.service.ProductServiceImpl;
import com.capg.greatoutdoor.productManagement.util.ProductRepo;

public class ProductDaoImplTest {
	static ProductServiceImpl service;
	static ProductRepo productRepo;
	static ProductDaoImpl productDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new ProductServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	@Test(expected = ProductException.class)
	public void testAddProduct() throws ProductException, InvalidInputException {
		service.addProduct(null);
	}

	@Test
	public void testLogin() throws LoginException {
		assertEquals(false, service.validateLogIn("", ""));
	}

	@Test(expected = ProductException.class)
	public void testUpdateProduct() throws ProductException, InvalidInputException {
		service.editProduct(null);
		
	}
	@Test(expected = ProductException.class)
	public void testDeleteProduct() throws ProductException {
		assertEquals(false, service.deleteProduct(""));
		
	}
	
	@Test
	public void testFilterProductByPrice() throws ProductException {
		assertNotNull(service.filterByPrice());
	}

	@Test
	public void testSearchAProduct() throws ProductException {
		assertNotNull(service.searchAProduct("puma"));
	}
	@Test
	public void checkLogin() throws LoginException
	{	
		assertEquals(true, service.validateLogIn("bob123", "admin"));
	}

}
