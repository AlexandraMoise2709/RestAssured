package tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class ParseJson {

	@Test
	public void parseJson() throws IOException, ParseException {
		//1.parser de json
		JSONParser parserObj = new JSONParser();
		//2. incarcam fisierul json
		FileReader reader =  new FileReader("data3.json");
		//3 parserul primeste fisierul
		Object obj = parserObj.parse(reader);
		//4 punem continutul intr-un json array
		JSONArray employeeList = (JSONArray) obj;
		System.out.println(employeeList);
		//5 citim un JsonObject din Json Array
		JSONObject employeeObject = (JSONObject) employeeList.get(0);
		System.out.println(employeeObject);
		
		String employeeAttributeDate = (String) employeeObject.get("date");
		System.out.println(employeeAttributeDate);
		
		JSONObject employeeAttribute = (JSONObject) employeeObject.get("employee");
		System.out.println(employeeAttribute);
		
		System.out.println(employeeAttribute.get("company"));
		System.out.println(employeeAttribute.get("firstName"));

		System.out.println("---------------------------------------------");
		
		File jsonFile = new File("data3.json");
		JsonPath jsonPath = JsonPath.from(jsonFile);
		
		
		System.out.println(jsonPath.getString("[0]"));
		System.out.println(jsonPath.getString("[0].employee.company"));
		
	}
	
	
}