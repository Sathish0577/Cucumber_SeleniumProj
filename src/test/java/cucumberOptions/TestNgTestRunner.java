package cucumberOptions;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/java/features/PurchaseOrder.feature",		
		glue="stepDefinations",monochrome=true,
		plugin= {"pretty","html:target/cucumber.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"rerun:target/failed_scenarios.txt"})

//"rerun:target/failed_scenarios.txt"   --> to rerun failed scenarios, entry is taken in text file
public class TestNgTestRunner extends AbstractTestNGCucumberTests{
	 /*@Override
     @DataProvider(parallel=true)	
	 public Object[][] scenarios() {
		 
		 return super.scenarios();
	 }*/ 
	
	
}
