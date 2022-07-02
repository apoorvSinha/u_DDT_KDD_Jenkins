package com.apoorv.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.apoorv.base.TestBase;

public class BankManagerLoginTest extends TestBase {

	@Test
	public void loginAsBankManager() {
		//Assert.assertEquals("xyz", "abc");
		click("bmlBtn_CSS");
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("AddCustomer_CSS"))));
		Assert.fail("login not successful");

	}
}
