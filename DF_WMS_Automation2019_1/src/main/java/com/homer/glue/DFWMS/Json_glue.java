//package com.homer.glue.DFWMS;
//
//import com.homer.dao.DataClass;
//import com.homer.dao.Then;
//import static io.restassured.RestAssured.*;
//import com.homer.glue.BaseStepDefn;
//
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//public class Json_glue extends BaseStepDefn {
//
//	public Json_glue(DataClass data) {
//		super(data);
//		// TODO Auto-generated constructor stub
//	}
//
//@Then("^post the json service$")
//public void post_the_json_service() throws Throwable { 
//	
//	String token_url = "http://wnc9786:4000/executeTestcase";
//	
//	
//	String body="{\"client_id\": \"muFmuj3nJEqVu3igcgBSODM7fFCBB2G9\","
//			+"\"client_secret\": \"i1ptOxXCaJgUYAXo\","
//			+"\"grant_type\": \"client_credentials\"}";
//	
//
//	// Getting the Access Token
//
//	Response resp = given().contentType("application/json")
//			.body(body).when().post(token_url);
//	
//	String response_body = resp.getBody().asString();
//	JsonPath jsonpath = new JsonPath(response_body);
//
//	// Validating Response Code
//	int response_code = resp.getStatusCode();
//	if (response_code == 200) {
//
//	access_token = jsonpath.getString("token");
//	
// report.addReportStep("Token should be generated",
//				"Token is generated successfully", StepResult.PASS);
//	
//	
//	}else{
//		report.addReportStep("Token should be generated",
//				"Token is not generated", StepResult.FAIL);
//	}
//
//   }
//
//catch(Exception e)
//{
//	System.out.println(e);
//}
//  
//}
//}
