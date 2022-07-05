package com.apoorv.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.apoorv.base.TestBase;

public class TestUtil extends TestBase {
	public static String screenshotPath;
	public static String screenshotName;

	public static void capturePrint() {
		LocalDateTime ldt = LocalDateTime.now();
		screenshotPath = ".//target/surefire-reports/html";
		screenshotName = "errorAt" + ldt.getHour() + "_" + ldt.getMinute() + "_" + ldt.getSecond() + ".jpeg";

		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		File dest = new File(screenshotPath + "/" + screenshotName);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {

		}
	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
		excel = new ExcelReader("./src/test/resources/excel/testdata.XLSX");
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}

	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		excel = new ExcelReader("./src/test/resources/excel/testdata.XLSX");
		String sheetName = "test_suite";
		int rows = excel.getRowCount(sheetName);
		for (int rnum = 2; rnum < rows; rnum++) {
			String testCase = excel.getCellData(sheetName, "TC_ID", rnum);
			if (testCase.equalsIgnoreCase(testName)) {
				String runMode = excel.getCellData(sheetName, "Runmode", rnum);
				if (runMode.equalsIgnoreCase("y")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
