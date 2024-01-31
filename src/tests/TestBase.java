package tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.ExtentReports;

import utils.DriverManager;
import utils.Report;
import constants.Constants;

public class TestBase {
	static String URL;
	static WebDriver driver;
	static JavascriptExecutor js;
	public static ExtentReports _extent;

	@SuppressWarnings("deprecation")
	@BeforeMethod
	public static void Initialize() throws MalformedURLException, InterruptedException {
		URL = Constants.appURL;

		// Initialize report
		_extent = Report.GetExtent();

		// Initialize WebDriver
		driver = DriverManager.Init_WebDriver(Constants.browserType, Constants.headLessMode, Constants.remoteDriver);

		// Initialize JS Executor
		js = (JavascriptExecutor) driver;

		// driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get(URL);
	}

	@AfterTest
	public static void CleanUp() {
		_extent.flush();
	}

	@AfterMethod
	public static void CleanUp2() {
		DriverManager.Close_WebDriver(driver);
	}
}
