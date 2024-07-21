package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

@SuppressWarnings("unused")
public class RestExample {
	
	@SuppressWarnings("unchecked")
	@Test
	public void restExampleTest() {
		
		Faker fakeData = new Faker();
		
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", fakeData.artist().name());
		requestBody.put("email", fakeData.internet().emailAddress());
		requestBody.put("age", "45");
		requestBody.put("gender", "m");

		File fisier =  new File("data.json");
		
		Response response = given()
				.headers("Content-Type", "application/json")
				//ex 1 --> direct in body as String
				/*.body("{\n"
							+ "    \"name\":\"Test\",\n"
							+ "    \"email\":\"test@test123334.com\",\n"
							+ "    \"age\":\"33\",\n"
							+ "    \"gender\":\"m\"\n"
							+ "    }") */
				//ex 2 --> body as JsonObject
				//.body(requestBody.toJSONString())
				//ex 3 --> body as file
				.body(fisier)
				.when()
					.post("https://keytrcrud.herokuapp.com/api/users/")
				.then()
				.assertThat().statusCode(201).extract().response();
		
		System.out.println(response);
		System.out.println(response.asString());
		System.out.println(response.asPrettyString());
		String msg = response.jsonPath().getString("msg");
		assertEquals(msg, "Successfully added!");
		String email = response.jsonPath().getString("result.email");
		System.out.println(email);
	}
	

}