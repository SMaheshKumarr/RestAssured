package com.restassured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {
	
	
	static String BaseURI ="http://localhost:3000";
	
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

}
