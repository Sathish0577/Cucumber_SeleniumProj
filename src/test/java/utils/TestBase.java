package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

	public WebDriver driver;	
	public WebDriver WebDriverManager() {
		
		
		
		
		//driver will be initialised only once
		if(driver==null) // this to check if browser is already open or not...avoid opening unncessary browsers opening
		{
		System.setProperty("webdriver.chrome.driver","C://Selenium//Jars//chrome v111//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	}
		//if already initialised return the driver
		return driver;	 
	}
	
	
	
}
