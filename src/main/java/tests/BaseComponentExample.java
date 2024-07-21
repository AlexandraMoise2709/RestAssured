package tests;


import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import testData.DataBuilder;
import utils.BaseComponent;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;

public class BaseComponentExample extends BaseComponent {
	
	String id, name, email, age, gender;

	@Test(priority=1)
	public void createNewUser() {
		
		Response response = doPostRequest("api/users", DataBuilder.buildUser().toJSONString(),201);
				//buildUser().toJSONString(), 201);
		
		assertEquals(response.jsonPath().getString("success"), "true");
		assertThat(response.jsonPath().getString("success"), is("true"));
		id = response.jsonPath().getString("result._id");
		name = response.jsonPath().getString("result.name");
		email = response.jsonPath().getString("result.email");
		age = response.jsonPath().getString("result.age");
		gender = response.jsonPath().getString("result.gender");
	}
	@Test(priority = 2, dependsOnMethods = "createNewUser")
	public void readUser() {
		
		Response response = doGetRequest("api/users/" +id);
		System.out.println(response.asPrettyString());
		assertThat(response.jsonPath().getString("result.name"), is(equalTo(name)));
		assertThat(response.jsonPath().getString("result.email"), is(equalTo(email)));
		//assertThat(response.jsonPath().getString("result.age"), is(equalTo(age)));
		assertThat(response.jsonPath().getString("result.gender"), is(equalTo(gender)));
	
	}
	@Test(priority = 3, dependsOnMethods = "createNewUser")
	public void updateUser() {
		Response response =  doPutRequest("api/users/"+id, 
				DataBuilder.buildUpdatedUser().toJSONString(),200);
				//buildUpdatedUser().toJSONString(), 200);	
		assertThat(response.jsonPath().getString("success"), is("true"));
	}
	
	@Test(priority = 4, dependsOnMethods = "createNewUser")
	public void deleteUser() {	
		Response response = doDeleteRequest("api/users/"+id);
		assertThat(response.jsonPath().getString("msg"), is(equalTo("It has been deleted.")));

		
		
	}
	
	
}