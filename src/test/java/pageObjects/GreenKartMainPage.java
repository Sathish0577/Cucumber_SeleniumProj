package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.TestContextSetup;

public class GreenKartMainPage {
	
	public WebDriver driver;	
	public GreenKartMainPage(WebDriver driver) {		
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
		
	By searchField = By.cssSelector("input.search-keyword");
	By prodName = By.xpath("//div[@class='product']/h4");
	By linkTopDeals= By.linkText("Top Deals");
	
	By incrementQty  = By.cssSelector("a.increment");		
	By addToCartBtn  = By.xpath("//div[@class='product-action']/button");
	By prodQtyAddedVal  = By.cssSelector("input.quantity");	
	
	
	
	By prodPrice = By.xpath("//div[@class='product']/p");
	
	public void searchProd(String name) {		
		driver.findElement(searchField).sendKeys(name);		
	}

	public String getProdName() {		
		return driver.findElement(prodName).getText();		
	}
	
	
	public void clickTopDeals() {
		driver.findElement(linkTopDeals).click();
	}
	
	public String getProdQtyAddedVal() {		
		return driver.findElement(prodQtyAddedVal).getAttribute("value");		
	}	
	
	public void incrementProdQty(String count) throws InterruptedException {	
		int cnt = Integer.parseInt(count);
		for(int i=0; i<cnt; i++)
		{ driver.findElement(incrementQty).click();	
		  Thread.sleep(1000);		
	     }	
	}	
	
	public void clickAddToCartBtn() {		
		 driver.findElement(addToCartBtn).click();		 
	}
	

	public void verifyTextVisibleAfterAddCart() {		
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(addToCartBtn, "Added"));
	}	
	
	
	public String getProdPrice() {
		return driver.findElement(prodPrice).getText();		
	}
	
	
	//Locator details to Fetch in a loop
	@FindBy(xpath="//div[@class='products']/div")	
	List<WebElement> MainPgProdLst;
		
	By prodQtyIncr_Lp  = By.xpath("div/a[@class='increment']");
	By prodQtyVal_Lp  = By.xpath("div/input[@class='quantity']");	
	By prodAdd2CartBtn_Lp  = By.xpath("div[@class='product-action']/button");		
	

	//****** this code only to select only one product/qty as part of 'Scenario Outline/Examples' way  ******	
	public void selectOneProdFromCart(String inProdName, String inProdQty) throws InterruptedException {		
		int prodsCnt= MainPgProdLst.size();	
		String prodTxt ="";
		int cnt = Integer.parseInt(inProdQty);
		for(int i=0; i<prodsCnt; i++) {					 
			 prodTxt = MainPgProdLst.get(i).findElement(By.xpath("h4")).getText().split("-")[0].trim();			
			 if(prodTxt.equalsIgnoreCase(inProdName)) {			
				
				 for(int j=0; j<cnt; j++)
					{				      
				      MainPgProdLst.get(i).findElement(prodQtyIncr_Lp).click();
				      Thread.sleep(1000);
					}
				
				 String prodQty = MainPgProdLst.get(i).findElement(prodQtyVal_Lp).getAttribute("value");					
				MainPgProdLst.get(i).findElement(prodAdd2CartBtn_Lp).click();							
				Thread.sleep(1000);			
				System.out.println("     Message: "+inProdName+" is added to cart with required quantity  : "+prodQty);			
			}			
		}	
		
	}
	
	
	//this code to select multiple products/qty in one loop/step as part of a 'Scenario- DataTable' and move to next action/step
	public void selectProdsFromCart(String inProdName, String inProdQty) throws InterruptedException {		
		int prodsCnt= MainPgProdLst.size();	
		String prodTxt ="";
		for(int i=0; i<prodsCnt; i++) {				
					 
			 prodTxt = MainPgProdLst.get(i).findElement(By.xpath("h4")).getText().split("-")[0].trim();			
			 if(prodTxt.equalsIgnoreCase(inProdName)) {	
				 
		//Wait for '+' icon to visible and click no. of times
				//Thread.sleep(1000);
				 int cnt = Integer.parseInt(inProdQty);
				   WebElement element = MainPgProdLst.get(i).findElement(prodQtyIncr_Lp);					
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);", element);
					for(int j=0; j<cnt; j++)
					{ 					
					    js.executeScript("arguments[0].click();", element);
					    Thread.sleep(1000);					
				     }
					
				Thread.sleep(1000);	
				String prodQty = MainPgProdLst.get(i).findElement(prodQtyVal_Lp).getAttribute("value");				
				
	   //Wait for 'Add To Cart' button to appear and click  
				element = MainPgProdLst.get(i).findElement(prodAdd2CartBtn_Lp);
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);", element);
				js1.executeScript("arguments[0].click();", element);				
				
				Thread.sleep(1000);				
				System.out.println("     Message: "+inProdName+" is added to cart with required quantity : "+prodQty);				
			}
			
		}		
	}
	
	
	
}
