package tests;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;


public class Homework4 {

	
	@Test
	public void testAPI() {
	Response response = given().get("https://swapi.dev/api/people/1/").then().extract().response();	
	

		JsonPath jsonPath = response.jsonPath();
		String name = jsonPath.getString("name");
		
		System.out.println(name);
		assertThat(name, equalTo("Luke Skywalker"));
		
		String height = jsonPath.getString("height");
		assertThat(Integer.parseInt(height), greaterThan(171));
		
		String mass = jsonPath.getString("mass");
		assertThat(Integer.parseInt(mass), lessThan(80));
		
		String eyeColor = jsonPath.getString("eye_color");
		String hairColor = jsonPath.getString("hair_color");
		String skinColor = jsonPath.getString("skin_color");
		
		
	       assertThat((eyeColor+ ' ' +hairColor+' '+skinColor), equalTo("blue blond fair"));
	   
	       String birthYear = jsonPath.getString("birth_year");
	       
}


	}
