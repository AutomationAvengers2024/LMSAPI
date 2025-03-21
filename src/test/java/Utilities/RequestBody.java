package Utilities;

	import java.util.Optional;

	import com.fasterxml.jackson.core.JsonProcessingException;
	import com.fasterxml.jackson.databind.ObjectMapper;
	import Pojo.User;
	import Pojo.UserAddress;

	public class RequestBody {

	    // Method to create a request body from the user data
	    public static String buildRequestBody(String userFirstName, String userLastName, String userContactNumber, String userEmailId, 
	                                          String plotNumber, String street, String state, String country, String zipCode) 
	                                          throws JsonProcessingException {
	        
	        // Create the UserAddress object
	        UserAddress userAddress = new UserAddress();
	        userAddress.setPlotNumber(plotNumber);
	        userAddress.setStreet(street);
	        userAddress.setState(state);
	        userAddress.setCountry(country);
	        userAddress.setZipCode(zipCode);

	        // Create the User object and populate it
	        User user = new User();
	        user.setUser_first_name(userFirstName);
	        user.setUser_last_name(userLastName);
	        user.setUser_contact_number(userContactNumber);
	        user.setUser_email_id(userEmailId);
	        user.setUserAddress(userAddress);

	        // Convert the User object to a JSON string using Jackson ObjectMapper
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(user); // Return the JSON string
	    }
	    public static String buildRequestBody(String userFirstName, String userLastName, String userContactNumber, String userEmailId) 
	             
	    throws JsonProcessingException {

	// Create the UserAddress object


	// Create the User object and populate it
	User user = new User();
	user.setUser_first_name(userFirstName);
	user.setUser_last_name(userLastName);
	user.setUser_contact_number(userContactNumber);
	user.setUser_email_id(userEmailId);


	// Convert the User object to a JSON string using Jackson ObjectMapper
	ObjectMapper objectMapper = new ObjectMapper();
	return objectMapper.writeValueAsString(user); // Return the JSON string
	}
	    public static String buildRequestBody(String plotNumber, String street, String state, String country, String zipCode) 
	            throws JsonProcessingException {

	// Create the UserAddress object
	UserAddress userAddress = new UserAddress();
	userAddress.setPlotNumber(plotNumber);
	userAddress.setStreet(street);
	userAddress.setState(state);
	userAddress.setCountry(country);
	userAddress.setZipCode(zipCode);

	// Create the User object and populate it


	// Convert the User object to a JSON string using Jackson ObjectMapper
	ObjectMapper objectMapper = new ObjectMapper();
	return objectMapper.writeValueAsString(userAddress); // Return the JSON string
	}

	}

