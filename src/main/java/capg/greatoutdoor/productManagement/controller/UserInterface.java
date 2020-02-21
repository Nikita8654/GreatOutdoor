package capg.greatoutdoor.productManagement.controller;

import java.util.Map;
import java.util.Scanner;

import capg.greatoutdoor.productManagement.dto.AddressDTO;
import capg.greatoutdoor.productManagement.dto.ProductDTO;
import capg.greatoutdoor.productManagement.dto.UserDTO;
import capg.greatoutdoor.productManagement.service.ProductServiceImpl;
import capg.greatoutdoor.productManagement.util.GenerateRandom;
import capg.greatoutdoors.productManagement.exceptions.LoginException;
import capg.greatoutdoors.productManagement.exceptions.ProductException;

public class UserInterface 
{

	static Scanner scan=new Scanner(System.in);
	
	static ProductServiceImpl object=new ProductServiceImpl();
	
	public static void main(String[] args) 
	{

    	 isLoggedIn();
    }
    
   
	private static void ProductMasterController()
	{
		 System.out.println("\nWelcome Product Master! \nYou can perform following operations");
		while(true)
	    {
	      System.out.println("\n1.Add a Product\n2.Update a Product\n3.Delete a Product\n4.Filter By ProductName\n5.Filter By ProductBrand\n6.Filter By ProductPrice\n7. Search a Product\n8. View a Product\n9. Log Out");
	      System.out.print("\nEnter your choice:");
	      int input=scan.nextInt();
	      switch(input)
	      {
	      case 1:
	    	  addAProduct();
	    	  break;
	      case 2:
	    	  updateAProduct();
	    	  break;
	      case 3:
	    	  deleteAProduct();
	    	  break;
	      case 4:
	    	  filterByProductName();
	    	  break;
	      case 5:
	    	  filterByProductBrand();
	    	  break;
	      case 6:
	    	  filterByProductPrice();
	    	  break;
	      case 7:
	    	  searchAProduct();
	    	  break;
	      case 8:
	    	  viewAllProducts();
	    	  break;
	      case 9:
	    	  LogOutCurrentUser();
	    	  break;
	      default:
	  		closeApplication();
	      }
	    }
	}
	private static void addProductMaster() 
	{
		UserDTO user=getUserDTO();
		object.createProductMaster(user);
		System.out.println("UserId:"+user.getUserId()+" \nPassword:"+user.getPassword());
		
	}

	private static void viewAllProducts() {
		try
		{
		 Map<String,ProductDTO> map=object.viewAllProducts();
		 System.out.println("Id\tprice\tcolour\tDimension\tSpecs\tmanufacturer\tQuantity\tCategory\tProductName\tProductBrand");
			map.forEach((k,v)->{
				
				System.out.println(v.toString());
				
			});
		}
		catch(ProductException e)
		{
			e.printStackTrace();
		}
		
	}

	private static void searchAProduct() {
		System.out.print("\n Provide any of the product name to search:");
		String input=scan.next();
		try
		{
		 Map<String,ProductDTO>map=object.searchAProduct(input);
		 System.out.println("ProductId\tprice\tcolour\tDimension\tSpecs\tmanufacturer\tQuantity\tCategory\tProductName\tProductBrand");
			map.forEach((k,v)->{
				
				System.out.println(v.toString());
				
			});
		}catch(ProductException e)
		{
			e.printStackTrace();
		}
		
	}

	private static void filterByProductPrice() {
		System.out.println("\n1)Sort by Increasing Order\n2)Sort by Decreasing Order ");
		int input=scan.nextInt();
		switch(input)
		{
		case 1:
			try {
				Map<String,ProductDTO> map=object.filterByPrice();
				System.out.println("Id\tprice\tcolour\tDimension\tSpecs\tmanufacturer\tQuantity\tCategory\tProductName\tProductBrand");
				map.forEach((k,v)->{
					
					System.out.println(v.toString());
					
				});
			} catch (ProductException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				Map<String,ProductDTO> map=object.filterByPrice();
				map.entrySet().stream().map(p->p.getValue()).sorted((a,b)->(a.getPrice()<b.getPrice())?1:-1).forEach(System.out::println);
			} catch (ProductException e) {
				e.printStackTrace();
			}
			break;
			default:
				System.out.println("Invalid Input");
				return;
		}
		
		
	}

