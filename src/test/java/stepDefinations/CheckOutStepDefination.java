package stepDefinations;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.CheckOutPage;
import utils.TestContextSetup;


public class CheckOutStepDefination {
	public WebDriver driver;
	TestContextSetup testContextSetup;
	public String CktProdName;
	public String CktProdQty;
	public String CktProdTotPrice;
	public String inProdName;
	public String inProdQty;
	
	public CheckOutStepDefination(TestContextSetup testContextSetup) {		
		this.testContextSetup = testContextSetup;		
		this.testContextSetup.inProdName = testContextSetup.inProdName;
		this.testContextSetup.inProdQty = testContextSetup.inProdQty;
	}
	
	@Then("User clicks on Cart icon and click proceed to CheckOut")
	public void user_clicks_on_cart_icon_and_click_proceed_to_check_out() throws InterruptedException {
		CheckOutPage chkOtPg = new CheckOutPage(testContextSetup.driver);		
		chkOtPg.clickCartIcon();		
			Thread.sleep(2000);		
			chkOtPg.verifyProcdToChkOutWindowVisible();
			chkOtPg.clickProcedToChkOutBtn();		
			Thread.sleep(2000);		
	}

	@Then("User is on CheckOut page")
	public void user_is_on_check_out_page() throws InterruptedException {
		CheckOutPage chkOtPg = new CheckOutPage(testContextSetup.driver);
		System.out.println("-- In CheckOut Page --");	
		Assert.assertTrue(chkOtPg.verifyCheckOutPageVisible().contains("cart"));
		Thread.sleep(2000);    
	}
	
	
	@Then("Validate product exist in CheckOut page and matches in previous page")
	public void validate_product_exist_in_check_out_page_and_matches_in_previous_page()  {
		CheckOutPage chkOtPg = new CheckOutPage(testContextSetup.driver);
		chkOtPg.getProdList();
		String prodNameInChkOutPg = chkOtPg.getChkOutProductName();
		String prodQtyInChkOutPg= chkOtPg.getChkOutProductQty();
		System.out.println("    Message: In Chk out Prod name: "+prodNameInChkOutPg+ "  Qty selected  :"+prodQtyInChkOutPg);
		System.out.println("    Message: In Main page Prod name:"+testContextSetup.LandingPgeProdName+"  Qty   :"+testContextSetup.ProdQtyAddValMainPg);
		Assert.assertEquals(prodNameInChkOutPg, testContextSetup.LandingPgeProdName);
		Assert.assertEquals(prodQtyInChkOutPg, testContextSetup.ProdQtyAddValMainPg);		
	}
	
	
	@Then("Validate buttons Apply and Place Order are enabled")
	public void validate_buttons_apply_and_place_order_are_enabled() {
		CheckOutPage chkOtPg = new CheckOutPage(testContextSetup.driver);
		Boolean flag=chkOtPg.verifyChkOutPromoAplyBtn();
		Boolean flag1 =chkOtPg.verifyChkOutPlcOrdBtn();		
		if( (flag==true) &&  (flag1==true )) {			
			System.out.println("    Message: CheckOut Page has required buttons enabled");
		}
		else
		{
			System.out.println("    Message: CheckOut Page has no required buttons");
		}
		
		
		/*   Or below way can be done
		 * Assert.assertTrue(chkOtPg.verifyChkOutPromoAplyBtn());
		Assert.assertTrue(chkOtPg.verifyChkOutPlcOrdBtn());*/
		
	}
	
	

	//Validate prod names from Scenario Outline / Examples with actual 
	@Then("^Validate products exists in CheckOut page and matches with given products (.+) and (.+)$")
    public void validate_products_exists_in_CheckOutpage_and_matches_with_given_products(String inProdName, String inProdQty) {
		CheckOutPage chkOtPg = new CheckOutPage(testContextSetup.driver);	
		
		List<WebElement> chkProdItem = chkOtPg.getProdList(); 	
		
		Boolean flag = chkOtPg.matchOneProdAndQuantityinCheckOutPageWithInput(chkProdItem, inProdName, inProdQty);
		
		Assert.assertTrue(flag, "Input product does not match with CheckOut Page");
		
	}
	
	
	//Validate prod names from DataTable provided for step ( as Scenario ) with actual 
	@Then("Validate products exists in CheckOut page and matches with given products and quantity")
    public void validate_products_exists_in_CheckOutpage_and_matches_with_given_products(DataTable data) {
		CheckOutPage chkOtPg = new CheckOutPage(testContextSetup.driver);	
		
		 //Fetch datatable row datas
		 List<List<String>> obj=data.asLists();	
		 
		 //Fetch from list required product	
		 List<WebElement> chkProdItem = chkOtPg.getProdList(); 		
		 
		 Boolean flag = chkOtPg.matchProdsAndQuantityinCheckOutPageWithInput(chkProdItem,obj, inProdName,  inProdQty);	
		 
		 Assert.assertTrue(flag, "Input mulitple products does not match with CheckOut Page");
		 
	}	
	
	
	
	 @And("Validate total price matches with total after discount")
     public void validate_total_price_matches_with_total_after_discount() {
		 CheckOutPage chkOtPg = new CheckOutPage(testContextSetup.driver);
			List<WebElement> chkProdItem = chkOtPg.getProdList(); 		
			 int count = chkProdItem.size();
			 int sum =0;
			 int totalDiscPrice = Integer.parseInt(chkOtPg.getChkOutTotalDiscPrice());
			 for(int i=1; i<=count;i++) {		
				 
				 String prodPrc= testContextSetup.driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[5]/p")).getText();
				// System.out.println("pric each : "+prodPrc);
				 sum = sum + Integer.parseInt(prodPrc);
			 }
		
			 if(sum==totalDiscPrice) {
				 
				 System.out.println("     Message:Total purchase price matches '" +sum+ "' with total discount price : '"+totalDiscPrice+"'");
				 
				 
			 }else
			 {
				 System.out.println("     Message:Total purchase price do not matches '" +sum+ "' with total discount price '"+totalDiscPrice+"'");
			 }
	}
	 
	 
	 @Then("User places clicks placeorder")
    public void user_clicks_placeorder_button() throws InterruptedException {
		CheckOutPage chkOtPg = new CheckOutPage(testContextSetup.driver);
		chkOtPg.clickPlaceOrdrOutBtn();
		Thread.sleep(2000);		
	}
	
	 //Select country as one value
	@And("User select one country {string} and submit")	
    public void user_select_one_country_and_submit(String country) throws InterruptedException {		
		CheckOutPage chkOtPg = new CheckOutPage(testContextSetup.driver);		
		chkOtPg.selectCountry(country);
		chkOtPg.selectAgreeTermsConds();
		chkOtPg.clickProceedBtn();
		Thread.sleep(1000);
	}
	
	
	//Select country with different values
	@And("^User selects country (.+) and submit$")
    public void user_selects_country_and_submit(String country) throws InterruptedException {		
		CheckOutPage chkOtPg = new CheckOutPage(testContextSetup.driver);		
		chkOtPg.selectCountry(country);
		chkOtPg.selectAgreeTermsConds();
		chkOtPg.clickProceedBtn();
		Thread.sleep(1000);
	}
	
	
}
