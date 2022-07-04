package com.apoorv.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import com.apoorv.base.TestBase;
import com.apoorv.utilities.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider="dp")
	public void openAccountTest(String customer, String currency ) throws InterruptedException {
		click("openAccount_CSS");
		select("customer_CSS", customer.trim());
		select("currency_CSS", currency.trim());
		click("process_CSS");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}


}
