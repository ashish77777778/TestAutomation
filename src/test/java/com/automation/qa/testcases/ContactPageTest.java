package com.automation.qa.testcases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.qa.base.Testbase;
import com.automation.qa.pages.ContactPage;
import com.automation.qa.pages.HomePage;
import com.automation.qa.pages.LoginPage;
import com.automation.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class ContactPageTest extends Testbase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactPage contactPage;
	TestUtil testUtil;
	
	public ContactPageTest() {
		
		super();
	}
	
	@BeforeTest
	public void setExtent() {
		//extent = new ExtentReports(System.getProperty("user.dir")+"Reports/ExtentReport.html", true);
		extent = new ExtentReports("C:\\Users\\ashis\\eclipse-workspace\\TestAutomation\\test-output\\ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Ashish PC");
		extent.addSystemInfo("UserName", "Ashish");
		extent.addSystemInfo("Environment", "QA");
		System.out.println("Before test");
		
	}
	
	@BeforeMethod
	public void setUp() {
		intialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactPage = homePage.clickOnContactsLink();
		//contactPage = new ContactPage();
		//contactPage = homePage.clickOnContactsLink();
		//contactPage = homePage.clickOnContactsLink();	
				
	}
	
	@Test(priority = 1)
	public void verifyContactUSLabelTest() {
		//contactPage.verifyContacUSLabel();
		//contactPage = homePage.clickOnContactsLink();
		extentTest =  extent.startTest("verifyContactUSLabelTest");
		
		Assert.assertTrue(contactPage.verifyContactUSLabel());
	}
		
	@AfterMethod
	public  void teardown(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL,"TEST Case Failed is :-"+ result.getName()); // to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST Case Failed is :-"+ result.getThrowable()); // to add error/exception in extent report 	
			
			String screenShotPath = getScreenShot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenShotPath)); // to add screenshot in extent report
			
		}
		else if (result.getStatus()==ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "TEST Case Skipped is "+ result.getName());
		}
		else if (result.getStatus()==ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "TEST Case Passed is "+ result.getName());
		}
		
		extent.endTest(extentTest);// Ending test and ends the current test and prepare to create html report
		
		driver.quit();
	}
	
	@AfterTest
	public void setExtent1() {
		extent.flush();
		extent.close();
		System.out.println("After test");
		
	}

}
