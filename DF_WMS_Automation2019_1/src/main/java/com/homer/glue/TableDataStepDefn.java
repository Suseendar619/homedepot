package com.homer.glue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.dao.Given;
import com.homer.dao.Order;
import com.homer.dao.TableData;
import com.homer.dao.Then;

public class TableDataStepDefn extends BaseStepDefn {

	public TableDataStepDefn(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	
	@Given("^I test Table Data with no parameter step$")
	public void i_test_Table_Data_with_no_parameter_step(TableData table) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
		
	  List<Map<String,String>> lstMap = table.asMaps(String.class, String.class);
	  
	  for(Map<String,String> map : lstMap) {
			
			System.out.println(map.get("order"));
			System.out.println(map.get("credit"));
			System.out.println(map.get("gift"));
			System.out.println(map.get("status"));
		}
	  
	  List<Order> lstOrder =  table.asList(Order.class);
	  
	  for (Order order : lstOrder) {
		  
		  System.out.println(order.order);
		  System.out.println(order.credit);
		  System.out.println(order.gift);
		  System.out.println(order.status);		
	  }
	  
	  List<List<String>> lstTable = table.raw();
	  
	  for(List<String> lstRows : lstTable) {
		  
		  for(String columnVal:lstRows) {
			  
			  System.out.println(columnVal);
		  }
	  }
	}

	@Given("^I test Table Data with parameter \"(.*?)\" step$")
	public void i_test_Table_Data_with_parameter_arg1_step(String arg1, TableData table) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
		
		  System.out.println("Parameter value - " +arg1);
				
		  List<Map<String,String>> lstMap = table.asMaps(String.class, String.class);
		  
		  System.out.println("----------List Map Example-----------------");
		  for(Map<String,String> map : lstMap) {
				
			   System.out.println("-------------------------------------");
			   System.out.println(map.get("order"));
			   System.out.println(map.get("credit"));
			   System.out.println(map.get("gift"));
			   System.out.println(map.get("status"));
			}
		  
		  List<Order> lstOrder =  table.asList(Order.class);
		  
		  System.out.println("----------Cast to Your Type Example-----------------");
		  for (Order order : lstOrder) {
			  
			  System.out.println("-------------------------------------");
			  System.out.println(order.order);
			  System.out.println(order.credit);
			  System.out.println(order.gift);
			  System.out.println(order.status);		
		  }
		  
		  List<List<String>> lstTable = table.raw();
		  
