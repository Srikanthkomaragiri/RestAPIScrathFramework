package resources;

import java.util.ArrayList;

import Pojo.Child1pojo;
import Pojo.Parentpojo;

public class Testdatabuild {

	
	
	public Parentpojo addplace(String Name, String Address, String Accuracy)
	{
		
		Parentpojo p = new Parentpojo();
		p.setAccuracy(Accuracy);
		p.setAddress(Address);
		p.setLanguage("French-IN");
		p.setName(Name);
		p.setPhone_number("(+91) 983 893 3937");
		ArrayList<String> ls = new ArrayList<String>();
		ls.add("shoe park");
		ls.add("shop");
		p.setTypes(ls);
		Child1pojo c1 =new Child1pojo();
		c1.setLat("-38.383494");
		c1.setLng("33.427362");
		p.setLocation(c1);;
		p.setWebsite("http://google.com");
		
		
		return p;
	}
	
	public String deletepayload(String Place_id)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+Place_id+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
}
