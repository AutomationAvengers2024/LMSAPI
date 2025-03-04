@Test
Feature: Testing Delete request in User API 

  Scenario: Successfully delete an existing user with userid
    Given Admin has a valid user to delete with user ID 
    When Admin sends a DELETE request with userid to valid endpoint 
    Then The API should return a status code 200 and the user record should be deleted
    
 Scenario: Successfully delete an existing user with user firstName
    Given Admin has a valid user to delete with user first name
    When Admin sends a DELETE request with user first name to valid endpoint 
    Then The API should return a status code 200 and the user record should be deleted   
    

  Scenario: Try to delete a non-existent user
    Given Admin has a non-existent user ID 
    When Admin sends a DELETE request to delete the non-existent user 
    Then The API should return a status code 404 Not Found.
    
  Scenario: Testing Delete request with invalid url
  Given Admin has invalid credentials for delete request
  When Admin sends a delete request to the invalid url
  Then The API should return a status code 404 Not Found.
  
  Scenario: Testing delete request without authorization
  Given Admin has a valid credentials
  When Admin sends a Delete request without authorization
  Then The Api should return  status code 401 unauthorized.
    