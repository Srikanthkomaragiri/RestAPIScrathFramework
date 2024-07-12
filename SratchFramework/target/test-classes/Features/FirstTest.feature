Feature: Validating Place API's.


Scenario Outline: Verify place is successfully added using ADDPlaceApi
    Given ADD place payload "<Name>" "<Address>" "<Accuracy>".
    When  User calls "AddPlaceApi" with "Post" request method.
    Then  The Api call got sucess with status code 200
    And  "status" in response body is "OK".
    And  "scope" in response body is "APP".
    And  Verify Place_id created for maps to "<Name>" using "GETPlaceApi" and "Get".
    
Examples:
  |Name|Address|Accuracy|
  |Frontline house|29, side layout, cohen 09|50|
  
@Deleteapi  
Scenario:Verify User Delete the Created Request In the MapsApi
       Given Add DeleteApi Payload.
       When  User Calls "DELETEPlaceApi" resource with "Post" method.
       Then  The Api call got the Status code is 200.
       Then  "status" in the response body is "OK".
  