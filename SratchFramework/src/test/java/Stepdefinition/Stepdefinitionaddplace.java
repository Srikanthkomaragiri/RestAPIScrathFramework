package Stepdefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import Pojo.Child1pojo;
import Pojo.Parentpojo;
import io.cucumber.java.en.Given ;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.Testdatabuild;
import resources.Utils;

public class Stepdefinitionaddplace  extends Utils{

	RequestSpecification res;
	ResponseSpecification resspec;
	Response respost;
	JsonPath js;
	static String pid;
 Testdatabuild n = new Testdatabuild();
private Response resGet;
 
 @Given("ADD place payload {string} {string} {string}.")
 public void add_place_payload(String Name, String Address, String Accuracy) throws IOException {
	
	
    // Write code here that turns the phrase above into concrete actions
	
	
		
	
	 resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

 res	= given().spec(requestspecification()).body(n.addplace(Name,Address,Accuracy));
	
	
	
}

 @When("User calls {string} with {string} request method.")
 public void user_calls_with_request_method(String Resource, String Method) {

     APIResources  resourceapi = APIResources.valueOf(Resource);
     System.out.println(resourceapi.getresource());
	 if(Method.equalsIgnoreCase("Post"))
	 {
     respost= res.when().post(resourceapi.getresource());
	 }
	 else if (Method.equalsIgnoreCase("Get")) {
		 resGet= res.when().get(resourceapi.getresource());
	}
	
	
}

@Then("The Api call got sucess with status code {int}")
public void the_api_call_got_sucess_with_status_code(Integer int1) {
    
  assertEquals(respost.getStatusCode(), 200);

}

@Then("{string} in response body is {string}.")
public void in_response_body_is(String key, String Expectedvalue) {

	  String Actualvalue =  getjsonresponse(respost,key);
	
	assertEquals(Actualvalue,Expectedvalue);
                 



}

@Then("Verify Place_id created for maps to {string} using {string} and {string}.")
public void verify_place_id_created_for_maps_to_using_and(String ExpectedName, String Resource, String Method) throws IOException 
{
	
	   pid = getjsonresponse(respost, "place_id");
	
	res	= given().spec(requestspecification()).queryParam("place_id", pid);
	user_calls_with_request_method(Resource,Method);
	String actual = getjsonresponse(resGet,"name");
	assertEquals(actual, ExpectedName);
	
}





@Given("Add DeleteApi Payload.")
public void add_delete_api_payload() throws IOException {
    
	 res =given().spec(requestspecification()).body(n.deletepayload(pid));
	
	
	
}

@When("User Calls {string} resource with {string} method.")
public void user_calls_resource_with_method(String resource, String method) {
   
	
	user_calls_with_request_method(resource,method);
	
	
}

@Then("The Api call got the Status code is {int}.")
public void the_api_call_got_the_status_code_is(Integer statuscode) {
    
	assertEquals(respost.getStatusCode(), 200);
	  
	
}

@Then("{string} in the response body is {string}.")
public void in_the_response_body_is(String Status, String Expectedstatus) {
   
  String actual =	getjsonresponse(respost, Status);
	assertEquals(actual,Expectedstatus);
}



	
	
}
