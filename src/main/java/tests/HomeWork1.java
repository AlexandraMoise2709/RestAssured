package tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import org.junit.runner.Request;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class HomeWork1 {

	
	@Test
	public void postFakeJson() {
		
		Response response = given()
				.header("Content-Type","application/json")
				.body("{\r\n"
						+ "  \"id\": 9,\r\n"
						+ "  \"title\": \"string\",\r\n"
						+ "  \"dueDate\": \"2024-07-15T03:40:58.076Z\",\r\n"
						+ "  \"completed\": true\r\n"
						+ "}")
				.when()
				.post("https://fakerestapi.azurewebsites.net/api/v1/Authors")
				.then()
				.assertThat().statusCode(200).extract().response();
				assertTrue(response.asString().contains("9"));
				
		
		System.out.println(response.asString());
		System.out.println(response.asPrettyString());

		
	}
}
