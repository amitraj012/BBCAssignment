package StepDefinitions;


import java.time.LocalDate;
import java.util.Dictionary;
import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;
import Helper.Helper;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class WeatherStepDefs {

	private RequestSpecification request;
	private Response response;
	private Dictionary weatherForcastDataDictionary;	
	private Helper helper = new Helper();

	@Before
	public void before(Scenario scenario) {
		request = RestAssured.with();
	}


	@Given("^I'm using the MetaWeather API$")
	public void i_m_using_the_MetaWeather_API() {
		// specify base URL
		request.given().contentType(ContentType.JSON)
				.baseUri("https://www.metaweather.com/api/");
	}

	@When("^I make a GET request to \"([^\"]*)\"$")
	public void i_make_a_GET_request_to(String path) {
		// invoking API's GET Method
		response = request.request(Method.GET, path);
	}


	@Then("^I verify the response code of (\\d+)$")
	public void i_verify_the_response_code_of(int code) {

		// getting status code into a variable
		int statusCode = response.getStatusCode();
		
		// validating status code
		Assert.assertEquals(code, statusCode);
		System.out.println("Status code is matching with expected value : "
				+ code);
	}
	
	
	@When("^I generate the weather forcast Dictionary$")
	public void i_generate_the_weather_forcast_Dictionary(){
		// getting the response JSON body into a variable
		String jsonResponseBody = response.asString();
		
		//Creating WeatherForcast Dictionary
		weatherForcastDataDictionary = helper.GenerateDataDictionary(jsonResponseBody,
				"consolidated_weather", "applicable_date", "weather_state_name");
}
	

	@Then("^I verify the expected weather \"([^\"]*)\" for the Day \"([^\"]*)\"$")
	public void i_verify_the_expected_weather_for_the_Day(String expectedWeather, String day) {
		//Creating the Date for the each day of forecast 
		
		if(day.contains("Today"))
		{
			day = LocalDate.now().toString();
		}
		if(day.contains("Tomorrow"))
		{
			day = LocalDate.now().plusDays(1).toString();
		}
		if(day.contains("Tomorrow+1"))
		{
			day = LocalDate.now().plusDays(2).toString();
		}
		if(day.contains("Tomorrow+2"))
		{
			day = LocalDate.now().plusDays(3).toString();
		}
		
		//validating Forecast based on the day
		Assert.assertEquals("Expected Weather Forcast is matching with the actual forcast", expectedWeather, weatherForcastDataDictionary.get(day));
	}
	
}