
package Utilities;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import io.restassured.response.Response;

public class Assertion {
	
	//Validate Ids
	

	
    public static void validateUserandAddressId(Response response,int Userid,int Addressid)
    {
    	Integer userId = response.jsonPath().getInt("user_id");
        Assert.assertTrue(userId instanceof Integer, "user_id is not of type Integer");
        Integer addressId = response.jsonPath().getInt("userAddress.addressId");
        Assert.assertTrue(addressId instanceof Integer, "addressId is not of type Integer");
        System.out.println("userId is "+userId);
        System.out.println("address Id is"+addressId);
        
    }

    // Validate Status Code
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code doesn't match");
    }

    // Validate JSON response fields
    public static void validateUserFields(Response response, String userFirstName, String userLastName, 
                                           String userContactNumber, String userEmailId, String zipCode, 
                                           String plotNumber, String street, String state, String country) {
        
        String actualFirstName = response.jsonPath().getString("user_first_name");
        Assert.assertEquals(actualFirstName, userFirstName, "user_first_name doesn't match");

        String actualLastName = response.jsonPath().getString("user_last_name");
        Assert.assertEquals(actualLastName, userLastName, "user_last_name doesn't match");

        Long responseContactNumber = response.jsonPath().getLong("user_contact_number");
        String responseContactNumberStr = String.valueOf(responseContactNumber);
        Assert.assertEquals(responseContactNumberStr, userContactNumber, "Contact number doesn't match");

        String actualEmail = response.jsonPath().getString("user_email_id");
        Assert.assertEquals(actualEmail, userEmailId, "user_email_id doesn't match");

        int responseZipCode = response.jsonPath().getInt("userAddress.zipCode");
        String responseZipcodeStr = String.valueOf(responseZipCode);
        Assert.assertEquals(responseZipcodeStr, zipCode, "zipCode doesn't match");

        String actualPlotNumber = response.jsonPath().getString("userAddress.plotNumber");
        Assert.assertEquals(actualPlotNumber, plotNumber, "plotNumber doesn't match");

        String actualStreet = response.jsonPath().getString("userAddress.street");
        Assert.assertEquals(actualStreet, street, "Street doesn't match");

        String actualState = response.jsonPath().getString("userAddress.state");
        Assert.assertEquals(actualState, state, "State doesn't match");

        String actualCountry = response.jsonPath().getString("userAddress.country");
        Assert.assertEquals(actualCountry, country, "Country doesn't match");
    }

    // Validate Creation and Modification Time (ISO format)
    public static void validateTimeFields(Response response) {
        String creationTime = response.jsonPath().getString("creation_time");
        Assert.assertTrue(creationTime.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\+\\d{2}:\\d{2}"), "Creation time is not in valid ISO format");

        String lastModTime = response.jsonPath().getString("last_mod_time");
        Assert.assertTrue(lastModTime.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\+\\d{2}:\\d{2}"), "Last modification time is not in valid ISO format");
    }

    // Validate response headers
    public static void validateResponseHeaders(Response response) {
        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Content-Type header is incorrect");

        String server = response.getHeader("Server");
        Assert.assertEquals(server, "Cowboy", "Server header is incorrect");
    }

    // Validate Data Types
    public static void validateDataTypes(Response response) {
        Integer userId = response.jsonPath().getInt("user_id");
        Assert.assertTrue(userId instanceof Integer, "user_id is not of type Integer");

        Assert.assertTrue(response.jsonPath().getString("user_first_name") instanceof String, "user_first_name is not of type String");
        Assert.assertTrue(response.jsonPath().getString("user_last_name") instanceof String, "user_last_name is not of type String");
        Assert.assertTrue(response.jsonPath().getString("user_contact_number") instanceof String, "user_contact_number is not of type Long");
        Assert.assertTrue(response.jsonPath().getString("user_email_id") instanceof String, "user_email_id is not of type String");
        Assert.assertTrue(response.jsonPath().getString("creation_time") instanceof String, "creation_time is not of type String");
        Assert.assertTrue(response.jsonPath().getString("last_mod_time") instanceof String, "last_mod_time is not of type String");

        Integer addressId = response.jsonPath().getInt("userAddress.addressId");
        Assert.assertTrue(addressId instanceof Integer, "addressId is not of type Integer");

        Assert.assertTrue(response.jsonPath().getString("userAddress.plotNumber") instanceof String, "plotNumber is not of type String");
        Assert.assertTrue(response.jsonPath().getString("userAddress.street") instanceof String, "street is not of type String");
        Assert.assertTrue(response.jsonPath().getString("userAddress.state") instanceof String, "state is not of type String");
        Assert.assertTrue(response.jsonPath().getString("userAddress.country") instanceof String, "country is not of type String");
        Integer zipCode = response.jsonPath().getInt("userAddress.zipCode");

       // Assert.assertTrue(response.jsonPath().getInt("userAddress.zipCode") instanceof Integer, "zipCode is not of type Integer");
           }
   
    








        // Assert the status message contains "Not Found"
        //assertEquals("Expected status message to be 'Not Found'", "HTTP/1.1 404 Not Found", response.getStatusLine());

    }
