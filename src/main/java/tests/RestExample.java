package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class RestExample {

	@Test
	public void restExampleTest() {
		
		Response response = given()
				.header("Content-Type","application/json")
				.body("{\r\n"
						+ "    \"name\": \"nume\",\r\n"
						+ "    \"email\": \"email@email.com\",\r\n"
						+ "    \"age\": \"13\",\r\n"
						+ "    \"gender\": \"M\"\r\n"
						+ "}")
				.when()
				.post("https://keytrcrud.herokuapp.com/api/users/")
				.then()
				.assertThat().statusCode(201).extract().response();
		
		System.out.println(response.asString());
		System.out.println(response.asPrettyString());

		
	}
}
