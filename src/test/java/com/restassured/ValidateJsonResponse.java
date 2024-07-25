package com.restassured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ValidateJsonResponse {
	
	static String BaseURI ="http://localhost:3000";
	
	//validating single response Approach 1
	//@Test
		public void testValidateResponseA1() {
			
			given()
				.baseUri(BaseURI)
				.header("Content-Type","application/json")
				.log()
				.all()
			.when()
				.get("/students")
			.then()
			 	.statusCode(200)
			 	.body("[4].name", equalTo("Mahes"))
			 	.log().all();
			
		}
		
		//validating single response Approach 2
		//@Test
		public void testValidateResponseA2() {
			
		Response res = 	given()
							.baseUri(BaseURI)
							.header("Content-Type","application/json")
							.log()
							.all()
						.when()
							.get("/students");
		
		//Option 1
		String getName = res.jsonPath().get("[4].name").toString();
		System.out.println("One of the Name details from Array is >> " + getName);
		
		//Option 2
		String usernames = res.jsonPath().getString("name[3]");
		System.out.println(usernames);
		
		//Option 3
		List<String> jsonResponse = res.jsonPath().getList("name");
		System.out.println(jsonResponse.get(0));
						
		}
		
		//Validate complete response and take the list of names from array
		//@Test
		public void testValidateEntireResponse() {
			
			Response res = given()
							.baseUri(BaseURI)
							.contentType(ContentType.JSON)
							.log()
							.all()
						  .when()
							.get("/students");
			
			//using JSONObject class
			
			  JSONObject jo = new JSONObject(res.asString()); //converting response to JSON
			  
			  //JSONArray jr = new JSONArray(res.asString());
			  for(int i=0;i<jo.getJSONArray("name").length();i++){
			  
			  String getNameList =
			  jo.getJSONArray("name").getJSONObject(i).get("name").toString();
			  System.out.println(getNameList);
			  
			  
			  }
			 
			
			//another way
			
			String name = res.jsonPath().getString("name");
			System.out.println(name);
			
			
		}
		

}
