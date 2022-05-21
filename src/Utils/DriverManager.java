package utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import Constants.Constants;

public class DriverManager {
	private static WebDriver driver = null; 
	public static WebDriver Init_WebDriver(String broswerType, boolean headLessMode,boolean remoteDriver) throws MalformedURLException
	{
		System.setProperty("webdriver.chrome.driver",Constants.chromeDriverPath);
		if(driver != null)
			return driver;
		
		if((broswerType == Constants.browserType) && (remoteDriver != false)) //for Selenium Grid
		{
			ChromeOptions capability = new ChromeOptions();
			
			capability.setCapability("platformName","Windows");
			capability.setCapability("browserVersion","101");
			
			driver = new RemoteWebDriver(new URL("http://192.168.0.101:4444"),capability);
		}
		else  
		{
			ChromeOptions capability = new ChromeOptions();
			if(headLessMode)
				capability.addArguments("headless");
		    
			driver = new ChromeDriver(capability);
		}
		
		return driver;
	}
	
	public static void Close_WebDriver(WebDriver driver)
	{
		if(driver != null)
		driver.quit();
	}
}
