package com.automation.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.automation.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import freemarker.template.SimpleDate;

public class Testbase {

	public static WebDriver driver;
	public static Properties prop;
	public  ExtentReports extent;
	public  ExtentTest extentTest;

	public Testbase() {

		try {
			prop = new Properties();
			FileInputStream fi = new FileInputStream(
					"C:\\Users\\ashis\\eclipse-workspace\\TestAutomation\\src\\main\\java\\com\\automation\\qa\\config\\config.properties");
			prop.load(fi);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void intialization() {

		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Software\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Software\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
	
	
//	  @BeforeTest
//	  public void setExtent() {
//	  extent = new ExtentReports("C:\\Users\\ashis\\eclipse-workspace\\TestAutomation\\test-output\\ExtentReport.html", true);
//	  extent.addSystemInfo("Host Name","Ashish PC"); 
//	  extent.addSystemInfo("UserName", "Ashish");
//	  extent.addSystemInfo("Environment", "QA");
//	  System.out.println("Before test");
//	  
//	  }
	 

	
		
//		  @AfterTest 
//		  public void setExtent1() { 
//			  extent.flush(); 
//			  extent.close();
//			  System.out.println("After test");
//		  
//		  }
		 
	public static String getScreenShot(WebDriver driver, String screenShotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destination =  System.getProperty("user.dir")+"/FailedTestsScreenshots/"+ screenShotName + dateName+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(src, finalDestination);
		return destination;
	}
	
	public static void takeScreenShot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		//File Dest=new File("D:\\ScreenShot\\screenshot.png");
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(src, new File(currentDir + "/Screenshots/"+ System.currentTimeMillis()+ ".png") );
		
	}
}
