package com.restassured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DifferentWaysToCreatePostMethod {
	
	
	static String BaseURI ="http://localhost:3000";
	
	/*
	 * Different ways to create post method 
	 * Using HashMap 
	 * Using POJO (Getter and Setter)
	 * Using org.json (JSONObject)
	 * Using External Json File (Reading data from external file)
	 * use faker library to generate random data
	 */
	
// POST request using HashMap 
//	@Test
	public void testPostUsingHashMap() {
		
		
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		hm.put("name", "Mahe");
		hm.put("location", "CHN");
		hm.put("courses", Arrays.asList("Docker","Maven"));
		
		given()
			.baseUri(BaseURI)
			.header("Content-Type","application/json")
			.body(hm)
		.when()
			.post("/students")
		.then()
		 	.statusCode(201)
		 	.body("name", equalTo("Mahe"));
		
	}
	
	// POST request using Json Library 	
	//@Test
	public void testPostUsingJsonLibrary() {
		
		//toString() should be mentioned in the body payload
		
		JSONObject jo = new JSONObject();
		jo.put("name", "Mahes");
		jo.put("location", "CHNN");
		jo.put("courses", Arrays.asList("Docker","Maven"));
		
		given()
			.baseUri(BaseURI)
			.header("Content-Type","application/json")
			.body(jo.toString())
			.log()
			.all()
		.when()
			.post("/students")
		.then()
		 	.statusCode(201)
		 	.body("name", equalTo("Mahes"))
		 	.body(contains("Mahes"))
		 	.log().all();
		
	}
	
	
	
	
	// POST request using POJO 	
	//	@Test
		public void testPostUsingPOJO() {
			
			
           Pojo_PostRequest po = new Pojo_PostRequest();
           po.setName("xxx");
           po.setLocation("Germany");
           po.setCourses(Arrays.asList("PHP","Java"));
           System.out.println(po);
			
			given()
				.baseUri(BaseURI)
				.header("Content-Type","application/json")
				.body(po)
				.log()
				.all()
			.when()
				.post("/students")
			.then()
			 	.statusCode(201)
			 	.body("name", equalTo("xxx"))
			 	.log().all();
			
		}
		
		
		@Test
		public void testPostUsingFakerLibrary() {
			
			Faker faker = new Faker();
		
			String fullname = faker.name().fullName();
			String location = faker.address().city();
			
			HashMap<String, Object> hm = new HashMap<String,Object>();
			hm.put("name", fullname);
			hm.put("location", location);
			hm.put("courses", Arrays.asList("Git","SVN"));
			
			given()
				.baseUri(BaseURI)
				.header("Content-Type","application/json")
				.body(hm)
			.when()
				.post("/students")
			.then()
			 	.statusCode(201);
			 	
			
		}
		
	
	//Delete the resources based on ID
	//@Test
	public void testDelete(){
		
		given()
		.baseUri(BaseURI)
		.log()
		.all()
	.when()
		.delete("/students/")
	.then()
	 	.statusCode(200)
	 	.log().all();
		
	}
	

}
