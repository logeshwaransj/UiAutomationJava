package utils;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class General {
	public static void ScrollToElement(WebElement element, WebDriver driver)
	{
		//scroll to element
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	
	}
	
	public static void CheckWindowsCount(String s, WebDriver driver)
	{
		// To do
	}
	
	public static String GetCurrentWindowDetails(WebDriver driver)
	{
		String originalWindow = driver.getWindowHandle();
		return originalWindow;
	}
	
	public static void SwitchWindow(String originalWindow, WebDriver driver)
	{
		// iterate each window
        for (String windowHandle : driver.getWindowHandles()) {
         if(!originalWindow.contentEquals(windowHandle)) 
         {
                driver.switchTo().window(windowHandle);
                System.out.print("True");
                break;
          }
        }
	}
	
	public static boolean WaitDOMToLoad(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	    
		//wait for JS to load
	    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	          return js.executeScript("return document.readyState")
	              .toString().equals("complete");
	        }
	      };
	      
	   // wait for jQuery to load
	      ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
			      try {
			        return ((Long)js.executeScript("return jQuery.active") == 0);
			      }
			      catch (Exception e) {
			        return true;
			      }
		        }
		      };
		   return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
}
