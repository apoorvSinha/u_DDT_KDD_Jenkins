package com.apoorv.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.apoorv.utilities.ExcelReader;
import com.apoorv.utilities.ExtentManager;
import com.apoorv.utilities.TestUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;

public class TestBase {
	/*
	 * WebDriver Properties Logs- log4j jar, .logs (application and selenium), log4j
	 * property Extent reports DB Excel Mail Reportng Extent reports Jenkins
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader("./src/test/resources/excel/testdata.XLSX");
	public static WebDriverWait wait;
	public static ExtentManager extent;
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() {
		if (driver == null) {
			// load configurations
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

			// load Object repository
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

			// choosing browser
			if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", ".//src/test/resources/executables/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (config.getProperty("browser").equals("edge")) {
				System.getProperty("webdriver.edge.driver", ".//src/test/resources/executables/msedgedriver.exe");
				driver = new EdgeDriver();
			} else if (config.getProperty("browser").equals("firefox")) {
				System.getProperty("webdriver.gecko.driver", ".//src/test/resources/executables/geckodriver.exe");
				driver = new FirefoxDriver();
			}

			// managing window and timeouts
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);

		}
	}

	public void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();

		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();

		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();

		}

		test.log(Status.INFO, "Clicking on: " + locator);
	}

	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		test.log(Status.INFO, "Typing in: " + locator + " entered value is: " + value);
	}

	static WebElement dropDown;

	public void select(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			dropDown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropDown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropDown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		Select selected = new Select(dropDown);
		selected.selectByVisibleText(value);

		test.log(Status.INFO, "Selecting from dropdown: " + locator + " value as: " + value);
	}

	public Boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void verifyEquals(String expecetd, String actual) {
		try {
			Assert.assertEquals(actual, expecetd);
		} catch (Throwable t) {
			TestUtil.capturePrint();

			// Reportng
			Reporter.log("<br>" + "Verification failure: " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + " ><img src="
					+ TestUtil.screenshotName + " height=1280 width =720></a>");
			// extent
			test.log(Status.FAIL, "Verification failure: " + t.getMessage());
			test.log(Status.FAIL, (Markup) test.addScreenCaptureFromPath(TestUtil.screenshotName));

		}
	}

	@AfterSuite
	public void TearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}

	}
}
