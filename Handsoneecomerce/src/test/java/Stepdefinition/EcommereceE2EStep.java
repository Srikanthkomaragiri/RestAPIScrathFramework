package Stepdefinition;

import Resources.APIResources;
import Resources.TestData;
import Resources.Utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import PojoDese.parent;
import Pojose.Login;
import Pojose.Parentcreateorderplace;
import Pojose.childcreateorderplace;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class EcommereceE2EStep extends Utils {

	
	TestData Td = new TestData();
	RequestSpecification  reqspec;
    Response  respost,resaddprod;
	Response responseco,deleteapires;
	String	jr;
	public static  String uid;
	public static  String t;
	public static String pid;
	//public String productOrderedId;
	RequestSpecification requestspecAddProduct,reqspecAddProd,Deleteorderapi;
    RequestSpecification placeorder;
	
	@Given("User Add valid Credentials {string} and {string} in the body payload.")
	public void user_add_valid_credentials_and_in_the_body_payload(String userEmail, String userPassword) throws IOException {
	    
	
		
	  reqspec  = given().spec(RequestSpecification1()).contentType(ContentType.JSON).body(Td.loginauthenticate(userEmail, userPassword));
		
		
	}

	@When("User Send the {string} Request to {string} resource with the credentials.")
	public void user_send_the_request_to_resource_with_the_credentials(String Method, String loginApi) {
	    
		 APIResources APR = APIResources.valueOf(loginApi); 
		 
		 if(Method.equalsIgnoreCase("Post"))
		 {
		respost	= reqspec.when().post(APR.getresource()); 
		 }
		
		
		
		
	}

	@Then("User get the Status code in the response body is {string}.")
	public void user_get_the_status_code_in_the_response_body_is(String Actualstatuscode) {
	  
		assertEquals(respost.getStatusCode(),200);
		
		
		
	}

	@Then("User get the {string} in the response body is {string}.")
	public void user_get_the_in_the_response_body_is(String key, String Expectedstatus) {

		
       
      
        t = getjsonresponse1(respost, "token");
      
      System.out.println("Access-token is "+t);
      
      uid = getjsonresponse1(respost, "userId");
      
     System.out.println("userId is "+uid);
      	}



	//////////////////////////////////////////////ADDPRODUCT IN APPLICATION
	
	
	

@Given("User provide valid product deatils in the form-data like below.")
public void user_provide_valid_product_deatils_in_the_form_data_like_below(DataTable dataTable) throws IOException {
    

	reqspecAddProd  =    given().spec(RequestSpecification1()).header("Authorization",t);
	
	
	
	

	
	
	Map<String, String> datamap = dataTable.asMap();///converted datatable to asMap
	
	
	for(Map.Entry<String, String> kv : datamap.entrySet())//return key and value
	{
		String key = kv.getKey();
		String value = kv.getValue();
		value = replacePlaceholders(value);
		
		
		if ("productImage".equals(key.trim())) { // Check if key is 'productImage'
            // Handle image file attachment
            File imageFile = new File(value.trim());
            if (imageFile.exists()) {
            	reqspecAddProd.multiPart("productImage", imageFile); // Assuming 'productImage' is the form field name
            } 
        } else {
        	reqspecAddProd.param(key,value);
        
        
        
        }
    }

	
	
	
	
}

	


@When("User Send the {string} with resource {string}.")
public void user_send_the_with_resource(String Method, String resource) {
    
	
	APIResources   Addproductapi = APIResources.valueOf(resource);
	
	if(Method.equalsIgnoreCase("Post"))
	{
	   resaddprod  = reqspecAddProd.when().post(Addproductapi.getresource());
	}
	
	
}

@Then("User get the Status code in the response body is {string} created.")
public void user_get_the_status_code_in_the_response_body_is_created(String string) {
   
	
	assertEquals(resaddprod.getStatusCode(), 201);
	
	
}

@Then("User get respective {string} and {string} in the response body.")
public void user_get_respective_and_in_the_response_body(String productid, String message) {
    
   pid =  getjsonresponse1(resaddprod, productid);
  String pmessage =  getjsonresponse1(resaddprod, message);
  System.out.println("product_id is "+pid);
  System.out.println("message is "+pmessage);


}
////////////place the order


@Given("User Added Valid bodypayload like country {string}.")
public void user_added_valid_bodypayload_like_country(String country) throws IOException{
	
	
	
	childcreateorderplace cco = new childcreateorderplace();
	cco.setCountry(country);
	cco.setProductOrderedId(pid);
	
	ArrayList<childcreateorderplace> AL = new ArrayList<childcreateorderplace>();
	
	AL.add(cco);
	
	Parentcreateorderplace pco = new Parentcreateorderplace();
	pco.setorders(AL);
	
	
	
	
	
     placeorder =  given().log().all().spec(RequestSpecification1()).
    		 contentType(ContentType.JSON).header("Authorization",t).body(pco);
     
	
	        
	
}

@When("User send the Post {string} method along with resource {string}.")
public void user_send_the_post_method_along_with_resource(String post, String resource) {
    
	APIResources co = APIResources.valueOf(resource);
	
	          responseco  = placeorder.when().post(co.getresource());
	          
}

@Then("User get the message {string} in the response body.")
public void user_get_the_message_in_the_response_body(String Message) {

	             
	    parent message  = deserialisation(responseco, Message);
	  
	          
	System.out.println("message is="+  message.getMessage());
	

	
	
}

@Then("User get the orders id {string} in the response body.")
public void user_get_the_orders_id_in_the_response_body(String orderid) {

    
	
    parent order_id  = deserialisation(responseco, orderid);
    
System.out.println("order_id is="+order_id.getOrders());



}

/////////////////////delete api


@Given("Add valid productid in the path parameters.")
public void add_valid_productid_in_the_path_parameters() throws IOException {
    
	      Deleteorderapi = given().spec(RequestSpecification1()).pathParam("pid",pid )
	    		  .header("Authorization",t).contentType(ContentType.JSON);
	      
	
	
}

@When("User send the Delete {string} with resource {string}.")
public void user_send_the_delete_with_resource(String Delete, String resource) {
  
	APIResources doapi= APIResources.valueOf(resource);
	   
	
	deleteapires  = Deleteorderapi.when().delete(doapi.getresource());
	
}

@Then("user got the message {string} is {string} in the response.")
public void user_got_the_message_is_in_the_response(String message, String expected) {
	        
	      String actualmessage  = getjsonresponse1(deleteapires, message);
          assertEquals(expected,actualmessage);
           System.out.println("............"+actualmessage);
	
}






	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
