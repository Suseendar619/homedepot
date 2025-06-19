package com.homer.glue;

import com.homer.dao.DataClass;
import com.homer.dao.Given;
import com.homer.enums.EnumClass.StepResult;

public class SubFolderStepDefn extends BaseStepDefn {

	public SubFolderStepDefn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Given("^Testing subfolder level zero$")
	public void testing_subfolder_level_zero() throws Throwable { 
		System.out.println("Testing subfolder level zero");	  
	}
	
	@Given("^Testing subfolder one level zero_one$")
	public void testing_subfolder_one_level_zero_one() throws Throwable { 
		
		System.out.println("Testing subfolder one level zero_one");
	}
	
	@Given("^Testing subfolder two level zero_one$")
	public void testing_subfolder_two_level_zero_one() throws Throwable { 
		
	  System.out.println("Testing subfolder two level zero_one");
	}
	
	@Given("^Testing level one_one$")
	public void testing_level_one_one() throws Throwable { 
		
		System.out.println("Testing level one_one");
	}
	
	@Given("^Testing subfolder one level one_one$")
	public void testing_subfolder_one_level_one_one() throws Throwable { 
		
		System.out.println("Testing subfolder one level one_one");
	}
	
	@Given("^Testing subfolder two level one_one$")
	public void testing_subfolder_two_level_one_one() throws Throwable {
		
	  System.out.println("Testing subfolder two level one_one");
	}

	@Given("^Testing level one_two$")
	public void testing_level_one_two() throws Throwable { 
		
		System.out.println("Testing level one_two");
	}
	
	@Given("^Testing subfolder one level one_two$")
	public void testing_subfolder_one_level_one_two() throws Throwable { 
	  
		System.out.println("Testing subfolder one level one_two");
	}
	
	@Given("^Testing subfolder two level one_two$")
	public void testing_subfolder_two_level_one_two() throws Throwable { 
		
		System.out.println("Testing subfolder two level one_two");
	}
	
	@Given("^Testing level two_one_one$")
	public void testing_level_two_one_one() throws Throwable { 
	  
		System.out.println("Testing level two_one_one");
	}

	@Given("^Testing subfolder one level two_one_one$")
	public void testing_subfolder_one_level_two_one_one() throws Throwable { 
	  
		System.out.println("Testing subfolder one level two_one_one");
	}

	@Given("^Testing subfolder two level two_one_one$")
	public void testing_subfolder_two_level_two_one_one() throws Throwable { 
		
	  System.out.println("Testing subfolder two level two_one_one");
	}
	
	@Given("^Testing level two_one_two$")
	public void testing_level_two_one_two() throws Throwable { 
	  
		System.out.println("Testing level two_one_two");
	}
	
	@Given("^Testing subfolder one level two_one_two$")
	public void testing_subfolder_one_level_two_one_two() throws Throwable { 
	  
		System.out.println("Testing subfolder one level two_one_two");
	}
	
	@Given("^Testing subfolder two level two_one_two$")
	public void testing_subfolder_two_level_two_one_two() throws Throwable { 
	  
		System.out.println("Testing subfolder two level two_one_two");
	}
	
	@Given("^Testing level two_two_one$")
	public void testing_level_two_two_one() throws Throwable {  
	  
		System.out.println("Testing level two_two_one");
	}

	@Given("^Testing subfolder one level two_two_one$")
	public void testing_subfolder_one_level_two_two_one() throws Throwable { 
		
		System.out.println("Testing subfolder one level two_two_one");
	}
	
	@Given("^Testing subfolder two level two_two_one$")
	public void testing_subfolder_two_level_two_two_one() throws Throwable { 
		
	  System.out.println("Testing subfolder two level two_two_one");
	}
	
	@Given("^Testing level two_two_two$")
	public void testing_level_two_two_two() throws Throwable { 
		
		System.out.println("Testing level two_two_two");
	}
	
	@Given("^Testing subfolder one level two_two_two$")
	public void testing_subfolder_one_level_two_two_two() throws Throwable { 
	  
		System.out.println("Testing subfolder one level two_two_two");
	}
	
	@Given("^Testing subfolder two level two_two_two$")
	public void testing_subfolder_two_level_two_two_two() throws Throwable { 
	
		System.out.println("Testing subfolder two level two_two_two");
	}
}
