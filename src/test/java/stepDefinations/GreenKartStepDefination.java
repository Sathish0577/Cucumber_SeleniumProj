package stepDefinations;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.GreenKartMainPage;
import pageObjects.OffersPage;
import utils.TestContextSetup;

public class GreenKartStepDefination {	
	public WebDriver driver;
	TestContextSetup testContextSetup;
	public String shortName="Tom";
	public String LandingPgeProdName;
	public String inProdName;
	public String inProdQty;	
	
	public GreenKartStepDefination(TestContextSetup testContextSetup) {		
		this.testContextSetup = testContextSetup;	
		this.testContextSetup.inProdName = testContextSetup.inProdName;
		this.testContextSetup.inProdQty = testContextSetup.inProdQty;
	}
	
	@Given("User is on GreenKart Landing page")
	public void user_is_on_green_kart_landing_page() {
		System.setProperty("webdriver.chrome.driver","C://Selenium//Jars//chrome v111//chromedriver.exe");
		testContextSetup.driver = new ChromeDriver();
		testContextSetup.driver.manage().window().maximize();
		testContextSetup.driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");	    
	}
	
	@When("^User searched with shortname (.+) and extracted actual name of product$")
	public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortName) throws InterruptedException {		
		GreenKartMainPage greenKartPg = new GreenKartMainPage(testContextSetup.driver);
		greenKartPg.searchProd(shortName);
		Thread.sleep(2000);		
		//testContextSetup.LandingPgeProdName= greenKartPg.getProdName().split("-")[0].trim();
		testContextSetup.LandingPgeProdName= greenKartPg.getProdName();
		System.out.println("    Message: Main page prod name selected is : "+testContextSetup.LandingPgeProdName);		    
	}
	
	
	@Then("^User add (.+) quantity of the product and click on AddToCart$") 
	public void user_add_quantity_of_the_product(String count) throws InterruptedException {
		GreenKartMainPage greenKartPg = new GreenKartMainPage(testContextSetup.driver);	
		greenKartPg.incrementProdQty(count);				
		testContextSetup.ProdQtyAddValMainPg=greenKartPg.getProdQtyAddedVal();
		System.out.println("    Message: Main page prod qauntity added is : "+testContextSetup.ProdQtyAddValMainPg);
		greenKartPg.clickAddToCartBtn();		
				
		//addKartPg.verifyTextVisibleAfterAddCart();
	}
	
	
	//This step to select one product at a time as part of 'Scenario Outline / Examples' way
	@When("^User adds one product (.+) with required quantity (.+)$")	
	public void user_adds_one_product_with_required_quantity(String inProdName, String inProdQty) throws InterruptedException {
		GreenKartMainPage greenKartPg = new GreenKartMainPage(testContextSetup.driver);
		greenKartPg.selectOneProdFromCart(inProdName,inProdQty);	
	}	
	
	
	//This step to select multiple products/qty in one loop as part of 'Scenario - DataTable' approach way
	@When("^User adds multiple products and required quantity with following details$")	
	public void user_adds_mulitple_products_and_required_quantity_with_following_details(DataTable data) throws InterruptedException {		
		List<List<String>> obj=data.asLists();
		int itemCnt = obj.size();	
		for(int i=0; i<itemCnt; i++) 
		{			
			int k=0;
			testContextSetup.inProdName =obj.get(i).get(k);
			testContextSetup.inProdQty = obj.get(i).get(k+1);
			System.out.println("     Message: Input value ["+i+"] : "+testContextSetup.inProdName+ " and qty required "+testContextSetup.inProdQty+" times");
			GreenKartMainPage greenKartPg = new GreenKartMainPage(testContextSetup.driver);
			greenKartPg.selectProdsFromCart(testContextSetup.inProdName,testContextSetup.inProdQty);
			Thread.sleep(2000);			
		}
		
	}	
	
	
	@When("User clicks TopDeals link")
	public void user_clicks_TopDeals_link() throws InterruptedException {
		GreenKartMainPage greenKartPg = new GreenKartMainPage(testContextSetup.driver);		
		greenKartPg.clickTopDeals();
		Thread.sleep(2000);	
		Set<String> s1= testContextSetup.driver.getWindowHandles();
		Iterator<String> it = s1.iterator();	  
		String parent = it.next();
		String child = it.next();
		testContextSetup.driver.switchTo().window(child);			
		
	}
	
}
