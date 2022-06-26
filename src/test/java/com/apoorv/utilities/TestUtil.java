package com.apoorv.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.apoorv.base.TestBase;


public class TestUtil extends TestBase {

	public static String screenshotPath = ".//test-output/html";
	public static String screenshotName = "errorAt"+NewDateTimeApis.TimeStamp()+".jpg";
	
	public static void capturePrint() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(screenshotPath);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {

		}

	}
}
