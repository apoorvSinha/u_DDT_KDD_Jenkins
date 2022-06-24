package com.apoorv.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.apoorv.base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test
	public void loginAsBankManager() {
		//flag to generate HTML in report
		System.setProperty("org.uncommons.reportng.escape-output", "fasle");
		
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("AddCustomer"))));
		
		//reportng
		Reporter.log("Login successfully executed");
		
		//open screenshot same page
		//Reporter.log("<a href=\"C:\\Users\\apoorv\\eclipse-workspace\\u_DDT_KDD_Jenkins\\src\\test\\resources\\pics\\shv.jpg\">Screenshot</a>");
		
		//to open screenshot in new page
		//Reporter.log("<a target=\"blank\" href=\"C:\\Users\\apoorv\\eclipse-workspace\\u_DDT_KDD_Jenkins\\src\\test\\resources\\pics\\shv.jpg\">Screenshot</a>");
		
		//add thumbnail
		Reporter.log("<a target=\"blank\" href=\"C:\\Users\\apoorv\\eclipse-workspace\\u_DDT_KDD_Jenkins\\src\\test\\resources\\pics\\shv.jpg\"><img src=\"C:\\Users\\apoorv\\eclipse-workspace\\u_DDT_KDD_Jenkins\\src\\test\\resources\\pics\\shv.jpg\" height=200 width =200></a>");
	}
}
