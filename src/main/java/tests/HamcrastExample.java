package tests;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrastExample {

	@Test
	public void hamcrastExample() {
		
		
		Response response = given().get("https://swapi.dev/api/planets/1/").then().extract().response();
		System.out.println(response.asPrettyString());
		
		//response.jsonPath().getString("name");
		
		JsonPath json = response.jsonPath();
		json.getString(null);
		
	String 	name = json.getString("name")
;				
				assertEquals(name,"Tatooine");
				assertThat(name,equalTo("sss"));
				assertThat(name,is("tatooine"));
				assertThat(name,is(equalTo("tatooine")));
}

	}
