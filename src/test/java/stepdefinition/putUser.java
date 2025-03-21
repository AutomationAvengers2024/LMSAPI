package stepdefinition;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;

import com.fasterxml.jackson.core.JsonProcessingException;

import Utilities.Assertion;
import Utilities.ConfigReader;
import Utilities.ExcelReader;
import Utilities.RequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import Pojo.User;

public class putUser {
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
	private String requestBody;
	int userId = User.getUser_Id();
	
	@Given("Admin has a valid\\/invalid request body for put operation from sheet name {string} at row number {int}")
	public void admin_has_a_valid_invalid_request_body_for_put_operation_from_sheet_name_at_row_number(String string, Integer int1) throws EncryptedDocumentException, IOException {
		// Get data from the Excel file
				List<LinkedHashMap<String, String>> dataFromExcel = ExcelReader.getExcelData(ConfigReader.getExcelPath(), "PutData");
				LinkedHashMap<String, String> userData = dataFromExcel.get(int1 - 1);  // Get the data for the specified row (1-based index)

				// Extract values from the map and assign them to instance variables
				userFirstName = userData.get("user_first_name");
				userLastName = userData.get("user_last_name");
				userContactNumber = userData.get("user_contact_number");
				userEmailId = userData.get("user_email_id");
				plotNumber = userData.get("plotNumber");
				street = userData.get("Street");
				state = userData.get("state");
				country = userData.get("Country");
				zipCode = userData.get("zipCode");

				// Extract expected status code and message from Excel
				expectedStatusCode = userData.get("status_code");

				// Assign them to instance variables (you may need them later for assertions)
				expectedStatusCodeInt = Integer.parseInt(expectedStatusCode);    	
	
	
	}
	@When("Admin sends a PUT request to the valid endpoint")
	public void admin_sends_a_put_request_to_the_valid_endpoint() throws JsonProcessingException {
		requestBody = RequestBody.buildRequestBody(userFirstName, userLastName, userContactNumber, 
				userEmailId, plotNumber, street, state, country, zipCode);

		// Send the POST request
		response = RestAssured.given()
				.auth()
				.basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword())
				.baseUri(ConfigReader.getApiUrl())
				.contentType("application/json")
				.body(requestBody)
				.when()
				.put("uap/updateuser/"+userId);
	}
	@Then("The API should return corresponding status code")
	public void the_api_should_return_corresponding_status_code() {
		Assertion.validateStatusCode(response, expectedStatusCodeInt);

	}
	

@Given("Admin has a valid request body for Put request")
public void admin_has_a_valid_request_body_for_put_request() throws JsonProcessingException {
	
	requestBody = RequestBody.buildRequestBody("Amudha", "Sathish","4255246717", 
			"Team01Amudha@gmail.com", "A-05", "South", "Ohio", "USA", "425227");
    
}
@When("Admin sends a Put request to the invalid url")
public void admin_sends_a_put_request_to_the_invalid_url() {
	response = RestAssured.given()
			.auth()
			.basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword())
			.baseUri(ConfigReader.getinvalidUrl())
			.contentType("application/json")
			.body(requestBody)
			.when()
			.put("uap/updateuser/"+userId);
   
}
@Then("The Api should return the status code {int} Not Found.")
public void the_api_should_return_the_status_code_not_found(Integer int1) {
	Assertion.validateStatusCode(response, 404);
    }


@When("Admin sends a PUT request to the invalid endpoint")
public void admin_sends_a_put_request_to_the_invalid_endpoint() {
	response = RestAssured.given()
			.auth()
			.basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword())
			.baseUri(ConfigReader.getApiUrl())
			.basePath(ConfigReader.getputinvalidendpoint())
			.contentType("application/json")
			.body(requestBody)
			.when()
			.put();
    
}

@When("Admin sends a PUT request without authorization")
public void admin_sends_a_put_request_without_authorization() {
	response = RestAssured.given()
			
			.baseUri(ConfigReader.getApiUrl())
			
			.contentType("application/json")
			.body(requestBody)
			.when()
			.put("uap/updateuser/"+userId);

    
}
@Then("The Api should return the status code {int} unauthorized.")
public void the_api_should_return_the_status_code_unauthorized(Integer int1) {
	Assertion.validateStatusCode(response, 401);

    
}


}