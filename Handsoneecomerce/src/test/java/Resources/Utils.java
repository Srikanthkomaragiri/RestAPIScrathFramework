package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import PojoDese.parent;
import Stepdefinition.EcommereceE2EStep;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	
	public static RequestSpecification request;	
	public RequestSpecification RequestSpecification1() throws IOException  
	{
		if(request==null)
		{
		PrintStream ps = new PrintStream(new File("logging.txt"));
	request = new RequestSpecBuilder().
	          addFilter(RequestLoggingFilter.logRequestTo(ps)).
	          addFilter(ResponseLoggingFilter.logResponseTo(ps)).
	          setBaseUri(globalvalues("BaseURI")).
	          build();
		
	
		}
		return request;
		
	}
	private String globalvalues(String key) throws IOException {
		
		Properties p = new Properties();
		FileInputStream f = new FileInputStream("D:\\QA Testing\\user\\RestWorkspace\\Handsoneecomerce\\src\\test\\java\\Resources\\global.properties");
		p.load(f);
		
		return p.getProperty(key);
		
	}

public String getjsonresponse1(Response bodyresponse,String key)
	{
		String br = bodyresponse.asString();
		
		
		
		JsonPath js = new JsonPath(br);
		System.out.println(js);
		System.out.println(js.get(key).toString());
	
		return js.get(key).toString();
	}
	
public parent deserialisation(Response bodyresponse,String key)
{
	parent br = bodyresponse.then().extract().response().as(parent.class);
	
       return br;

	//return br.get(key).toString();
}

/*
public RequestSpecification Addproductecomerece(DataTable datatable) throws IOException
{
	
	
	
	
	RequestSpecification  Addproduct  = RequestSpecification1();
	
	
	Map<String, String> datamap = datatable.asMap();///converted datatable to asMap
	
	
	for(Map.Entry<String, String> kv : datamap.entrySet())//return key and value
	{
		String key = kv.getKey();
		String value = kv.getValue();
		value = replacePlaceholders(value);
		
		
		if ("productImage".equals(key.trim())) { // Check if key is 'productImage'
            // Handle image file attachment
            File imageFile = new File(value.trim());
            if (imageFile.exists()) {
                Addproduct.multiPart("productImage", imageFile); // Assuming 'productImage' is the form field name
            } 
        } else {
            Addproduct.param(key,value);
        
        
        
        }
    }

	
	return Addproduct;
	
	
}*/

public String replacePlaceholders(String value) {
    // Implement your placeholder replacement logic here
    // For example, replace {{userId}} with actual user ID
    // Example value
	String userId =EcommereceE2EStep.uid;
	value = value.replace("{{userId}}", userId);

    // Add more placeholder replacements as needed

    return value;
}





















}
