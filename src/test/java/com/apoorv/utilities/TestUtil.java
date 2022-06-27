package com.apoorv.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.apoorv.base.TestBase;


public class TestUtil extends TestBase {
	public static String screenshotPath;
	public static String screenshotName;
	
	public static void capturePrint() {
		LocalDateTime ldt = LocalDateTime.now();
		screenshotPath = ".//target/surefire-reports/html";
		screenshotName = "errorAt"+ldt.getHour()+"_"+ldt.getMinute()+"_"+ldt.getSecond()+".jpeg";
		
		TakesScreenshot screen = (TakesScreenshot)driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		File dest = new File(screenshotPath+"/"+screenshotName);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {

		}

	}
}
