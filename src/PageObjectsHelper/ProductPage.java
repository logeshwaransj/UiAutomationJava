package PageObjectsHelper;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.General;

public class ProductPage {
		WebDriver driver;
		WebDriverWait wait;
			
		public ProductPage(WebDriver driver) {
			this.driver=driver;
			this.wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		}
		
		//Locator for Product Page-->Filter drop down(top right corner side)
		By dropDown_Xpath = By.xpath("//span[@class='a-dropdown-prompt']");
		
		//Locator for Product Page-->choose drop down options list
		By dropDownOptions_Linktext = By.partialLinkText("Price: High to Low");
		
		//Locator for getting product list
		By productList_Xpath = By.xpath("//div[contains(@class, 'a-section')]//img[starts-with(@src, 'https://m.media-amazon.com/images')]");
		
		//Locator for product page --> bullet feature
		By featureBullet_Id = By.id("feature-bullets");
	
		//-------- methods for user actions -----------
		
		//click DropDown button on product page
		public void ClickDropDownButton() {
			General.WaitDOMToLoad(driver);
			
			driver.findElement(dropDown_Xpath).click();
		}
		
		//click DropDown button on product page
		public void ChooseDropDownOptions(By op) {
			wait.until(ExpectedConditions.elementToBeClickable(op));
			driver.findElement(op).click();
		}
		
		//click DropDown button and choose option
		public void SelectDropDownOptions(){
			ClickDropDownButton();
			ChooseDropDownOptions (dropDownOptions_Linktext);
		}
		
		//get product list
		public List<WebElement> GetProductList() {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productList_Xpath));
			
			List<WebElement> productList =  driver.findElements(productList_Xpath);
			return productList;
		}
		
		//get feature bullets details on product page
		public String GetFeatureBullets() {
			General.WaitDOMToLoad(driver);
			WebElement featureBullets =  driver.findElement(featureBullet_Id);
			
			//scroll
			General.ScrollToElement(featureBullets, driver);
			return featureBullets.getText();
		}
		
		public void ClickProduct(WebElement el) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", el);
		}
}
