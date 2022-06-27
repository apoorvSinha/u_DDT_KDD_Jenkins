package com.apoorv.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.apoorv.base.TestBase;
import com.apoorv.utilities.TestUtil;

public class CustomListeners  extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) {
		
	}

	public void onTestSuccess(ITestResult result) {
		
		// reportng

		
		
		
		// flag to generate HTML in report
//		System.setProperty("org.uncommons.reportng.escape-output", "false");
//
//		Reporter.log("Login successfully executed");

		// open screenshot same page
		// Reporter.log("<a
		// href=\"C:\\Users\\apoorv\\eclipse-workspace\\u_DDT_KDD_Jenkins\\src\\test\\resources\\pics\\shv.jpg\">Screenshot</a>");

		// to open screenshot in new page
		// Reporter.log("<a target=\"blank\"
		// href=\"C:\\Users\\apoorv\\eclipse-workspace\\u_DDT_KDD_Jenkins\\src\\test\\resources\\pics\\shv.jpg\">Screenshot</a>");

		// add thumbnail
		// Reporter.log("<a target=\"blank\"
		// href=\"C:\\Users\\apoorv\\eclipse-workspace\\u_DDT_KDD_Jenkins\\src\\test\\resources\\pics\\shv.jpg\"><img
		// src=\"C:\\Users\\apoorv\\eclipse-workspace\\u_DDT_KDD_Jenkins\\src\\test\\resources\\pics\\shv.jpg\"
		// height=200 width =200></a>");
	
		
	

	
	}

	public void onTestFailure(ITestResult result) {
		
		
		//
		//Reportng
		
		// flag to generate HTML in report
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		TestUtil.capturePrint();
		Reporter.log("Captured screenshot");
		// add thumbnail
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + " ><img src=" + TestUtil.screenshotName+ " height=1280 width =720></a>");
	
	
	
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
	}

}
