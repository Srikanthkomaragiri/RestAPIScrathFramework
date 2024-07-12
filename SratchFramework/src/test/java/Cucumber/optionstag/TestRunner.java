package Cucumber.optionstag;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		
		
		features = "D:\\QA Testing\\user\\RestWorkspace\\SratchFramework\\src\\test\\java\\Features\\FirstTest.feature",
		plugin = "json:target/jsonReports/cucumber-report.json",
		glue = {"Stepdefinition"},
		tags = "@Deleteapi"
		
		
		
		
		
		
		)	



public class TestRunner {


	
}
