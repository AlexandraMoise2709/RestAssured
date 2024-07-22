package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SuppressWarnings("unchecked")
public class Homework2 {
	JSONObject requestBody, requestBodyUpdate;
	String id,name, trips;
	
	
	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI = "https://api.instantwebtools.net/";
	
		requestBody = new JSONObject();
		requestBodyUpdate = new JSONObject();

		requestBody.put("name", "Tarom");
		requestBody.put("country", "Romania");
		requestBody.put("logo", "https://commons.wikimedia.org/wiki/File:TAROM_Logo_tail.svg");
		requestBody.put("slogan", "From Romania");		
		requestBody.put("head_quaters","Bucharest Romania");
		requestBody.put("website", "www.tarom.com");		
		requestBody.put("established", "1990");	
		

		
	}
	
	@Test(priority=1)
	public void createNewAirline() {
		
	Response response =	
						given().
								contentType(ContentType.JSON).
								body(requestBody.toJSONString()).
						when().
								post("v1/airlines/").
						then().
								statusCode(200).
								body("name", equalTo(requestBody.get("name"))).
								body("_id", anything()).
							log().all().extract().response();
	
		id = response.jsonPath().getString("_id");
		name = response.jsonPath().getString("name");
		

	}
	
    @Test(priority=2, dependsOnMethods = "createNewAirline")
	public void updateTicket() {
		Faker fakeData = new Faker();		
		
		JSONObject requestBodyUpdate = new JSONObject();
		requestBodyUpdate.put("name", fakeData.artist().name());
		requestBodyUpdate.put("trips", "250");
		requestBodyUpdate.put("airline", id);
	
	}
	
    @Test(priority=3, dependsOnMethods = "updateTicket")
	public void updateExistingTicket () {
    	Response response =	
    						given().
    								contentType(ContentType.JSON)
    								.body(requestBodyUpdate.toJSONString()).
    						when().
    								post("v1/passenger/").
    						then().
    							statusCode(200).
    							body("name", equalTo(requestBodyUpdate.get("name"))).		
    						log().all().extract().response();
    	
		id = response.jsonPath().getString("_id");
		name = response.jsonPath().getString("name");
		trips = response.jsonPath().getString("trips"); 
		
	}
	
    @Test(priority=4, dependsOnMethods = "updateExistingTicket")
	public void getTicket () {

    	Response response = given().
    								get("v1/passenger/"+id).
    						then().
    								statusCode(200).
    								extract().response();

				assertEquals(name, response.jsonPath().getString("name"));
				assertEquals(trips, response.jsonPath().getString("trips"));

		
	}
    //https://api.instantwebtools.net/v1/passenger/
    @SuppressWarnings("unused")
	@Test(priority=5, dependsOnMethods = "updateExistingTicket")
	public void deleteTicket () {

    	Response response = given().
    								delete("v1/passenger/"+id).
    						then().
    								statusCode(201).
    								body("info", equalTo("Passenger data deleted successfully.")).
    								extract().response();

	//Passenger data deleted successfully.

		
	}
    
}
