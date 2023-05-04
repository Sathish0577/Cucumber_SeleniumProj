package pageObjects;

import org.openqa.selenium.WebDriver;


//This class helps to avoid creating object where respective page object methods are called/required in each stepDefination class  "GreenKartMainPage grKt = new GreenKartMainPage();
//so instead this pageObjectMgr can be called
public class PageObjectManager {
	
	public WebDriver driver;
	public GreenKartMainPage  greenKartPg;
	public OffersPage  offersPg;
		
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public GreenKartMainPage getGreenKartMainPg() {
		 greenKartPg = new GreenKartMainPage(driver);
		return greenKartPg;
	}	
	
	public OffersPage getOffersPg() {
		offersPg = new OffersPage(driver);
		return offersPg;
	}		

}
