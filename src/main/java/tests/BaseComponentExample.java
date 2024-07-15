package tests;

import static org.testng.Assert.assertEquals;

import io.restassured.response.Response;
import testData.DataBuilder;
import utils.BaseComponent;

public class BaseComponentExample extends BaseComponent {

	@Test(priority=1)
	public void createNewUser() {
		
		Response response = doPostReqest("api/Users",DataBuilder.buildUser().toJSONString(),200);
		
		assertEquals(response.jsonPath().getString("success"),"true");
	}
	
}
