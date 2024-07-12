package Stepdefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	
	
	@Before("@Deleteapi")
	public void beforehook() throws IOException
	{
		if(Stepdefinitionaddplace.pid==null)
		{
			
		
		Stepdefinitionaddplace s = new Stepdefinitionaddplace();
		s.add_place_payload("Rahul", "India", "111");
		s.user_calls_with_request_method("AddPlaceApi","Post");
		s.verify_place_id_created_for_maps_to_using_and("Rahul","GETPlaceApi","Get"); 
		
		
		}
		
	}
	
}
