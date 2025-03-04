@Test
Feature: Get request in UserApi

  Scenario: GET all users successfully
    Given Admin create Get request with  valid credential
    When Admin sends a GET request to the valid endpoint
    Then The API should return a status code 200 and all the users with expected json response body 
    
    Scenario: GET request user by user firstname
    Given Admin create Get request with  valid credential
    When Admin sends a GET request to the valid endpoint with firstname
    Then The API should return a status code 200 OK
    
    Scenario: Get request by user id
    Given Admin create Get request with  valid credential
    When Admin sends a GET request to the valid endpoint with userid
    Then The API should return a status code 200 OK
    
    Scenario: Retrieve all users with invalid url
    GivenAdmin create Get request with  invalid credential
    When Admin sends a GET request to the invalid url
    Then The API should return a status code 404 Not Found
    
    Scenario: Retrieve all users without authorization
    Given Admin create Get request with  invalid credential
    When Admin sends a GET request without authorization
    Then The API should return a status code 401 Unauthorized
