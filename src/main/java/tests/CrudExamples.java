package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.response.Response;

//https://keytodorestapi.herokuapp.com/api/delete/66955026918f8b0014c
public class CrudExamples   {
	JSONObject requestbody =  new JSONObject();
	JSONObject requestbodyUpdate =  new JSONObject();
	String id;
	/*
	 * //https://keytodorestapi.herokuapp.com

	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI="https://keytodorestapi.herokuapp.com/";
		
		Faker fake  = new Faker();
		requestbody.put("title",fake.food().dish());
		requestbody.put("body",fake.chuckNorris().fact());
		requestbodyUpdate.put("title",fake.food().dish());
		requestbodyUpdate.put("body",fake.chuckNorris().fact());
		
	}
	@Test(priority=1)
	public void createTodo() {
	Response response = given().
		//header("Content-Type","application/json")
			contentType(ContentType.JSON).
			body(requestbody.toJSONString()).
		//when().
				post("api/save").
		then().
				statusCode(200).
				body("info", equalTo("Todo saved! Nice job!")).
				body("id", anything()).
				log().all().extract().response();
		
		id= response.jsonPath().getString("id");
		System.out.println(id);
		
		
		
	}
	
	@Test(priority=2)
	public void geToDO() {
	Response response = given().get("api/"+id).then()
			.statusCode(200).
			extract().response();
		
		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());
		System.out.println(response.jsonPath().getString("_id"));
		
		assertEquals(id, response.jsonPath().getString("_id"));
		assertThat(id,is(equalTo(response.jsonPath().getString("_id"))));
		
	}
	
	
	@Test(priority=3)
	public void updateTODO() {
	Response response = given().body(requestbodyUpdate.toJSONString()).put("api/todos/"+id).then().extract().response();
	System.out.println(response.asPrettyString());
	System.out.println(requestbodyUpdate.toJSONString());
	}
	
	
	@Test(priority=4)
	public void deleteToDO() {
	Response response = given().delete("api/delete/"+id)
			.then().extract().response();
		
		System.out.println(response.asPrettyString());
	


		
	}
}
