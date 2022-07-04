package com.apoorv.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.apoorv.base.TestBase;
import com.apoorv.utilities.ExcelReader;
import com.apoorv.utilities.TestUtil;

public class AddCustomerTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider="dp")
	public void addCustomerTest(String firstName, String lastName, String postCode, String alertText) throws InterruptedException {
		
		click("AddCustomer_CSS");
		type("EnterFirstName_CSS", firstName);
		type("EnterLastName_XPATH", lastName);
		type("EnterPostalCode_CSS", postCode);
		click("AddCustomerAfterData_CSS");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();
		Thread.sleep(3000);
	}

}
