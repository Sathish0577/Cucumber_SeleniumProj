package stepDefinations;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.TestBase;
import utils.TestContextSetup;

public class Hooks {
	TestContextSetup testContextSetup;
	
	public Hooks(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;		
	}
	
	/*@Before("@SmokeTest")
	public void beforeValidation() {
		System.out.println(" $$$$$ before smoke");
	}*/
	
	//@After
	public void afterValidation() {		
		testContextSetup.driver.quit();
		System.out.println("Browser closed");		
	}
	
	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException {		
		
		//System.out.println("took screenshot");	
		
		WebDriver driver=testContextSetup.driver;				
		if(scenario.isFailed())  //if only step is failed
		{
			
		File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);   	
		scenario.attach(fileContent, "image/png", "image");	
		
		}
		
		
	}
	
	
	

	
	
	

}
