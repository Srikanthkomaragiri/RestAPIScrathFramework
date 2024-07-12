package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	
	public static RequestSpecification request;
	
	public RequestSpecification requestspecification() throws IOException
	{
		if(request==null)
		{
		PrintStream ps = new PrintStream(new File("logging.txt"));//for logging the actions
		 request = new RequestSpecBuilder().
				 addFilter(RequestLoggingFilter.logRequestTo(ps)).
				 addFilter(ResponseLoggingFilter.logResponseTo(ps)).
			    setBaseUri(globalvalues("BaseURI")).
			    
			    setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").
			   
			    build();
		 
		 
           return request;
		}
		return request;
}


public String globalvalues(String key) throws IOException
{
	Properties p = new Properties();//properties class to read .properties file
	FileInputStream f = new FileInputStream("D:\\QA Testing\\user\\RestWorkspace\\SratchFramework\\src\\test\\java\\resources\\gloabl.properties");
	p.load(f);
	return p.getProperty(key);
}

public String getjsonresponse(Response respost,String key)
{
	 String a =  respost.asString();
      JsonPath js = new JsonPath(a);
      return js.get(key).toString();
      
      
}



}
