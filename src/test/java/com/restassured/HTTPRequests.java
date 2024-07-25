package com.restassured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class HTTPRequests {
	
	int id;
	static String BaseURI = "https://reqres.in/";
	

	@Test(priority=1,enabled=false)
	public void getReq() {
		
		System.out.println("Get Request");
		
		RestAssured.baseURI="https://reqres.in/api";
		
			given()
			 .baseUri(BaseURI)
			 .header("Content-Type","application/json")
			 
			.when()
			  .get("/users?page=2")
			  
			.then()
			  .assertThat()
			  .statusCode(200)
			  .log()
			  .all();
			
	}
	
		@Test(priority=2)
		public void postReq() {
		
		System.out.println("***Post Request****");
		
		HashMap<String,Object> data = new HashMap<String,Object>();
		data.put("name", "MHK");
		data.put("job", "Eng");
		
			id = given()
					  .baseUri(BaseURI)
					  .header("Content-Type","application/json")
					  .body(data)
				
				.when()
					   .post("/users")
					   .jsonPath().getInt("id");
		   
				/*.then()
				   .assertThat()
				   .statusCode(201)
				   .log().all();*/
				
				System.out.println(id);
		
		
	}
	
	@Test(priority=3)
	public void putReq() {
		
		System.out.println("****Put Request****");
		
		RestAssured.baseURI="https://reqres.in/api";
		
		HashMap<String,Object> data = new HashMap<String,Object>();
		data.put("name", "MH");
		data.put("job", "Engineer");
		
        given()
	           .baseUri(BaseURI)
			   .header("Content-Type","application/json")
			   .body(data)
		
		.when()
			   .put("/users/"+id)
   
		.then()
			   .assertThat()
			   .statusCode(200)
			   .log().all();
		
		
	}
	
	

}
