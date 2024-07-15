package tests;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;


import org.json.simple.JSONObject;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.File;
public class RestExample {

	@SuppressWarnings("unchecked")
	@Test
	public void restExampleTest() {
		
		Faker fakedata = new Faker();
		JSONObject requestBody = new JSONObject();
		requestBody.put("name",fakedata.artist().name());
		requestBody.put("email",fakedata.internet().emailAddress());
		requestBody.put("age","23");
		requestBody.put("gender","m");
//		
		File fisier = new File("data.json");

		Response response = given()
				.header("Content-Type","application/json")
				//ex1-->direct in body as string
			/*	.body("{\r\n"
						+ "    \"name\": \"nume\",\r\n"
						+ "    \"email\": \"em3ail@email.com\",\r\n"
						+ "    \"age\": \"13\",\r\n"
						+ "    \"gender\": \"M\"\r\n"
						+ "}") */
				//ex2 --> body as JsonObject
				.body(requestBody.toJSONString()) 
				//.body(fisier) -->optiunea 3
				.when()
				.post("https://keytrcrud.herokuapp.com/api/users/")
				.then()
				.assertThat().statusCode(201).extract().response();
		
		System.out.println(response.asString());
		System.out.println(response.asPrettyString());
		String msg =  response.jsonPath().getString("msg");
		System.out.println(msg);
		String email = response.jsonPath().getString("result.email");
		System.out.println(email);
		
	}
}