		  System.out.println("----------Cast to Lis<List<String>> Example-----------------");
		  for(List<String> lstRows : lstTable) {
			  
			  System.out.println("-------------------------------------");
			  for(String columnVal:lstRows) {
				  
				  System.out.println(columnVal);
			  }
		  }
	}
	
	@Given("^I test two column data table map feature with parameter \"(.*?)\" step$")
	public void i_test_two_column_data_table_map_feature_with_parameter_arg1_step(String arg1, TableData table) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
		
		 Map<String, String> mapTable = table.asMap(String.class, String.class);
		 
		 System.out.println("----------Cast to Map<String, String> Example-----------------");
		 System.out.println(mapTable.get("FirstName"));
		 System.out.println(mapTable.get("LastName"));
		 System.out.println(mapTable.get("City"));	
	 
	}
	
	@Given("^I test type cast List of String with no parameter step$")
	public void i_test_type_cast_List_of_String_with_no_parameter_step(List<List<String>> lstTableData) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
	
		 System.out.println("----------List String Example-----------------");
		 
		 for(List<String> lstRows : lstTableData) {
			  System.out.println("-------------------------------------"); 
			  for(String columnVal:lstRows) {
				  
				  System.out.println(columnVal);
			  }
		  }		
	}

	@Given("^I test type cast List of String with parameter \"(.*?)\" and \"(.*?)\" step$")
	public void i_test_type_cast_List_of_String_with_parameter_arg1_and_arg2_step(String arg1, String arg2, List<List<String>> lstTableData) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
	 
		 System.out.println("Parameter 1 - "+arg1);
		 System.out.println("Parameter 2 - "+arg2);
		 
		 System.out.println("----------List String Example-----------------");
		 
		 for(List<String> lstRows : lstTableData) {
			  System.out.println("-------------------------------------"); 
			  for(String columnVal:lstRows) {
				  
				  System.out.println(columnVal);
			  }
		  }		
	}
	
	@Given("^I test type cast List of Map with no parameter step$")
	public void i_test_type_cast_List_of_Map_with_no_parameter_step(List<Map<String,String>> lstMapTable) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
			
		 System.out.println("----------List Map Example-----------------");
		
		 for(Map<String,String> map : lstMapTable) {
				
			System.out.println("-------------------------------------");
			System.out.println(map.get("order"));
			System.out.println(map.get("credit"));
			System.out.println(map.get("gift"));
			System.out.println(map.get("status"));
		  }
	}

	@Given("^I test type cast List of Map with parameter \"(.*?)\" and \"(.*?)\" step$")
	public void i_test_type_cast_List_of_Map_with_parameter_arg1_and_arg2_step(String arg1, String arg2, List<Map<String,String>> lstMapTable) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
	
		 System.out.println("Parameter 1 - "+arg1);
		 System.out.println("Parameter 2 - "+arg2);
			
		 System.out.println("----------List Map Example-----------------");
		
		 for(Map<String,String> map : lstMapTable) {
				
			System.out.println("-------------------------------------");
			System.out.println(map.get("order"));
			System.out.println(map.get("credit"));
			System.out.println(map.get("gift"));
			System.out.println(map.get("status"));
		  }
	}
	
	@Given("^I test type cast List of Your Type with no parameter step$")
	public void i_test_type_cast_List_of_Your_Type_with_no_parameter_step(List<Order> lstOrder) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
	
		System.out.println("----------Cast to Your Type Example-----------------");
		for (Order order : lstOrder) {
			  
		   System.out.println("-------------------------------------");
		   System.out.println(order.order);
		   System.out.println(order.credit);
		   System.out.println(order.gift);
		   System.out.println(order.status);		
		 }		
	}

	@Given("^I test type cast List of Your Type with parameter \"(.*?)\" and \"(.*?)\" step$")
	public void i_test_type_cast_List_of_Your_Type_with_parameter_arg1_and_arg2_step(String arg1, String arg2, List<Order> lstOrder) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
		
		System.out.println("Parameter 1 - "+arg1);
		System.out.println("Parameter 2 - "+arg2);
		
		System.out.println("----------Cast to Your Type Example-----------------");
		for (Order order : lstOrder) {
			  
		   System.out.println("-------------------------------------");
		   System.out.println(order.order);
		   System.out.println(order.credit);
		   System.out.println(order.gift);
		   System.out.println(order.status);		
		 }
	}
	
	@Given("^I test type cast Map of String with no parameter step$")
	public void i_test_type_cast_Map_of_String_with_no_parameter_step(Map<String, String> mapTable) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
		
		 System.out.println("----------Cast to Map<String, String> Example-----------------");
		 System.out.println(mapTable.get("FirstName"));
		 System.out.println(mapTable.get("LastName"));
		 System.out.println(mapTable.get("City"));	
	}

	@Given("^I test type cast Map of String with parameter \"(.*?)\" and \"(.*?)\" step$")
	public void i_test_type_cast_Map_of_String_with_parameter_arg1_and_arg2_step(String arg1, String arg2, Map<String, String> mapTable) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
	
		System.out.println("Parameter 1 - "+arg1);
		System.out.println("Parameter 2 - "+arg2);
		
		System.out.println("----------Cast to Map<String, String> Example-----------------");
		System.out.println(mapTable.get("FirstName"));
		System.out.println(mapTable.get("LastName"));
		System.out.println(mapTable.get("City"));
	}

	@Given("^I test Table Data with parameter \"(.*?)\" and data table ([^\"]*) and ([^\"]*) step$")
	public void i_test_Table_Data_with_parameter_arg1_and_data_table_keyword1_and_keyword2_step(String arg1, String keyword1, String keyword2, TableData table) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String
		
		  System.out.println("Parameter 1 - "+arg1);
		  System.out.println("Data table column 1 - "+keyword1);
		  System.out.println("Data table column 2 - "+keyword2);
		  System.out.println("Data from excel - "+dataTable.getData(DataColumn.Keyword));
		  List<Map<String,String>> lstMap = table.asMaps(String.class, String.class);
	  
		  System.out.println("----------List Map Example-----------------");
		  for(Map<String,String> map : lstMap) {
				
			   System.out.println("-------------------------------------");
			   System.out.println(map.get("order"));
			   System.out.println(map.get("credit"));
			   System.out.println(map.get("gift"));
			   System.out.println(map.get("status"));
			}
		  
		  List<Order> lstOrder =  table.asList(Order.class);
		  
		  System.out.println("----------Cast to Your Type Example-----------------");
		  for (Order order : lstOrder) {
			  
			  System.out.println("-------------------------------------");
			  System.out.println(order.order);
			  System.out.println(order.credit);
			  System.out.println(order.gift);
			  System.out.println(order.status);		
		  }
		  
		  List<List<String>> lstTable = table.raw();
		  
		  System.out.println("----------Cast to Lis<List<String>> Example-----------------");
		  for(List<String> lstRows : lstTable) {
			  
			  System.out.println("-------------------------------------");
			  for(String columnVal:lstRows) {
				  
				  System.out.println(columnVal);
			  }
		  }	
	}

	@Given("^I test type cast List of Your Type with parameter \"(.*?)\" and \"(.*?)\" and data table ([^\"]*) and ([^\"]*) step$")
	public void i_test_type_cast_List_of_Your_Type_with_parameter_arg1_and_arg2_and_data_table_keyword1_and_keyword2_step(String arg1,
			String arg2, String keyword1, String keyword2, List<Order> lstOrder) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
	
		  System.out.println("Parameter 1 - "+arg1);
		  System.out.println("Data table column 1 - "+keyword1);
		  System.out.println("Data table column 2 - "+keyword2);
		  System.out.println("Data from excel - "+dataTable.getData(DataColumn.Keyword));
		  
		  for (Order order : lstOrder) {
			  
			  System.out.println("-------------------------------------");
			  System.out.println(order.order);
			  System.out.println(order.credit);
			  System.out.println(order.gift);
			  System.out.println(order.status);		
		  }		  
	}
	

	@Then("^I test Hash Map Data$")
	public void i_test_Hash_Map_Data(Map<String, String> mapTable) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
		
		System.out.println(mapTable.get("Email"));
		System.out.println(mapTable.get("Password"));
	}
	

	@Given("^I test Table Data with parameter \"(.*?)\"$")
	public void i_test_Table_Data_with_parameter_arg1(String arg1, TableData table) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
		
		 List<Map<String,String>> lstMap = table.asMaps(String.class, String.class);
		 System.out.println("----------List Map Example-----------------");
		  for(Map<String,String> map : lstMap) {
				
			   System.out.println("-------------------------------------");
			   System.out.println(map.get("order"));
			   System.out.println(map.get("credit"));
			   System.out.println(map.get("gift"));
			   System.out.println(map.get("status"));
			}
		  
		  List<Order> lstOrder =  table.asList(Order.class);
		  
		  System.out.println("----------Cast to Your Type Example-----------------");
		  for (Order order : lstOrder) {
			  
			  System.out.println("-------------------------------------");
			  System.out.println(order.order);
			  System.out.println(order.credit);
			  System.out.println(order.gift);
			  System.out.println(order.status);		
		  }
		  
		  List<List<String>> lstTable = table.raw();
		  
		  System.out.println("----------Cast to Lis<List<String>> Example-----------------");
		  for(List<String> lstRows : lstTable) {
			  
			  System.out.println("-------------------------------------");
			  for(String columnVal:lstRows) {
				  
				  System.out.println(columnVal);
			  }
		  }
	}
	
	@Given("^I test type cast List of Your Type with parameter \"(.*?)\" and \"(.*?)\" and data ([^\"]*) and ([^\"]*)$")
	public void i_test_type_cast_List_of_Your_Type_with_parameter_arg1_and_arg2_and_data_Email_and_Password(String arg1, String arg2, String Email, String Password, TableData table) throws Throwable { 
	 // Write code here that turns the phrase above into concrete actions 
	 // For automatic transformation, change TableData to one of
	 // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	 // E,K,V must be a String 
		
		  System.out.println("Parameter 1 - "+arg1);
		  System.out.println("Data table column 1 - "+Email);
		  System.out.println("Data table column 2 - "+Password);
		  System.out.println("Data from excel - "+dataTable.getData(DataColumn.Keyword));
		  List<Map<String,String>> lstMap = table.asMaps(String.class, String.class);
	  
		  System.out.println("----------List Map Example-----------------");
		  for(Map<String,String> map : lstMap) {
				
			   System.out.println("-------------------------------------");
			   System.out.println(map.get("order"));
			   System.out.println(map.get("credit"));
			   System.out.println(map.get("gift"));
			   System.out.println(map.get("status"));
			}
		  
		  List<Order> lstOrder =  table.asList(Order.class);
		  
		  System.out.println("----------Cast to Your Type Example-----------------");
		  for (Order order : lstOrder) {
			  
			  System.out.println("-------------------------------------");
			  System.out.println(order.order);
			  System.out.println(order.credit);
			  System.out.println(order.gift);
			  System.out.println(order.status);		
		  }
		  
		  List<List<String>> lstTable = table.raw();
		  
		  System.out.println("----------Cast to Lis<List<String>> Example-----------------");
		  for(List<String> lstRows : lstTable) {
			  
			  System.out.println("-------------------------------------");
			  for(String columnVal:lstRows) {
				  
				  System.out.println(columnVal);
			  }
		  }		
	}

	@Then("^I verify all the tags on NRF using below data$")
	public void i_verify_all_the_tags_on_NRF_using_below_data(Map<String, String>dataMap) throws Throwable { 
	
		for (Entry<String, String> data : dataMap.entrySet()) {			
				
			System.out.println(data.getKey());
			System.out.println(data.getValue());
		}
		
	}
}
