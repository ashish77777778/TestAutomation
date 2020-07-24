package com.automation.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.automation.qa.base.Testbase;
import org.apache.commons.io.*;

public class TestUtil extends Testbase {

	public static long IMPLICIT_WAIT = 25;
	public static long PAGE_LOAD_TIMEOUT = 25;

	public void ClickOnElementUsingActionMethod(WebElement element) {

		// element = driver.findElement(By.linkText("Contact"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}

	public void switchToFrame(String name) {
		driver.switchTo().frame(name);
	}

	public void clickUsingJavaScript(WebElement ele) {

		// WebElement ele = driver.findElement(By.xpath("element_xpath"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	public static String TESTDATA_SHEET_PATH = "C:\\Users\\ashis\\eclipse-workspace\\TestAutomation\\src\\main\\java\\com\\automation\\qa\\testdata\\TestData.xlsx";

	static Workbook book;
	static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;

		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum());
		System.out.println(sheet.getRow(0).getLastCellNum());

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				System.out.println(data[i][k]);
			}

		}

		return data;

	}
	
	public static void takeScreenShot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		//File Dest=new File("D:\\ScreenShot\\screenshot.png");
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(src, new File(currentDir + "/Screenshots/"+ System.currentTimeMillis()+ ".png") );
		
	}

}	
