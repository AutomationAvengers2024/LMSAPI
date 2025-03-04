@Test
Feature: Testing PUT request in UserAPI 

  Scenario Outline: Testing PUT request with valid and invalid data
    Given Admin has a valid/invalid request body for put operation from sheet name "<sheetName>" at row number <rowNumber>
    When Admin sends a PUT request to the valid endpoint 
    Then The API should return corresponding status code 
    
    Examples:
  | sheetName  | rowNumber |
  | PutData    | 1         |
  | PutData    | 2         |
  | PutData    | 3         |
  | PutData    | 4         |
  | PutData    | 5         |
  | PutData    | 6         |
  | PutData    | 7         |
  | PutData    | 8         |
  | PutData    | 9         |
  | PutData    | 10        |
  | PutData    | 11        |
  | PutData    | 12        |
  | PutData    | 13        |
  | PutData    | 14        |
  | PutData    | 15        |
  | PutData    | 16        |
  | PutData    | 17        |
  | PutData    | 18        |
  | PutData    | 19        |
  | PutData    | 20        |
  | PutData    | 21        |
  | PutData    | 22        |
  | PutData    | 23        |
  | PutData    | 24        |
  | PutData    | 25        |
  | PutData    | 26        |
  
  Scenario: Testing PUT request with invalid url
  Given Admin has a valid request body for Put request
  When Admin sends a Put request to the invalid url
  Then The Api should return the status code 404 Not Found.
  
  Scenario: Testing PUT request with invalid create user endpoint
   Given Admin has a valid request body for Put request  
   When Admin sends a PUT request to the invalid endpoint
   Then The Api should return the status code 404 Not Found.  
  
  Scenario: Testing PUT request without authorization
  Given Admin has a valid request body for Put request  
  When Admin sends a PUT request without authorization
  Then The Api should return the status code 401 unauthorized.