package stepdefinition;

import Utilities.Assertion;
import Utilities.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import Pojo.User;

public class getUser {
	private Response response;
	
	@Given("Admin create Get request with  valid credential")
	public void admin_has_valid_credentials() {
		
	 RestAssured.baseURI= ConfigReader.getApiUrl();
		
	    	}
	@When("Admin sends a GET request to the valid endpoint")
	public void admin_sends_a_get_request_to_the_valid_endpoint() {
		response = RestAssured.given()
                .auth()
                .basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword())  
                .contentType("application/json")  
                .when()
                .get(ConfigReader.getGetAllUsersEndpoint());	
		}
	@Then("The API should return a status code {int} and list of users with expected json response body")
	public void the_api_should_return_a_status_code_and_list_of_users_with_expected_json_response_body(Integer int1) {
		Assertion.validateStatusCode(response, 200);
		String responseBody = response.getBody().asString();
		System.out.println("Response Body: " + responseBody);
	}

@When("Admin sends a GET request to the valid endpoint with firstname")
public void admin_sends_a_get_request_to_the_valid_endpoint_with_firstname() {
	response = RestAssured.given()
            .auth()
            .basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword())  
            .contentType("application/json")  
            .when()
            .get(ConfigReader.getuserbyfirstname());	
    
}
@Then("The API should return a status code {int} and corresponding user details")
public void the_api_should_return_a_status_code_and_corresponding_user_details(Integer int1) {
	Assertion.validateStatusCode(response, 200);
	String responseBody = response.getBody().asString();
	System.out.println("Response Body: " + responseBody);

}

@When("Admin sends a GET request to the valid endpoint with userid")
public void admin_sends_a_get_request_to_the_valid_endpoint_with_userid() {
	int userId = User.getUser_Id();
    System.out.println("Using User ID: " + userId);
	response = RestAssured.given()
            .auth()
            .basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword())  
            .contentType("application/json")  
            .when()
            .get("uap/user/"+userId);	

	
    }

@Given("Admin has invalid credentials")
public void admin_has_invalid_credentials() {
	RestAssured.baseURI= ConfigReader.getinvalidUrl();
}
@When("Admin sends a GET request to the invalid url")
public void admin_sends_a_get_request_to_the_invalid_url() {
	response = RestAssured.given()
            .auth()
            .basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword())  
            .contentType("application/json")  
            .when()
            .get(ConfigReader.getGetAllUsersEndpoint());	
   }
@Then("The API should return a status code {int} Not Found")
public void the_api_should_return_a_status_code_not_found(Integer int1) {
	Assertion.validateStatusCode(response, 404);
	
    }

@When("Admin sends a GET request without authorization")
public void admin_sends_a_get_request_without_authorization() {
	response = RestAssured.given()
            
            .contentType("application/json")  
            .when()
            .get(ConfigReader.getGetAllUsersEndpoint());	

    }
@Then("The API should return a status code {int} Unauthorized")
public void the_api_should_return_a_status_code_unauthorized(Integer int1) {
	Assertion.validateStatusCode(response, 401);

   }









}