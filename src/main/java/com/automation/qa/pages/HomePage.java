package com.automation.qa.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.qa.base.Testbase;
import com.automation.qa.util.TestUtil;

public class HomePage extends Testbase{
	
	TestUtil testUtil = new TestUtil();
	
	@FindBy(linkText="Contact")
	WebElement contactLink;
	
	@FindBy(linkText="Assistance")
	WebElement AssistanceLink;
	
	 
	
	public HomePage() {
		PageFactory.initElements(driver, this);
		
	}
		
	//Actions :-	
		public String validateHomePageTitle() {
			return driver.getTitle();
		}
	
		public ContactPage clickOnContactsLink() {				
			
			WebElement element = driver.findElement(By.linkText("Contact"));	
			testUtil.clickUsingJavaScript(element);
			return new ContactPage();
		}
		
		public AssistancePage clickOnAssistanceLink() {
			WebElement element = driver.findElement(By.linkText("Assistance"));
			testUtil.ClickOnElementUsingActionMethod(element);			
			return new AssistancePage();
		}
	

}
