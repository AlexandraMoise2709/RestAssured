package tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.*;

		

public class HomeWork3 {
	

	
	@Test(priority=1 )
	public void testStarship() {
		
		List<String> route = new ArrayList<>(); 
    	route.add("films/2/");
    	route.add("films/6/");
    	route.add("films/5/");
    	
    	for (String path : route) {
		Response response = given().get("https://swapi.dev/api/"+ path).then().extract().response();
		String responseBody = response.asString();
		assertThat("response body contains starship", responseBody, containsString("starship"));		
		
    	}
    	
	}
    	@Test(priority=2)
    	public void testStarshipInstannce() {
    		
    		Response response = given().get("https://swapi.dev/api/starships/3/").then().extract().response();
    		
    		JsonPath jsonPath = response.jsonPath();
            List<String> pilots = jsonPath.getList("pilots");
            
           assertThat(pilots, is(instanceOf(List.class)));
           assertThat(pilots, is(empty()));
            
    	
           List<String> films = jsonPath.getList("films");
           assertThat(films, is(instanceOf(List.class)));
           assertThat(films, is(notNullValue()));
            
            
            String model = jsonPath.getString("model");
           assertThat(model, containsString("Imperial"));
           assertThat(model, containsString("Destroyer"));
            
           String name = jsonPath.getString("name");
           assertThat(name, matchesPattern("[a-zA-Z 0-9]+"));
            
           String crew = jsonPath.getString("crew");
           assertThat(crew, matchesPattern("[\\d,]+"));
    		
           String passengers = jsonPath.getString("passengers");
           assertThat(passengers,  equalTo("n/a"));
            
            String responseBody = response.asString();
            assertThat(responseBody.length(), not(lessThan(100)));
    		
    		
    		
    	}
	}


