package Tests;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import PageObjectsHelper.MenuPage;
import PageObjectsHelper.ProductPage;
import Utils.General;
import Utils.Screenshot;
import dev.failsafe.internal.util.Assert;

public class FilterProductTest extends TestBase   {
	 ExtentTest _test;

    @Test
	public void FilterSamsungTVTest() throws Exception {
    	try {
    	_test = _extent.startTest(this.getClass().getName(), "Test to get 2nd highest samsung TV product");

    	_test.log(LogStatus.INFO, "Opened amazon.in and Starting test");
    	
    	MenuPage menu = new MenuPage(driver);
    	
    	_test.log(LogStatus.INFO, "Clicking HarmBurgerButton");
    	menu.ClickHamBurgerButton();
    	
    	_test.log(LogStatus.INFO, "Filtering Main Category - TV, Appliances, Electronics");
    	menu.FilterMainCategory();
    	
    	_test.log(LogStatus.INFO, "Filtering sub Category - TV");
    	menu.FilterSubCategory(); //todo
    	
    	_test.log(LogStatus.INFO, "Filtering - Samsung brand");
    	menu.FilterBrand(); //todo
    	
    	ProductPage product = new ProductPage(driver);
    	
    	_test.log(LogStatus.INFO, "Choosing drop down on product list page - selecting 'Price High to Low'");
    	product.SelectDropDownOptions();
    	
    	_test.log(LogStatus.INFO, "getting product list");
    	
    	List<WebElement> productList= product.GetProductList();
    	Assert.isTrue(productList.size() > 0, "Prouduct count is 0");
    	_test.log(LogStatus.INFO,"Prouduct Count is: "+Integer.toString(productList.size()));
    	
    	_test.log(LogStatus.INFO,"clicking 2nd product from list");
    	String parentWindow = General.GetCurrentWindowDetails(driver);
    	
    	//productList.get(2).click(); not working in Headless
    	product.ClickProduct(productList.get(1));
    	
    	_test.log(LogStatus.INFO,"Switching window to get product details");
    	General.SwitchWindow(parentWindow, driver);
    	
    	_test.log(LogStatus.INFO,"Bullet Features: "+product.GetFeatureBullets());
    	
    	_test.log(LogStatus.PASS,"test passed for filtering TV product");
    	_test.log(LogStatus.INFO,_test.addScreenCapture(Screenshot.TakeScreenShot(driver))+ "Screenshot of last step");
    	
    	_test.log(LogStatus.INFO,"test completed");
    	}
    	catch (Exception e)
    	{
		_test.log(LogStatus.FAIL,"Test Case Failed while getting product list" + e.getMessage());
		_test.log(LogStatus.INFO,_test.addScreenCapture(Screenshot.TakeScreenShot(driver))+ "Test Failed");
		
		throw new Exception("Test Case Failed while getting product list" + e.getMessage());
    	}
    }
}
