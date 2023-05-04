package cucumberOptions;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="@target/failed_scenarios.txt",		
		glue="stepDefinations",monochrome=true,
		plugin= {"pretty","html:target/cucumber.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class FailedTestNgTestRunner extends AbstractTestNGCucumberTests{
	 /*@Override
     @DataProvider(parallel=true)	
	 public Object[][] scenarios() {
		 
		 return super.scenarios();
	 }*/ 	
}
