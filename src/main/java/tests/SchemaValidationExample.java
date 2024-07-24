package tests;

import io.restassured.response.ResponseBody;
import testData.DataBuilder;
import utils.BaseComponent;
import static org.junit.Assert.assertThat;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationExample extends BaseComponent  {
	
	public void testResponseSchema() {
		
		@SuppressWarnings("rawtypes")
		ResponseBody resp = doPostRequest("api/users", DataBuilder.buildUser().toJSONString(),201);
		
		
		assertThat(resp.asString(),matchesJsonSchemaInClasspath("schema.json"));
		
		
	}

	
}
