package tests;

import org.codehaus.groovy.control.customizers.builder.PostCompletionFactory;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredAuthExample {

	//@Test
	public void basicAuth() {
	/*	
		Response response = given().
				contentType(ContentType.JSON). 
				body("{\"username\" : \"admin\",\"password\" : \"password123\"}"). 
				post("https://restful-booker.herokuapp.com/auth"). 
				then().extract().response();
		
		System.out.println(response.asPrettyString());
		String token = response.jsonPath().getString("token");*/
		
		Response responseDel = given(). 
				contentType(ContentType.JSON).
				auth().preemptive().basic("admin", "password123").
				delete("https://restful-booker.herokuapp.com/booking/452").
				then().extract().response();
		
		System.out.println(responseDel.asPrettyString());
		
	}
	
	//@Test
	public void formAuth() {
		
		Response response = given(). 
				formParam("username", "customer"). 
				formParam("password", "customer@123"). 
				formParam("woocommerce-login-nonce", "e55aefbdfb"). 
				formParam("_wp_http_referer", "/my-account-2/").
				formParam("login", "Log+in").
				when().
				redirects().follow(true).redirects().max(100).
				post("https://keyfood.ro/my-account-2/").
				then().extract().response();
		
		System.out.println(response.asString());
		
		Map<String, String> cookies = response.cookies();

		System.out.println(cookies);
		System.out.println(cookies.size());
		
		
		Response response2 = given()
				.cookies(cookies)
				.get("https://keyfood.ro/my-account-2/orders/")
				.then().extract().response();
		
		//System.out.println(response2.asString());
		
		assertTrue(response2.asString().contains("#2859"));
		
		
		Response response3 = given()
				.cookies(cookies). 
				get("https://keyfood.ro/my-account-2/view-order/2857/"). 
				then().extract().response();
		
		System.out.println(response3.asString());
		
		assertTrue(response3.asString().contains("Order #<mark class=\"order-number\">2857</mark> was placed on <mark class=\"order-date\">July 8, 2024</mark> and is currently <mark class=\"order-status\">Processing</mark>.</p>"));
		
	}
	
	@Test
	public void bearerAuth() {
		
		Response responseToken = given(). 
				contentType(ContentType.JSON). 
				body("{\"user\":\"admin\",\"pass\": \"admin@123\"}"). 
				post("https://dev-todo-b369f85c9f07.herokuapp.com/api/login")
				.then().extract().response();
				
		String token = responseToken.jsonPath().getString("token");
		System.out.println(responseToken.asPrettyString());
		
		Response responseProfile = given(). 
				//auth().oauth2(token).
				header("Authorization", "Bearer " + token).
				get("https://dev-todo-b369f85c9f07.herokuapp.com/api/api/profile"). 
				then().extract().response();
		
		System.out.println(responseProfile.asPrettyString());
		
	}
	
}