package stepdefinition;

import Utilities.Assertion;
import Utilities.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import Pojo.User;

public class deleteUser {
	private Response response;
	private int userId;
	private String userName;
	
	
	
	@Given("Admin has a valid user to delete with user ID")
	public void admin_has_a_valid_user_to_delete_with_user_id() {
		userId=User.getUser_Id();
		
	    	}
	@When("Admin sends a DELETE request with userid to valid endpoint")
	public void admin_sends_a_delete_request_with_userid_to_valid_endpoint() {
		
		response = RestAssured.given()
		        .auth()
		        .basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword()) // API Auth
		        .baseUri(ConfigReader.getApiUrl()) // Base URI
		        .basePath("uap/deleteuser/"+userId) // Endpoint with a path param for userId
		         // Pass the userId in the path
		        .when()
		        .delete();
	    
	}
	@Then("The API should return a status code {int} and the user record should be deleted")
	public void the_api_should_return_a_status_code_and_the_user_record_should_be_deleted(Integer int1) {
		Assertion.validateStatusCode(response, 200);
	}
	

@Given("Admin has a valid user to delete with user first name")
public void admin_has_a_valid_user_to_delete_with_user_first_name() {
	userName="Sathish";
    
}
@When("Admin sends a DELETE request with user first name to valid endpoint")
public void admin_sends_a_delete_request_with_user_first_name_to_valid_endpoint() {
	response = RestAssured.given()
	        .auth()
	        .basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword()) // API Auth
	        .baseUri(ConfigReader.getApiUrl()) // Base URI
	        .basePath("uap/deleteuser/username/"+userName) // Endpoint with a path param for userId
	         // Pass the userId in the path
	        .when()
	        .delete();

    
}

@Given("Admin has a non-existent user ID")
public void admin_has_a_non_existent_user_id() {
    userId=999;
}
@When("Admin sends a DELETE request to delete the non-existent user")
public void admin_sends_a_delete_request_to_delete_the_non_existent_user() {
	 response=RestAssured.given()
	        .auth()
	        .basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword()) // API Auth
	        .baseUri(ConfigReader.getApiUrl()) // Base URI
	        .basePath("uap/deleteuser/"+userId) 
	         
	        .when()
	        .delete();

    
}
@Given("Admin has invalid credentials for delete request")
public void admin_has_invalid_credentials_for_delete_request() {
	RestAssured.baseURI= ConfigReader.getinvalidUrl();
}
@When("Admin sends a delete request to the invalid url")
public void admin_sends_a_delete_request_to_the_invalid_url() {
	response=RestAssured.given()
    .auth()
    .basic(ConfigReader.getApiUsername(), ConfigReader.getApiPassword()) // API Auth
    
    .basePath("uap/deleteuser/"+userId) 
     
    .when()
    .delete();
	
	
	
    }
@Given("Admin has a valid credentials")
public void admin_has_a_valid_credentials() {
	RestAssured.baseURI= ConfigReader.getApiUrl();

}
@When("Admin sends a Delete request without authorization")
public void admin_sends_a_delete_request_without_authorization() {
	response=RestAssured.given()
    
    .baseUri(ConfigReader.getApiUrl()) // Base URI
    .basePath("uap/deleteuser/"+userId) 
     
    .when()
    .delete();
    
}

@Then("The API should return a status code {int} Not Found.")
public void the_api_should_return_a_status_code_not_found(Integer int1) {
	Assertion.validateStatusCode(response, 404);
    }
@Then("The Api should return  status code {int} unauthorized.")
public void the_api_should_return_status_code_unauthorized(Integer int1) {
	Assertion.validateStatusCode(response, 401);
}



}
