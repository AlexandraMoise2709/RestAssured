package tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class JsonPathExample {

	@Test
	public void testJsonPath() {
		
		Response response = given().get("https://keytrcrud.herokuapp.com/api/users/").then().extract().response();
		
		System.out.println(response.asString());
		JsonPath json = response.jsonPath();
		
		System.out.println(json.getString("users"));
		//return the size of the array
		System.out.println(json.getString("users.size()"));
		
		System.out.println(json.getString("users.name"));
		System.out.println(json.getString("users.email"));
		System.out.println(json.getString("users._id"));
		
		System.out.println(json.getString("users.name[4]"));
		System.out.println(json.getString("users.name[0]"));
		System.out.println(json.getString("users[4].name"));
		System.out.println(json.getString("users[4]._id"));
		
		System.out.println("---------------------");
		//System.out.println(json.get);
		
		List<String> allFemale = json.getList("users.findAll{it.gender=='f'}");
		List<String> allFemales = json.getList("users.findAll{it.gender=='f'}.name");
		System.out.println(allFemales.get(0));
		
		System.out.println(allFemale.toString());
		List<String> allMales = json.getList("users.findAll{it.gender=='m'}");
		System.out.println(allMales.size());
		
		System.out.println("---------------------");
		String getAllFritz = json.getString("users.findAll{it.name=='Fritz'}._id");
		System.out.println(getAllFritz);
		
		String oldFritz = json.getString("users.findAll{it.age==115 & it.name =='Fritz'}._id");
		System.out.println("old " + oldFritz);
		
		List<String> allFritzAndHans = json.getList("users.findAll{it.name =='Fritz' | it.name == 'Hans'}");
		System.out.println(allFritzAndHans);
		System.out.println(allFritzAndHans.size());

	}
	
}
