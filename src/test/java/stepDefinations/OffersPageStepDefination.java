package stepDefinations;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.GreenKartMainPage;
import pageObjects.OffersPage;
import utils.TestContextSetup;

public class OffersPageStepDefination {
	public WebDriver driver;
	TestContextSetup testContextSetup;
	public String offerPgProdName;

	public OffersPageStepDefination(TestContextSetup testContextSetup) {		
		this.testContextSetup = testContextSetup;
	}

	@Then("^User searched for same shortname (.+) in offers page and check if product exist with same name$")
	public void user_searched_for_same_shortname_in_offers_page_and_check_if_product_exist(String shortName) throws InterruptedException {
		GreenKartMainPage greenKartPg = new GreenKartMainPage(testContextSetup.driver);
		OffersPage offersPg = new OffersPage(testContextSetup.driver);
		greenKartPg.clickTopDeals();
		Thread.sleep(2000);		
		Set<String> s1= testContextSetup.driver.getWindowHandles();
		Iterator<String> it = s1.iterator();	  
		String parent = it.next();
		String child = it.next();
		testContextSetup.driver.switchTo().window(child);		
		offersPg.offersPgSearchProd(shortName);	
		Thread.sleep(2000);
		offerPgProdName=offersPg.getProdNameAtOffersPg();
		//System.out.println("Product in Offers page is : "+offerPgProdName);		

	}


	@Then("Validate product name in offers page with landing page")
	public void validate_product_name_in_offers_page_with_landing_page() {
		System.out.println("Product in Offers page is : "+offerPgProdName);
		Assert.assertEquals(offerPgProdName,testContextSetup.LandingPgeProdName,"Product does not exist in Offers page");
	}

	
		
	@Then("User searches for product {string} with different page size {string}")
    public void user_searches_for_product_with_different_page_size(String inProdName, String pageSize) throws InterruptedException {
		OffersPage offersPg = new OffersPage(testContextSetup.driver);	
		offersPg.searchProdUnderPageSize(inProdName,pageSize);
		
	}
	
	@Then("^User searches for products (.+) with different page size selection (.+)$")
    public void user_searches_for_products_with_different_page_size_selection(String inProdName, String pageSize) throws InterruptedException {
		OffersPage offersPg = new OffersPage(testContextSetup.driver);	
		offersPg.searchProdUnderPageSize(inProdName,pageSize);
		
	}
	
	
	
	
}