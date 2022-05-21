package Tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;

import Utils.DriverManager;
import Utils.Report; 
import Constants.Constants;

public class TestBase {
    static String URL;
    static WebDriver driver;
    static JavascriptExecutor js;
    public static ExtentReports _extent;
    
    @SuppressWarnings("deprecation")
	@BeforeTest
	public static void Initialize() throws MalformedURLException
	{
	   URL=Constants.appURL;
	   
	   //Initialize report
	   _extent = Report.GetExtent();
		
	   //Initialize WebDriver
	   driver = DriverManager.Init_WebDriver(Constants.browserType,Constants.headLessMode,Constants.remoteDriver);
	   
	   //Initialize JS Executor
	   js = (JavascriptExecutor) driver;
	   
	   //driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	   driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
	   //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	   driver.manage().window().maximize();
	   
	   driver.get(URL);
	}
	
    @AfterTest
	public static void CleanUp()
	{	
		DriverManager.Close_WebDriver(driver);
		
		_extent.flush();
	}
}
