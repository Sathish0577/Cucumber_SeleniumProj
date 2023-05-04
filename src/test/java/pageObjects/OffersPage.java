package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OffersPage {

	public WebDriver driver;

	public OffersPage(WebDriver driver) {		
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}	
	
	
	By offersPgSearchField = By.cssSelector("input#search-field");
	
	//select drop - 5, 10, 20 range	
	@FindBy(xpath="//select[@id='page-menu']")	
	WebElement offersPgSelect;

	//Table	
	By offersTable = By.cssSelector("table.table.table-bordered");	

	@FindBy(xpath="//table/thead/tr")
	List<WebElement> prodLstHead;	

	@FindBy(xpath="//table/tbody/tr")
	List<WebElement> prodLst;	

	By offrPgProdTxt = By.xpath("//table/tbody/tr/td[1]");
	By offrPgProdPrice = By.xpath("//table/tbody/tr/td[2]");
	By offrPgProdDiscPrice = By.xpath("//table/tbody/tr/td[3]");

	By offrPgProdTxt_Lp = By.xpath("td[1]");
	By offrPgProdPrice_Lp = By.xpath("td[2]");
	By offrPgProdDiscPrice_Lp = By.xpath("td[3]");

	By offrPgColHeader1 = By.xpath("//table/thead/tr/th[1]/span");
	By offrPgColHeader2 = By.xpath("//table/thead/tr/th[1]/span");
	By offrPgColHeader3 = By.xpath("//table/thead/tr/th[1]/span");

	//List of buttons for navigation
	@FindBy(xpath="//ul/li/a")
	List<WebElement> navignButtonsLst;	

	By navgnFirstBtn = By.xpath("//a[@aria-label='First']");
	By navgnPrevBtn = By.xpath("//a[@aria-label='Previous']");
	By navgn1_Btn = By.xpath("//a/span[contains(text(),'1')]");
	By navgn2_Btn = By.xpath("//a/span[contains(text(),'2')]");
	By navgn3_Btn = By.xpath("//a/span[contains(text(),'3')]");
	By navgn4_Btn = By.xpath("//a/span[contains(text(),'4')]");
	By navgnNextBtn = By.xpath("//a[@aria-label='Next']");
	By navgnLastBtn = By.xpath("//a[@aria-label='Last']");	

	public void offersPgSearchProd(String name) {		
		driver.findElement(offersPgSearchField).sendKeys(name);		
	}

	public int getProdCount() {	
		return prodLst.size(); 			
	}	

	public String getProdNameAtOffersPg() {
		return driver.findElement(offrPgProdTxt).getText();
	}

	public boolean isNextBtnEnabled() {
		return driver.findElement(navgnNextBtn).isEnabled();
	}

	public boolean isPrvosBtnEnabled() {
		return driver.findElement(navgnPrevBtn).isEnabled();
	}

	public boolean isLastBtnEnabled() {
		return driver.findElement(navgnLastBtn).isEnabled();
	}

	public boolean isFirstBtnEnabled() {
		return driver.findElement(navgnFirstBtn).isEnabled();
	}



	public void searchProdUnderPageSize(String inProdName, String pgSize) throws InterruptedException {

		Select s = new Select(offersPgSelect);		
		s.selectByVisibleText(pgSize);
		Thread.sleep(2000);

		Boolean found= false;
		Boolean flag= false;
		String prodTxt=""; String prodPrice=""; String prodDiscPrice ="";
		 int count = prodLst.size();
         int j=1;
         
		while(found==false) {			

			for(int i=0; i<count;i++) {
							
				prodTxt=prodLst.get(i).findElement(offrPgProdTxt_Lp).getText();
				prodPrice = prodLst.get(i).findElement(offrPgProdPrice_Lp).getText();
				prodDiscPrice =prodLst.get(i).findElement(offrPgProdDiscPrice_Lp).getText();
				//System.out.println(""+prodTxt+"   "+prodPrice+"   "+prodDiscPrice);
				
				if(inProdName.equalsIgnoreCase(prodTxt)) {					
					System.out.println("     Message : Required product name '"+prodTxt+"' exists and  price is '"+prodPrice+"' discount price is : '"+prodDiscPrice+"'");
					found= true;
					break;
				}
				else
				{	
					continue;
				}	

			}
			
			flag=isNextBtnEnabled();
			if( (flag==true) && (found==false) )
			{
				//System.out.println("inside btn enabled state");
				driver.findElement(navgnNextBtn).click();
				Thread.sleep(2000);	
				count = prodLst.size();
				j++;			
			}
		}


	}

}
