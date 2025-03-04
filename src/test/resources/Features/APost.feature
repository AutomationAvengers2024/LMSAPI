@Test
Feature: Testing POST request in UserAPI 

  Scenario Outline: Testing POST request with valid and invalid data
    Given Admin has a valid/invalid request body from sheet name "<sheetName>" at row number <rowNumber>
    When Admin sends a POST request to the endpoint 
    Then The API should return corresponding status code and status line

   Examples:
  | sheetName  | rowNumber |
  | UserData   | 1         |
  | UserData   | 2         |
  | UserData   | 3         |
  | UserData   | 4         |
  | UserData   | 5         |
  | UserData   | 6         |
  | UserData   | 7         |
  | UserData   | 8         |
  | UserData   | 9         |
  | UserData   | 10        |
  | UserData   | 11        |
  | UserData   | 12        |
  | UserData   | 13        |
  | UserData   | 14        |
  | UserData   | 15        |
  | UserData   | 16        |
  | UserData   | 17        |
  
  
  Scenario: Testing POST request with invalid url
  Given Admin has a valid request body
  When Admin sends a POST request to the invalid url
  Then The Api should return the status code 404
  
  Scenario: Testing POST request with invalid create user endpoint
  Given Admin has a valid request body
  When Admin sends a POST request to the invalid endpoint
  Then The Api should return the status code 404
  
  Scenario: Testing POST request without authorization
  Given Admin has a valid request body
  When Admin sends a POST request without authorization
  Then The Api should return the status code 401 unauthorized
  
  Scenario: Testing POST request with only mandatory fields
  Given Admin has a valid request body with only mandatory fields
  When Admin sends a POST request to the valid endpoint
  Then The Api should return the status code 201 Created
  
  Scenario: Testing POST request without mandatory fields
  Given Admin has a valid request body without mandatory fields
  When Admin sends a POST request to the valid endpoint
  Then The Api should return the status code 400 Bad Request
  
  Scenario: Testing POST request with existing emailid and contactnumber
  Given Admin has a  request body with existing emailid and contact number
  When Admin sends a POST request to the valid endpoint
  Then The Api should return the status code 409 Conflict
