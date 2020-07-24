package com.automation.qa.pages;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.qa.base.Testbase;

public class LoginPage extends Testbase{
	
	//Pagefactory 	- Object Repository
	
	@FindBy(linkText="Login")
	@CacheLookup
	WebElement loginURLLink;

	@FindBy(id="memberId")
	WebElement mobileNumber;
	
	@FindBy(id="mobilePass")
	WebElement mobilePassword;
	
	@FindBy(xpath="//span[@class='buttonText']")
	WebElement loginBtn;

	
	
	@FindBy(xpath="//button[@class='btn btn-primary block bold mem-fb-login']")
	WebElement loginwithFacebookLink;
	
	@FindBy(linkText="Sign Up")
	WebElement signUPLink;
	
	// Initializing  the page objects
	public LoginPage() {
		
		PageFactory.initElements(driver, this);
		// this means its pointing to current class object
		//PageFactory.initElements(driver, LoginPage.class);
	}
	
	//Actions :-
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public HomePage login(String un, String pwd) {
		loginURLLink.click();
		mobileNumber.click();
		mobileNumber.sendKeys(un);
		mobileNumber.click();
		mobilePassword.sendKeys(pwd);		
		loginBtn.click();
		return new HomePage(); 
	}
	


}
