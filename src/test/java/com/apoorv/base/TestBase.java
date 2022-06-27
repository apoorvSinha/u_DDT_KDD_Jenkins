package com.apoorv.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.apoorv.utilities.ExcelReader;
import com.apoorv.utilities.ExtentManager;

public class TestBase {
	/*
	 * WebDriver
	 * Properties
	 * Logs- log4j jar, .logs (application and selenium), log4j property
	 * Extent reports
	 * DB
	 * Excel
	 * Mail
	 * Reportng
	 * Extent reports
	 * Jenkins
	 * */
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel ;
	public static WebDriverWait wait;
	public static ExtentManager extent;
	
	@BeforeSuite
	public void setUp() {
		if(driver==null) {
			//load configurations
			try {
				fis = new FileInputStream(".//src/test/resources/properties/config.propeties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//load Object repository
			try {
				fis = new FileInputStream(".//src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			//choosing browser
			if(config.getProperty("browser").equals("chrome")){
				System.setProperty("webdriver.chrome.driver", ".//src/test/resources/executables/chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(config.getProperty("browser").equals("edge")){
				System.getProperty("webdriver.edge.driver", ".//src/test/resources/executables/msedgedriver.exe");
				driver = new EdgeDriver();
			}
			else if(config.getProperty("browser").equals("firefox")) {
				System.getProperty("webdriver.gecko.driver", ".//src/test/resources/executables/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			
			//managing window and timeouts
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
			

		}
	}
	
	public Boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	@AfterSuite
	public void TearDown() throws InterruptedException {
		if(driver!=null) {
			Thread.sleep(3000);
			driver.quit();
		}
		
	}
}