	private static void filterByProductBrand() {
		try {
			System.out.println("Available Product Brands:");
			object.viewAllProducts().entrySet().stream().map(p->p.getValue()).map(p->p.getProductBrand()).forEach(System.out::println);
			System.out.println("Enter Brand Name to Filter:");
			
			Map<String,ProductDTO> map=object.filterByBrand(scan.next());
			System.out.println("Id\tprice\tcolour\tDimension\tSpecs\tmanufacturer\tQuantity\tCategory\tProductName\tProductBrand");
			map.forEach((k,v)->{
				
				System.out.println(v.toString());
				
			});
		} catch (ProductException e) {
		
			e.printStackTrace();
		}
		
	}

	private static void filterByProductName() 
	{
		
		try {
			System.out.println("Available Product:");
			object.viewAllProducts().entrySet().stream().map(p->p.getValue()).map(p->p.getProductName()).forEach(System.out::println);
			System.out.println("Enter Product Name to Filter:");
			String filterInput=scan.next();
			System.out.println(filterInput);
			Map<String,ProductDTO> map=object.filterByName(filterInput);
			System.out.println("Id\tprice\tcolour\tDimension\tSpecs\tmanufacturer\tQuantity\tCategory\tProductName\tProductBrand");
			map.forEach((k,v)->{
				
				System.out.println(v.toString());
				
			});
		} catch (ProductException e) {
		
			e.printStackTrace();
		}
		
	}

	private static void deleteAProduct()
	{
	 try {
		object.viewAllProducts().values().stream().forEach(System.out::println);
		System.out.print("\nEnter productId to delete:");
		String input=scan.next();
		boolean isdeleted=object.deleteProduct(input);
		if(isdeleted)
			System.out.println("Product Deleted SuccessFully !!");
		else
			System.out.println("Unable to delete product!");
	    }  
	 catch (ProductException e) 
	 {
		 e.printStackTrace();
	  }
	 
	
	}

	private static void updateAProduct() 
	{
		 viewAllProducts();
	     ProductDTO product=getInputOfProduct();
	    try 
	    {
			object.editProduct(product);
		} 
	    catch (ProductException e) {
	    	e.printStackTrace();
		}
	  
	   
		
	}

	public static void addAProduct()
	{
	   	String productId=GenerateRandom.generateRandomString();
	   	System.out.print("\n Enter Price:");
	   	double price=scan.nextDouble();
	   	System.out.print("\nEnter Color:");
	   	String color=scan.next();
	   	System.out.print("\nEnter Dimension:");
	   	String dimension=scan.next();
	   	System.out.print("\nEnter Specification:");
	   	String specification=scan.next();
	   	System.out.print("\nEnter manufacturer:");
	   	String manufacturer=scan.next();
	   	System.out.print("\nEnter ProductName:");
	   	String productName=scan.next();
	   	System.out.print("\nEnter ProductBrand:");
	   	String productBrand=scan.next();
	   	System.out.print("\nEnter ProductQuantity:");
	   	int quantity=scan.nextInt();
	   	System.out.print("\nEnter ProductCategory:");
	   	int category=scan.nextInt();
	  try 
	  {
		boolean isinserted=object.addProduct(new ProductDTO(productId,price,color,dimension,specification,manufacturer,quantity,category,productName,productBrand));
	    if(isinserted)
	    	System.out.println("Product added Successfully !!");
	    else
	    	System.out.println("Unable to add the product ");
	  } 
	  catch (ProductException e) {
		  e.printStackTrace();
	 }
	  
	}
	
