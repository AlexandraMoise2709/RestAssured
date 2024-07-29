package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.json.simple.JSONObject;

import static org.hamcrest.Matchers.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Homework4 {

	public void testAPI() {
	Response response = given().get("https://swapi.dev/api/people/1/").then().extract().response();
	
	
	
	try {
		JsonPath jsonPath = response.jsonPath();
		String name = jsonPath.getString("name");
		
		System.out.println(name);
	//	assertThat(name, equalTo("Luke Skywalker"));
		
		String height = jsonPath.getString("height");
        //assertThat(height, is(greaterThan(170)));
		
	}catch(Exception e) {
		
		e.printStackTrace();
	}
	
}
}