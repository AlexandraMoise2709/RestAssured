package tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent;

public class SentFromFile extends BaseComponent{
	
	@Test
	public void testJsonFile() throws FileNotFoundException, IOException, ParseException {
		
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("data2.json"));
		JSONArray userList = (JSONArray) obj;
		
		for(Object user : userList) {
			JSONObject userJson = (JSONObject) user;
			Response resp = doPostRequest("api/users", userJson.toJSONString(), 201);
			System.out.println(resp.asPrettyString());
			
		}
		
		
		
	}
	
	
	

}