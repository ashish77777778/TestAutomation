package com.automation.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.qa.base.Testbase;

public class ContactPage extends Testbase{
	
	@FindBy(xpath = "//h1[contains(text(),'Contact us')]")
	WebElement ContactUs;
	
	public ContactPage() {
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifyContactUSLabel() {
		return ContactUs.isDisplayed();
	}
	
	
	

}