	public static ProductDTO getInputOfProduct()
	{	System.out.println("Enter the productId to edit the product details:");
		String productId=scan.next();
	   	System.out.println("Enter Price:");
	   	double price=scan.nextDouble();
	   	System.out.print("\nEnter Color:");
	   	String color=scan.next();
	   	System.out.print("\nEnter Dimension:");
	   	String dimension=scan.next();
	   	System.out.print("\nEnter Specification:");
	   	String specification=scan.next();
	   	System.out.print("\nEnter manufacturer:");
	   	String manufacturer=scan.next();
	   	System.out.print("\nEnter ProductName:");
	   	String productName=scan.next();
	   	System.out.print("\nEnter ProductBrand:");
	   	String productBrand=scan.next();
	   	System.out.print("\nEnter ProductQuantity:");
	   	int quantity=scan.nextInt();
	   	System.out.print("\nEnter ProductCategory:");
	   	int category=scan.nextInt();
	  return new ProductDTO(productId,price,color,dimension,specification,manufacturer,quantity,category,productName,productBrand);	
	}
	
	private static void isLoggedIn()
	{
	   if(object.checkUserLoggedIn())
	   {
		   if(object.checkWhoLoggedIn())
		   {
			   AdminController();
		   }
		   else
		   {
			 ProductMasterController();  
		   }
	   }
	   else
	   {
		   System.out.println("Please Log In!!");
		   promptLogIn();
	   }
	}
	
	private static void AdminController() 
	{
	 System.out.println("\nWelcome Admin !!\nNow choose...");	
	  while(true)
	  {
	   System.out.println("\n\n1)Add a Product Master\n2)View All Products\n3)Search A Product\n4)Filter a Product By Name\n5)Filter Product By Price\n6)Filter Product By Brand\n7)LogOut");	  
	   System.out.print("\nEnter your Choice:");
	   int input=scan.nextInt();
	   switch(input)
	   {
	   case 1:
		    addProductMaster();
		   break;
	   case 2:
		    viewAllProducts();
		   break;
	   case 3:
		   searchAProduct();
		   break;
	   case 4:
		    filterByProductName();
		   break;
	   case 5:
		   filterByProductPrice();
		   break;
	   case 6:
		   filterByProductBrand();
		   break;	
	   case 7:
		   LogOutCurrentUser();
		   break;
	default:
		closeApplication();
	   }
	  }
		
	}


	private static void LogOutCurrentUser() {
	  
		System.out.println("Are you sure that you want to log out? y/n");
		String input=scan.next();
		if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("Yes"))
		{
			object.logOutCurrentUser();
			isLoggedIn();
		}
		else
		{
			isLoggedIn();
		}
		
	}


	private static void promptLogIn()
	{
	System.out.println("Please Enter UserId:");	
	  String userid=scan.next();
	  System.out.println("Please Enter Password"); 
	  String password=scan.next();
	   try
	   {
	    if(!object.validateLogIn(userid, password))
	    	System.out.println("Wrong UserName/Password");
	    isLoggedIn();		   
	   }
	   catch(LoginException e)
	   {
		   e.printStackTrace();
		   promptLogIn();
	   }
	  
	}
	
	public static void closeApplication()
	{
	  System.exit(0);	
	}
	
	public static UserDTO getUserDTO()
	{
		System.out.print("\nEnter UserId:");
		String userid=scan.next();
		System.out.print("\nEnter password:");
		System.out.println("Please Enter Password"); 
		  String password=scan.next();
		System.out.print("\nEnter PhoneNumber:");
		String phone=scan.next();
		System.out.print("\nEnter Email:");
		String email=scan.next();
	   return new UserDTO(userid,password,phone,email,"ProductMaster",new AddressDTO("101","B1001","Noida","Uttar Pradesh","201306"));
	}
	
}

