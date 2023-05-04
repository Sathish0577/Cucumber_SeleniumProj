package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage {

	public WebDriver driver;
	public CheckOutPage(WebDriver driver) {		
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}	


	//By chkOutTable  = By.xpath("//table/tbody/tr");

	By cartIcon  = By.xpath("//img[contains(@src,'bag.png')]");	
	By cartWindow =  By.cssSelector("div.cart-preview");
	By prcdToChkOutBtn  = By.xpath("//div/div[2][@class='action-block']/button");

	@FindBy(xpath="//table/tbody/tr")
	List<WebElement> chkOutprodLst;		

	/*	@FindBy(xpath="//table[@id='productCartTables']/tbody/tr")
	List<WebElement> chkOutprodLst;*/		

	By chkOutProdName  = By.xpath("//table/tbody/tr/td[2]/p");	
	By chkOutProdQty  = By.xpath("//table/tbody/tr/td[3]/p");	
	By chkOutProdTotalPrice  = By.xpath("//table/tbody/tr/td[5]/p");
	By chkOutPromoApplyBtn  = By.cssSelector("button.promoBtn");	
	By chkOutPlcOrdBtn  = By.xpath("//*[contains(@style,'text-align')]/button");
	By chkOutTotalDiscPrice = By.cssSelector("span.discountAmt");

	@FindBy(xpath="//select[contains(@style,'width:')]")
	WebElement sel;

	By chkAgreeTerms = By.cssSelector("input.chkAgree");
	By procedBtn = By.xpath("//*[contains(text(),'Proceed')]");

	public List<WebElement> getProdList() {		
		return chkOutprodLst;
	}

	public String getChkOutProductName() {
		return driver.findElement(chkOutProdName).getText();
	}


	public String getChkOutProductQty() {
		return driver.findElement(chkOutProdQty).getText();
	}


	public Boolean verifyChkOutPromoAplyBtn() {
		Boolean flag =driver.findElement(chkOutPromoApplyBtn).isEnabled();
		return flag;
	}


	public Boolean verifyChkOutPlcOrdBtn() {
		Boolean flag =driver.findElement(chkOutPlcOrdBtn).isEnabled();
		return flag;
	}

	public String verifyCheckOutPageVisible() {		
		return driver.getCurrentUrl();		
	}

	public void clickCartIcon() {		
		driver.findElement(cartIcon).click();		 
	}

	public void verifyProcdToChkOutWindowVisible() {	
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartWindow));		
	}

	public void clickProcedToChkOutBtn() {		
		driver.findElement(prcdToChkOutBtn).click();
	}

	public void clickPlaceOrdrOutBtn() {		
		driver.findElement(chkOutPlcOrdBtn).click();
	}


	/*public void getProdNameQtyPriceFromChkOut(String inProdName, String inProdQty) {		
		int cnt = chkOutprodLst.size();
		String CktProdName = "";
		String CktProdQty = "";
		String CktProdTotPrice = "";

		for(int i=0; i<cnt; i++) {
			CktProdName = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[2]/p")).getText();
			if(CktProdName.equalsIgnoreCase(inProdName)) {			
				CktProdQty = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[3]/p")).getText();
				CktProdTotPrice = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[5]/p")).getText();	
				break;
			}
			else
			{
				continue;
			}
		}
		System.out.println("Check Out Page prods matches with given : "+CktProdName+" Quantity is :"+CktProdQty+" Total price is: "+CktProdTotPrice);
	}*/

	public String getChkOutTotalDiscPrice() {
		return driver.findElement(chkOutTotalDiscPrice).getText();
	}	

	public void selectCountry(String country) throws InterruptedException {		
		Select s  = new Select(sel);
		s.selectByVisibleText(country);
		Thread.sleep(1000);		
	}	

	public void selectAgreeTermsConds() {		
		driver.findElement(chkAgreeTerms).click();		 
	}


	public void clickProceedBtn() {		
		driver.findElement(procedBtn).click();		 
	}

	

	//Single prod in CheckOut page verification with Input (Scenario Outline)
	public Boolean matchOneProdAndQuantityinCheckOutPageWithInput(List<WebElement> chkProdItem,String inProdName, String inProdQty) {

		String CktProdTotPrice =""; String CktProdName =""; String CktProdQty = "";
		Boolean flag=false;
		int count = chkProdItem.size();
		for(int i=1; i<=count;i++) {			 
			CktProdName = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[2]/p")).getText().split("-")[0].trim();
			CktProdQty = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[3]/p")).getText();			

			if( (CktProdName.equalsIgnoreCase(inProdName)) ) {						

				CktProdTotPrice = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[5]/p")).getText();	
				System.out.println("     Message: Check Out Page prods matches with given : '"+CktProdName+"' Quantity is :"+CktProdQty+" Total price of prod is: '"+CktProdTotPrice+"'");
				flag=true;
			}	 

		} 

		return flag;

	}



	//Mulitple prods in CheckOut page verification with Input dataTable (Scenario step)
	public Boolean matchProdsAndQuantityinCheckOutPageWithInput(List<WebElement> chkProdItem,List<List<String>> obj,String inProdName, String inProdQty) {
		
		int count = chkProdItem.size();
		int k=0;
		int j=0;
		String CktProdTotPrice =""; String CktProdName =""; String CktProdQty = "";
		Boolean flag=false;
		for(int i=1; i<=count;i++) {			 
			CktProdName = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[2]/p")).getText().split("-")[0].trim();
			CktProdQty = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[3]/p")).getText();

			//fetch input prodName to verify with ChckOut table prod name
			inProdName =obj.get(j).get(k);
			inProdQty = obj.get(j).get(k+1);			

			if( (CktProdName.equalsIgnoreCase(inProdName)) ) {	

				CktProdTotPrice = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[5]/p")).getText();
				System.out.println("     Message: Check Out Page prods matches with given : '"+CktProdName+"' Quantity is :"+CktProdQty+"  Total price of prod is: '"+CktProdTotPrice+"'");
				flag=true;
			}
			j++;
		} 

		return flag;
	}

}





