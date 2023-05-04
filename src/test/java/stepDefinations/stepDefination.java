package stepDefinations;

import java.util.List;

//import org.junit.runner.RunWith;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;


//@RunWith(Cucumber.class)
public class stepDefination {	
	
	@Given("^User is on NetBanking landing page$")
	public void user_is_on_NetBanking_landing() {
		System.out.println("navigated to login url");
	}	
	
	@When("^User login in to application with username and password$")
	public void user_login_into_application_with_username_and_password() {
		System.out.println("enter username and password");
	}
	
	@Then("^Home page is populated$")
    public void home_page_is_populated() {
		System.out.println("home page is populated");
	}
	
	//@When("^User login into application with username \"([^\"]*)\" and password \"([^\"]*)\"$")	
	@When("User login in to application with username {string} and password {string}")
	public void user_login_into_application_with_username_and_password(String arg1, String arg2) {
		System.out.println(arg1);
		System.out.println(arg2);
	}
	
/*    @When("^User login with following details$")
    public void user_login_with_following_details(DataTable data) throws Throwable {    	
    List<List<String>> obj=data.asLists();
    System.out.println(obj.get(0).get(0));  // data is present in 1 row and mulitple columns
    System.out.println(obj.get(0).get(1));
    System.out.println(obj.get(0).get(2));
     
    }*/	
    
    @When("^User login into application with username (.+) and password (.+)$")
    public void user_login_into_application_with_username_and_password1(String usrname, String passwd) throws Throwable {
       
    	System.out.println("Username : "+usrname);
    	System.out.println("password : "+passwd);
    	
    }
	
	@And("^Cards displayed are \"([^\"]*)\"$")
    public void cards_displayed_are_something(String strArg1) {
		System.out.println("cards are displayed : "+strArg1);
	}
	
	@And("^Cards are displayed$")
    public void cards_are_displayed() {
		System.out.println("cards are displayed");
	}
	
	
	@Given("validate the browser")
	public void validate_the_browser() {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("---- Validate browser------");
	}
	@When("Browser is triggered")
	public void browser_is_triggered() {
	    // Write code here that turns the phrase above into concrete actions
	   System.out.println("browser triggered");
	}
	@Then("check if browser is started")
	public void check_if_browser_is_started() {
	    // Write code here that turns the phrase above into concrete actions
	   System.out.println("----browser started-----");
	}

}
