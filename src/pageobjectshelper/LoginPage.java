package pageobjectshelper;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import constants.Constants;
import constants.TestDataConstants;
import utils.General;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// Locator for Accounts
	By accountsLoginOption = By.id("nav-link-accountList");

	By userMailIdInput = By.name("email");

	By passwordInput = By.name("password");

	By continueButton = By.className("a-button-input");

	By accountsListText = By.id("nav-link-accountList-nav-line-1");

	// Validation for email
	By erroNotification = By.className("a-alert-heading");
	By erroNotificationMessage = By.className("a-list-item");

	// Validation for empty email
	By erroNotificationMessageForEmptyMailInput = By
			.xpath("//*[@id=\"auth-email-missing-alert\"]/div/div[@class=\"a-alert-content\"]");
	By erroNotificationMessageForEmptyPasswordInput = By
			.xpath("//*[@id=\"auth-password-missing-alert\"]/div/div[@class=\"a-alert-content\"]");
	//

	// -------- methods for user actions -----------

	// click DropDown button on product page
	public void ClickUserAccountLink() throws Exception {
		General.WaitDOMToLoad(driver);

		driver.findElement(accountsLoginOption).click();
	}

	public void EnterUserMail(String email) {
		General.WaitDOMToLoad(driver);

		driver.findElement(userMailIdInput).sendKeys(email);
	}

	public void EnterUserPassword(String password) {
		General.WaitDOMToLoad(driver);

		driver.findElement(passwordInput).sendKeys(password);
	}

	public void ClickContinueButton() {
		General.WaitDOMToLoad(driver);

		driver.findElement(continueButton).click();
		General.WaitDOMToLoad(driver);
	}

	public String GetAccountsLinkText() {
		return driver.findElement(accountsListText).getText();
	}

	public void CheckValidationMessageForInValidEmail() {
		driver.findElement(erroNotification).isDisplayed();
		Assert.assertTrue(
				driver.findElement(erroNotificationMessage).getText().contains(Constants.invalidEmailErrorMessage));
	}

	public void CheckValidationMessageForEmptyEmail() {

		Assert.assertTrue(driver.findElement(erroNotificationMessageForEmptyMailInput).getText().trim()
				.contains("Enter your email or mobile phone number"));
	}

	public void CheckValidationMessageForEmptyPassword() {

		Assert.assertTrue(driver.findElement(erroNotificationMessageForEmptyPasswordInput).getText().trim()
				.contains("Enter your password"));
	}

	public void CheckValidationMessageForInValidPassword() {
		driver.findElement(erroNotification).isDisplayed();
		Assert.assertTrue(
				driver.findElement(erroNotificationMessage).getText().contains(Constants.invalidPasswordErrorMessage));
	}

	public void LoginUsingDefaultCredentials() throws Exception {

		ClickUserAccountLink();

		EnterUserMail(TestDataConstants.username);
		ClickContinueButton();

		EnterUserPassword(TestDataConstants.password);
		ClickContinueButton();

		Assert.assertEquals(General.GetCurrentURL(driver), Constants.homePageURL);
	}

}
