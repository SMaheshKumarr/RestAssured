package com.apichaining;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CreateUserAndGetValueFromResponseToPassInNextReq {
	
	int id;
	int id1;
	static String BaseURI = "https://reqres.in/";
	
	// First Approach 
	    @Test(enabled = true)
		public void postReqAndGetIdFromResponse() {

			Map<String, Object> jsonBody = new HashMap<String, Object>();

			List<String> al = new ArrayList<String>();
			al.add("java");
			al.add("hi");

			jsonBody.put("name", "Mahesh");
			jsonBody.put("job", "QA");
			jsonBody.put("skills", al);
			System.out.println(jsonBody);

			id = given()
					.baseUri("https://reqres.in/")
					.header("Content-Type", "application/json")
					.body(jsonBody)
					.log()
					.body()
				.when()
				.post("/api/users?page=2")
				.jsonPath()
				.getInt("id"); // get the ID from the response for
																						// API Chaining
			System.out.println(id);

		}
		
		/*Second Approach - Here we use ITestContext as parameter inside the method and using this we can set the response value in the context set attribute.  
		We use this parameter
		*/
	    @Test(enabled = true)
		public void postReqAndGetIdFromRes(ITestContext context) {

			Map<String, Object> jsonBody = new HashMap<String, Object>();

			List<String> al = new ArrayList<String>();
			al.add("java");
			al.add("hi");

			jsonBody.put("name", "Mahesh");
			jsonBody.put("job", "QA");
			jsonBody.put("skills", al);
			System.out.println(jsonBody);

			int id1 = given()
					.baseUri("https://reqres.in/")
					.header("Content-Type", "application/json")
					.body(jsonBody)
					.log()
					.body()
				.when()
				.post("/api/users?page=2")
				.jsonPath()
				.getInt("id"); // get the ID from the response 
																					
			System.out.println("Generated Id is:" + id1);
			context.getSuite().setAttribute("user_id", id1);

		}
		
		

}
