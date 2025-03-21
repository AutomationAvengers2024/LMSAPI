package stepdefinition;


	import static org.testng.Assert.assertTrue;

	import java.io.IOException;
	import java.util.LinkedHashMap;
	import java.util.List;

	import org.apache.poi.EncryptedDocumentException;
	import org.testng.Assert;

	import com.fasterxml.jackson.core.JsonProcessingException;

	import Utilities.Assertion;
	import Utilities.ConfigReader;
	import Utilities.ExcelReader;
	import Utilities.RequestBody; // Import the RequestBodyBuilder
	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import io.restassured.RestAssured;
	import io.restassured.response.Response;
	import Pojo.User;

	public class postUser {

		private String userFirstName;
		private String userLastName;
		private String userContactNumber;
		private String userEmailId;
		private String plotNumber;
		private String street;
		private String state;
		private String country;
		private String zipCode;
		private Response response;
		private int expectedStatusCodeInt;
		private String expectedStatusCode;
		private int userid;
		private int addressid;
		private String requestBody;

		@Given("Admin has a valid\\/invalid request body from sheet name {string} at row number {int}")
		public void admin_has_a_valid_invalid_request_body_from_sheet_name_at_row_number(String string, Integer int1) throws EncryptedDocumentException, IOException {
			
			List<LinkedHashMap<String, String>> dataFromExcel = ExcelReader.getExcelData(ConfigReader.getExcelPath(), "UserData");
			LinkedHashMap<String, String> userData = dataFromExcel.get(int1 - 1); 

			
			userFirstName = userData.get("user_first_name");
			userLastName = userData.get("user_last_name");
			userContactNumber = userData.get("user_contact_number");
			userEmailId = userData.get("user_email_id");
			plotNumber = userData.get("plotNumber");
			street = userData.get("Street");
			state = userData.get("state");
			country = userData.get("Country");
			zipCode = userData.get("zipCode");

			
			expectedStatusCode = userData.get("status_code");

					expectedStatusCodeInt = Integer.parseInt(expectedStatusCode);
		}

		@When("Admin sends a POST request to the endpoint")
		public void admin_sends_a_post_request_to_the_endpoint() throws JsonProcessingException {
			
			requestBody = RequestBody.buildRequestBody(userFirstName, userLastName, userContactNumber, 
					userEmailId, plotNumber, street, state, country, zipCode);

			
			response = RestAssured.given()
					.auth()
					.basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword())
					.baseUri(ConfigReader.getApiUrl())
					.basePath(ConfigReader.getCreateUserEndpoint())
					.contentType("application/json")
					.body(requestBody)
					.when()
					.post();
			
	        
			if (response.getStatusCode() == 201) {
				if (response.getStatusCode() == 201) {
			        this.userid = response.jsonPath().getInt("user_id");  
			        User.setUserId(this.userid);  
			        System.out.println("User ID extracted: " + this.userid);  
			    }
			}
		}

		@Then("The API should return corresponding status code and status line")
		public void the_api_should_return_corresponding_status_code_and_status_line() {
			int statusnumber = Integer.parseInt(expectedStatusCode);
			if (statusnumber == 201) {
				Assertion.validateStatusCode(response, expectedStatusCodeInt);
				Assertion.validateUserandAddressId(response, userid, addressid);
				Assertion.validateUserFields(response, userFirstName, userLastName, userContactNumber, 
						userEmailId, zipCode, plotNumber, street, state, country);
				Assertion.validateTimeFields(response);
				Assertion.validateDataTypes(response);
				Assertion.validateResponseHeaders(response);
				
			} else {
				Assertion.validateStatusCode(response, expectedStatusCodeInt);
			}
		}

		@Given("Admin has a valid request body")
		public void admin_has_a_valid_request_body() throws JsonProcessingException {
			requestBody = RequestBody.buildRequestBody("Amudha", "Sathish","4255246717", 
					"Team01Amudha@gmail.com", "A-05", "South", "Ohio", "USA", "425227");
		}
		@When("Admin sends a POST request to the invalid url")
		public void admin_sends_a_post_request_to_the_invalid_url() {
			response = RestAssured.given()
					.auth()
					.basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword())
					.baseUri(ConfigReader.getinvalidUrl())
					.basePath(ConfigReader.getCreateUserEndpoint())
					.contentType("application/json")
					.body(requestBody)
					.when()
					.post();

		}
		@Then("The Api should return the status code {int}")
		public void the_api_should_return_the_status_code(Integer int1) {
			Assertion.validateStatusCode(response, 404);
		}
		

	@When("Admin sends a POST request to the invalid endpoint")
	public void admin_sends_a_post_request_to_the_invalid_endpoint() {
		response = RestAssured.given()
				.auth()
				.basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword())
				.baseUri(ConfigReader.getApiUrl())
				.basePath(ConfigReader.getinvalidpostendpoint())
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post();

	    }


	@When("Admin sends a POST request without authorization")
	public void admin_sends_a_post_request_without_authorization() {
		response = RestAssured.given()
				.baseUri(ConfigReader.getApiUrl())
				.basePath(ConfigReader.getCreateUserEndpoint())
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post();
	    }
	@Then("The Api should return the status code {int} unauthorized")
	public void the_api_should_return_the_status_code_unauthorized(Integer int1) {
		Assertion.validateStatusCode(response, 401);

	    
	}

	@Given("Admin has a valid request body with only mandatory fields")
	public void admin_has_a_valid_request_body_with_only_mandatory_fields() throws JsonProcessingException {
		requestBody = RequestBody.buildRequestBody("Sathish", "Kumar","4255246717", 
				"Team01Sathish@gmail.com");

		
	    }
	@When("Admin sends a POST request to the valid endpoint")
	public void admin_sends_a_post_request_to_the_valid_endpoint() {
		response = RestAssured.given()
				.auth()
				.basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword())
				.baseUri(ConfigReader.getApiUrl())
				.basePath(ConfigReader.getCreateUserEndpoint())
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post();
	    
	}
	@Then("The Api should return the status code {int} Created")
	public void the_api_should_return_the_status_code_created(Integer int1) {
		
		Assertion.validateStatusCode(response, 201);
	 
	}


	@Given("Admin has a valid request body without mandatory fields")
	public void admin_has_a_valid_request_body_without_mandatory_fields() throws JsonProcessingException {
		requestBody = RequestBody.buildRequestBody("A-05", "West", "Ohio", "USA", "42334");
	}
	@Then("The Api should return the status code {int} Bad Request")
	public void the_api_should_return_the_status_code_bad_request(Integer int1) {
		Assertion.validateStatusCode(response, 400);

	    
	}

	@Given("Admin has a  request body with existing emailid and contact number")
	public void admin_has_a_request_body_with_existing_emailid_and_contact_number() throws JsonProcessingException {
		requestBody = RequestBody.buildRequestBody("Anu", "Priya", "1234567901", 
				"anu123@gmail.com", "u-67", "Main", "Florida", "USA", "45227");

		
	    }
	@Then("The Api should return the status code {int} Conflict")
	public void the_api_should_return_the_status_code_conflict(Integer int1) {
		Assertion.validateStatusCode(response, 409);
	    }

	}
