package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.List;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestExample {

	
	@Test
	public void hamcrestTest() {
		
		Response response = given().get("https://swapi.dev/api/planets/1/").
				then().extract().response();
		
		System.out.println(response.asPrettyString());
		
		
		
		JsonPath json = response.jsonPath();
		//response.jsonPath().getString("name");
		String name = json.getString("name");
		System.out.println(name);
		//TestNG
		assertEquals(name, "Tatooine");
		//Hamcrest
		//assertThat(name, "Tatooine");
		assertThat(name, equalTo("Tatooine"));
		assertThat(name, is("Tatooine"));//is(T value)
		assertThat(name, is(equalTo("Tatooine")));//is(Matcher<T>)
	
		//TestNg
		assertNotEquals(name, "Mars");
		//hamcrest
		assertThat(name, is(not("Mars")));
		assertThat(name, is(not(equalTo("Mars"))));
		assertThat(name, is(not(instanceOf(Integer.class))));
		
		assertThat(name, is(instanceOf(String.class)));

		assertThat(name, is(notNullValue()));
		
		String text = "";
		String text2 = null;

		System.out.println("Lenghtul lui text :" +text.length());	
		//assertThat(text, is(emptyOrNullString()));
		//assertThat("", is(emptyString()));
		//assertThat(text, isEmptyString());
		assertThat(text2, is(nullValue()));
		
		String name2 = "  Tatooine  ";
		assertThat(name, is(equalToCompressingWhiteSpace(name2)));
		
		//startsWith -- endsWith
		assertThat(name, startsWith("Tat"));
		assertThat(name, endsWith("ine"));
		System.out.println(response.asString());
		assertThat(response.asString(), startsWith("{\"name\":\"Tatooine\",\"rotation"));
		assertThat(response.asString(), startsWithIgnoringCase("{\"NAME\":\"TATOOINE\",\"rotation"));
		assertThat(response.asString(), endsWith("dev/api/planets/1/\"}"));
		assertThat(response.asString(), endsWithIgnoringCase("dEv/ApI/pLaNeTs/1/\"}"));

		String url = json.getString("url");
		//anyOf
		//assertThat(url, anyOf(startsWith("https"), containsString("123")));
		//allOf
		assertThat(url, allOf(startsWith("https"), containsString("swapi"), endsWith("/1/")));
		
		//pattern
		assertThat(name, matchesPattern("[a-zA-Z]+"));
		String gravity = json.getString("gravity");
		assertThat(gravity, matchesPattern("[a-zA-Z 0-9]+"));
		String diameter = json.getString("diameter");
		assertThat(diameter, matchesPattern("[0-9]+"));
		
		//and
		assertThat(response.asString(), both(containsString("terrain")).and(containsString("population")));
		//or
		assertThat(name, either(is("Tatooine")).or(is("Mars")).or(is("Terra")) );
		assertThat(response.asString(), either(containsString("abc")).or(containsString("population")));

		
		List<String> films	= json.getList("films");
		System.out.println(films.get(1));
		assertThat(films, contains("https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"));
		
		assertThat(films, hasSize(5));
		assertThat(films, hasSize(lessThan(10)));
		assertThat(films, hasSize(graterThan(3)));
	}

	private Matcher graterThan(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}