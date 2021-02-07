package CucumberStepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.And;
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

	String username = "user";
	String password = "pass";
	private static final String BASEURL = "http://localhost:8084/authapp";
	private static final String LoanBASEURL = "http://localhost:8083/loan";

	RequestSpecification request;
	private static String token;
	private static Response response;

	@Given("^User is logged in and authorized$")
	public void user_is_logged_in_and_authorized() throws Throwable {

		RestAssured.baseURI = BASEURL;
		request = RestAssured.given();
		request.header("Content-Type", "application/json");

		response = request.body("{ \"username\":\"" + username + "\", \"password\":\"" + password + "\"}")
				.post("/login");

		String jsonString = response.asString();
		token = JsonPath.from(jsonString).get("authToken");

		log.debug("token : " + token);
		log.debug("Add URL : " + RestAssured.baseURI);
	}

	@And("^Database has loan Details$")
	public void database_has_loan_details() throws Throwable {

		RestAssured.baseURI = LoanBASEURL;

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

		response = request.body(
				"{\"loanNumber\":\"1\",\"propertyAddress\":\"addd\",\"loanType\":\"home\",\"loanAmount\":\"2333\",\"loanTerm\":\"3\",\"loanStatus\":\"active\",\"loanManagementFees\":\"3\",\"originationDate\":\"2020-12-02\",\"lienInfo\":\"y\",\"legalDocument\":\"pan\",\"loanHistory\":\"his\",\"fname\":\"a\",\"lname\":\"b\"}")
				.post("/new");

	}

	@When("^User enters loan Details$")
	public void user_enters_loan_details() throws Throwable {

		RestAssured.baseURI = LoanBASEURL;

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

		response = request.body(
				"{\"loanNumber\":\"1\",\"propertyAddress\":\"addd\",\"loanType\":\"home\",\"loanAmount\":\"2333\",\"loanTerm\":\"3\",\"loanStatus\":\"active\",\"loanManagementFees\":\"3\",\"originationDate\":\"2020-12-02\",\"lienInfo\":\"y\",\"legalDocument\":\"pan\",\"loanHistory\":\"his\",\"fname\":\"a\",\"lname\":\"b\"}")
				.post("/new");
		log.debug("User enters loan details and save to database");

	}

	@When("^User enters loan Number$")
	public void user_enters_loan_number() throws Throwable {

		RestAssured.baseURI = LoanBASEURL;

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

		response = request.pathParameter("id", 1).get("/get/{id}");
		log.debug("User enters loan Number and get loan details");

	}

	@When("^User enters loan Number to delete loan Details$")
	public void user_enters_loan_number_to_delete_loan_details() throws Throwable {

		RestAssured.baseURI = LoanBASEURL;

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

		response = request.pathParameter("id", 1).delete("/delete/{id}");
		log.debug("User enters loan Number and delete loan Details");
	}

	@When("^User enters loan Number and update loan Details$")
	public void user_enters_loan_number_and_update_loan_details() throws Throwable {

		RestAssured.baseURI = LoanBASEURL;

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

		response = request.pathParameter("id", 1).body(
				"{\"loanNumber\":\"1\",\"propertyAddress\":\"ad\",\"loanType\":\"home\",\"loanAmount\":\"2333\",\"loanTerm\":\"3\",\"loanStatus\":\"active\",\"loanManagementFees\":\"3\",\"originationDate\":\"2020-12-02\",\"lienInfo\":\"y\",\"legalDocument\":\"pan\",\"loanHistory\":\"his\",\"fname\":\"a\",\"lname\":\"b\"}")
				.put("/edit/{id}");
		log.debug("User enters loan Number and updates loan Details");

	}

	@Then("^Response Status Ok returned$")
	public void response_status_ok_returned() throws Throwable {

		assertEquals(200, response.getStatusCode());
	}

}