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

public class AddCustomerTest extends TestBase {
	
	@Test(dataProvider="getData")
	public void addCustomer(String firstName, String lastName, String postCode, String alertText ) {
		driver.findElement(By.cssSelector(OR.getProperty("AddCustomer"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("EnterFirstName"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(OR.getProperty("EnterLastName"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(OR.getProperty("EnterPostalCode"))).sendKeys(postCode);
		driver.findElement(By.cssSelector(OR.getProperty("AddCustomerAfterData"))).click();;
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();
		Reporter.log("Customer addedd successfully");
	}
	
	@DataProvider
	public Object[][] getData(){
		excel = new ExcelReader("./src/test/resources/excel/testdata.XLSX");
		String sheetName="AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows-1][cols];
		
		for(int rowNum=2; rowNum<=rows; rowNum++) {
			for(int colNum=0; colNum< cols; colNum++) {
				data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}
}
