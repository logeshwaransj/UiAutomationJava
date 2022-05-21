package PageObjectsHelper;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.General;

public class MenuPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public MenuPage(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	}
	
	//Locator for hamBurger button
		By hamBurgerBtn_Id = By.id("nav-hamburger-menu");
	
	//Locator for Main Categories Ex: TV, Appliances, Electronics
		By tvCategory_LinkText = By.linkText("TV, Appliances, Electronics");
		
	//Locator for Sub Categories Ex: TV,Appliances,Electronics---->TV
		By televisionsSubCategory_LinkText = By.linkText("Televisions");
	
	//Locator for TV--->Brands
		By samsung_Xpath = By.xpath("//span[@class='a-size-base a-color-base'][normalize-space()='Samsung']");	
	
	//-------- methods for user actions -----------
	
	//click hamburger	
	public void ClickHamBurgerButton() {
		General.WaitDOMToLoad(driver);
		wait.until(ExpectedConditions.elementToBeClickable(hamBurgerBtn_Id));
		driver.findElement(hamBurgerBtn_Id).click();
	}
	
	//filter main category
	public void FilterMainCategory() {
		wait.until(ExpectedConditions.elementToBeClickable(tvCategory_LinkText));
		
		WebElement mainCategory = driver.findElement(tvCategory_LinkText);
		
		//scroll
		General.ScrollToElement(mainCategory, driver);
		mainCategory.click();
	}
	
	//filter sub category
	public void FilterSubCategory() {
		wait.until(ExpectedConditions.elementToBeClickable(televisionsSubCategory_LinkText));
		
		WebElement subCategory = driver.findElement(televisionsSubCategory_LinkText);
		subCategory.click();
	}
	
	//filter brand
	public void FilterBrand() {
		wait.until(ExpectedConditions.elementToBeClickable(samsung_Xpath));
		
		WebElement brand = driver.findElement(samsung_Xpath);
		//scroll
		General.ScrollToElement(brand, driver);
		
		brand.click();
	}
}