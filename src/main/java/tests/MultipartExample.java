package tests;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class MultipartExample {

	@Test
	public void multipartTest() {
		
		Response response  = given().contentType(ContentType.JSON).body("{\"email\":\"test@test.com\",\"password\":\"123456\"}").
				post("https://keytrainingtravelshop.herokuapp.com/api/users/login").then().extract().response();
		
		Map<String, String> cookies =response.cookies();
		
		File fisier = new File("cat-1455468_640.jpg");
		Response response2 = given().cookies(cookies).multiPart(new File("download.jpeg")).multiPart(fisier).when().
				post("https://keytrainingtravelshop.herokuapp.com/api/product/uploadImage").then().extract().response();
		
		
		System.out.println(response2.asString());
		
		
		
	}
}
