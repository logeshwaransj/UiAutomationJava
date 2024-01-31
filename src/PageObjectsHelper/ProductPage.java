package pageobjectshelper;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import constants.Constants;
import utils.General;

public class ProductPage {
	WebDriver driver;
	WebDriverWait wait;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// Search
	By searchboxInput = By.id("twotabsearchtextbox");
	By searchButton = By.id("nav-search-submit-button");

	// Locator for Product Page-->Filter drop down(top right corner side)
	By dropDown_Xpath = By.xpath("//span[@class='a-dropdown-prompt']");

	// Locator for Product Page-->choose drop down options list
	By dropDownOptions_Linktext = By.partialLinkText("Price: High to Low");

	// Locator for getting product list
	By productList_Xpath = By
			.xpath("//div[contains(@class, 'a-section')]//img[starts-with(@src, 'https://m.media-amazon.com/images')]");
	By productListLinkText_Xpath = By.xpath("//div[contains(@data-cy, 'title-recipe')]//h2//a//span");

	// Locator for product page --> bullet feature
	By featureBullet_Id = By.id("feature-bullets");

	By noResultsMessageXpath = By.xpath(
			"//span[@data-component-type=\"s-search-results\"]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]");

	// -------- methods for user actions -----------

	public void EnterSearchKeywords(String keyword) {
		General.WaitDOMToLoad(driver);
		driver.findElement(searchboxInput).sendKeys(keyword);
	}

	public void ClickSearchButton() {
		General.WaitDOMToLoad(driver);
		driver.findElement(searchButton).click();
	}

	// click DropDown button on product page
	public void ClickDropDownButton() {
		General.WaitDOMToLoad(driver);

		driver.findElement(dropDown_Xpath).click();
	}

	// click DropDown button on product page
	public void ChooseDropDownOptions(By op) {
		wait.until(ExpectedConditions.elementToBeClickable(op));
		driver.findElement(op).click();
	}

	// click DropDown button and choose option
	public void SelectDropDownOptions() {
		ClickDropDownButton();
		ChooseDropDownOptions(dropDownOptions_Linktext);
	}

	// get product list
	public List<WebElement> GetProductList() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productList_Xpath));

		List<WebElement> productList = driver.findElements(productList_Xpath);
		return productList;
	}

	// get product search results text list
	public List<WebElement> GetProductSearchList() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productListLinkText_Xpath));

		List<WebElement> productList = driver.findElements(productListLinkText_Xpath);
		return productList;
	}

	// get feature bullets details on product page
	public String GetFeatureBullets() {
		General.WaitDOMToLoad(driver);
		WebElement featureBullets = driver.findElement(featureBullet_Id);

		// scroll
		General.ScrollToElement(featureBullets, driver);
		return featureBullets.getText();
	}

	public void ClickProduct(WebElement el) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", el);
	}

	// Validate search results
	public boolean ValidateSearchResults(List<WebElement> productList, String keyword) throws Exception {
		var splitStr = keyword.split("\\s+");
		for (WebElement item : productList) {
			// System.out.print(item.getText());
			boolean result = containsWords(item.getText(), splitStr);
			return false;
		}
		return true;
	}

	public static boolean containsWords(String inputString, String[] items) {
		boolean found = true;
		for (String item : items) {
			// System.out.print(item+" found in "+ inputString);
			if (!inputString.contains(item)) {
				found = false;
				// System.out.print(item+" not found in "+ inputString);
				break;
			}
		}
		return found;
	}

	public void CheckNoResultsFoundMessage() {
		driver.findElement(noResultsMessageXpath).isDisplayed();
		Assert.assertTrue(
				driver.findElement(noResultsMessageXpath).getText().contains(Constants.noResultsfoundMessage));
	}
}
