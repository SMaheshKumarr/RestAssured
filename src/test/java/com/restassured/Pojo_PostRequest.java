package com.restassured;

import java.util.List;

public class Pojo_PostRequest {

	String name;
	String location;
	List courses;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List getCourses() {
		return courses;
	}
	public void setCourses(List courses) {
		this.courses = courses;
	}

}
