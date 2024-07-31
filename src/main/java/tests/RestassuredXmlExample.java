package tests;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;



public class RestassuredXmlExample {

	@Test
	public void testXmlResponse() {
		
		Response response = given().get("https://run.mocky.io/v3/a6271be8-f3cb-4eda-ac09-4365c971b224").then().extract().response();
	
		System.out.println(response.xmlPath().getList("catalog"));
		System.out.println(response.xmlPath().getList("catalog.book[0]"));
		//https://run.mocky.io/v3/a6271be8-f3cb-4eda-ac09-4365c971b224
		System.out.println(response.xmlPath().getList("catalog.book[0].author"));
		System.out.println(response.xmlPath().getList("catalog.book[1].author"));
	}
}
