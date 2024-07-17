package CucumberOptions;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = "D:\\QA Testing\\user\\RestWorkspace\\Handsoneecomerce\\src\\test\\java\\FeatureFiles\\EcomerceE2E.feature",
		glue = {"Stepdefinition"}
		
		)








public class TestRunner {

	
}
