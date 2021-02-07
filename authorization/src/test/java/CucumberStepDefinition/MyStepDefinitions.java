package CucumberStepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyStepDefinitions {

	private static final String BASE_URL = "http://localhost:8084/authapp";
	String username = "user";
	String password = "pass";

	RequestSpecification request;
	private static Response response;
	private static String token;

	@Given("^Post Login$")
	public void post_login() throws Throwable {
		RestAssured.baseURI = BASE_URL;
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
	}
	
	 @When("^Provide Valid Credential with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	    public void provide_valid_credential_with_username_something_and_password_something(String strArg1, String strArg2) throws Throwable {
		 response = request.body("{ \"username\":\"" + strArg1 + "\", \"password\":\"" + strArg2 + "\"}")
					.post("/login");
		 if(response.getStatusCode()==200) {
		 String jsonString = response.asString();
			token = JsonPath.from(jsonString).get("authToken");
		 }
			log.debug("token : " + token);
			log.debug("Add URL : " + RestAssured.baseURI);
	   }

	@When("^Provide Valid token$")
	public void provide_valid_token() throws Throwable {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

		response = request.get("/validate");
		log.debug("User gets validation");
	}

	@When("^Provide inValid token$")
	public void provide_invalid_token() throws Throwable {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token + "abcs").header("Content-Type", "application/json");

		response = request.get("/validate");
		log.debug("User is not valid");
	}

	@Then("^Status_code equals \"([^\"]*)\"$")
	public void statuscode_equals_something(String strArg1) throws Throwable {
		assertEquals(Integer.parseInt(strArg1), response.getStatusCode());
	}

}