package tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
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
		
		JsonPath jsonPath = response.jsonPath();
        List<String> pilots = jsonPath.getList("pilots");
        
       assertThat(pilots, is(instanceOf(String.class)));
       assertThat(pilots, is(empty()));
        
	
        List<String> films = jsonPath.getList("films");
        assertThat(films, is(not(instanceOf(String.class))));
        assertThat(films, is(not(empty())));
        
        
        String model = jsonPath.getString("model");
       assertThat(model, containsString("Imperial"));
       assertThat(model, containsString("Destroyer"));
        
        String name = jsonPath.getString("name");
        assertThat(name, not(matchesPattern("[a-zA-Z 0-9]+")));
        
        String crew = jsonPath.getString("crew");
        assertThat(crew, matchesPattern("\\d+")); 
		
        String passengers = jsonPath.getString("passengers");
        assertThat(passengers, matchesPattern("\\d+"));
        
        assertThat(responseBody.length(), greaterThanOrEqualTo(100));
    	}
	}
	
}
    	

