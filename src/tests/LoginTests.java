package tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import pageobjectshelper.LoginPage;
import utils.General;
import utils.Screenshot;
import constants.Constants;
import constants.TestDataConstants;

public class LoginTests extends TestBase {
	ExtentTest _test;

	@Test(priority = 1)
	public void LoginWithValidCredentialsTest() throws Exception {
		try {
			_test = _extent.startTest(new Object() {
			}.getClass().getEnclosingMethod().getName(), "Test Valid Login in Amazon wesite");

			_test.log(LogStatus.INFO, "Opening amazon.in and Starting test");

			LoginPage loginpage = new LoginPage(driver);

			loginpage.ClickUserAccountLink();

			_test.log(LogStatus.INFO, "Entering Email Id");
			loginpage.EnterUserMail(TestDataConstants.username);
			loginpage.ClickContinueButton();

			_test.log(LogStatus.INFO, "Entering Password");
			loginpage.EnterUserPassword(TestDataConstants.password);
			loginpage.ClickContinueButton();

			_test.log(LogStatus.INFO, "checking home page URL and verify content");
			String currenURL = General.GetCurrentURL(driver);
			Assert.assertEquals(currenURL, Constants.homePageURL);

			Assert.assertNotEquals(loginpage.GetAccountsLinkText(), "Hello, sign in",
					"Home page not shown after login");

			_test.log(LogStatus.INFO, _test.addScreenCapture(Screenshot.TakeScreenShot(driver)) + "Screen Status");
			_test.log(LogStatus.PASS, "Test case passed for valid login");
		} catch (Exception e) {
			_test.log(LogStatus.FAIL, "Test Case Failed for LoginWithValidCredentialsTest" + e.getMessage());
			_test.log(LogStatus.INFO, _test.addScreenCapture(Screenshot.TakeScreenShot(driver)) + "Test Failed");

			throw new Exception("Test Case Failed for LoginWithValidCredentialsTest" + e.getMessage());
		}
	}

	@Test(priority = 2)
	public void LoginValidationWithIncorrectEmailTest() throws Exception {
		try {
			_test = _extent.startTest(new Object() {
			}.getClass().getEnclosingMethod().getName(), "Test Login with Incorrect Email in Amazon wesite");

			_test.log(LogStatus.INFO, "Opening amazon.in and Starting test");

			LoginPage loginpage = new LoginPage(driver);

			loginpage.ClickUserAccountLink();

			_test.log(LogStatus.INFO, "entering invalid user name");
			loginpage.EnterUserMail("sjlogeshwaran33@gmail.com");
			loginpage.ClickContinueButton();

			_test.log(LogStatus.INFO, "checking validation message");

			loginpage.CheckValidationMessageForInValidEmail();

			_test.log(LogStatus.INFO, _test.addScreenCapture(Screenshot.TakeScreenShot(driver)) + "Screen Status");
			_test.log(LogStatus.PASS, "Test case passed for invalid login");

		} catch (Exception e) {
			_test.log(LogStatus.FAIL, "Test Case Failed for LoginValidationWithIncorrectEmailTest" + e.getMessage());
			_test.log(LogStatus.INFO, _test.addScreenCapture(Screenshot.TakeScreenShot(driver)) + "Test Failed");

			throw new Exception("Test Case Failed for LoginValidationWithIncorrectEmailTest" + e.getMessage());
		}
	}

	@Test(priority = 3)
	public void LoginValidationWithInValidEmailTest() throws Exception {
		try {
			_test = _extent.startTest(new Object() {
			}.getClass().getEnclosingMethod().getName(),
					"Test with Invalid Login Email Format and validation in Amazon wesite");

			_test.log(LogStatus.INFO, "Opening amazon.in and Starting test");

			LoginPage loginpage = new LoginPage(driver);

			loginpage.ClickUserAccountLink();

			_test.log(LogStatus.INFO, "entering invalid user name");
			loginpage.EnterUserMail("sjlogeshwaran33");
			loginpage.ClickContinueButton();

			_test.log(LogStatus.INFO, "checking validation message");

			loginpage.CheckValidationMessageForInValidEmail();

			_test.log(LogStatus.INFO, _test.addScreenCapture(Screenshot.TakeScreenShot(driver)) + "Screen Status");
			_test.log(LogStatus.PASS, "Test case passed for invalid mail Id format");
		} catch (Exception e) {
			_test.log(LogStatus.FAIL, "Test Case Failed for LoginValidationWithInValidEmailTest" + e.getMessage());
			_test.log(LogStatus.INFO, _test.addScreenCapture(Screenshot.TakeScreenShot(driver)) + "Test Failed");

			throw new Exception("Test Case Failed for LoginValidationWithInValidEmailTest" + e.getMessage());
		}
	}

	@Test(priority = 4)
	public void LoginValidationWithEmptyEmailTest() throws Exception {
		try {
			_test = _extent.startTest(new Object() {
			}.getClass().getEnclosingMethod().getName(), "Test Login using Empty Email Id  in Amazon wesite");

			_test.log(LogStatus.INFO, "Opening amazon.in and Starting test");

			LoginPage loginpage = new LoginPage(driver);

			loginpage.ClickUserAccountLink();

			_test.log(LogStatus.INFO, "entering invalid user name");
			loginpage.EnterUserMail(" ");
			loginpage.ClickContinueButton();

			_test.log(LogStatus.INFO, "checking validation message");

			loginpage.CheckValidationMessageForEmptyEmail();

			_test.log(LogStatus.INFO, _test.addScreenCapture(Screenshot.TakeScreenShot(driver)) + "Screen Status");
			_test.log(LogStatus.PASS, "Test case passed for Login using Empty Email Id");
		}

		catch (Exception e) {
			_test.log(LogStatus.FAIL, "Test Case Failed for LoginValidationWithEmptyEmailTest" + e.getMessage());
			_test.log(LogStatus.INFO, _test.addScreenCapture(Screenshot.TakeScreenShot(driver)) + "Test Failed");

			throw new Exception("Test Case Failed for LoginValidationWithEmptyEmailTest" + e.getMessage());
		}
	}

	@Test(priority = 4)
	public void LoginValidationWithEmptyPasswordTest() throws Exception {
		try {
			_test = _extent.startTest(new Object() {
			}.getClass().getEnclosingMethod().getName(), "Test Login using Empty Password in Amazon wesite");

			_test.log(LogStatus.INFO, "Opening amazon.in and Starting test");

			LoginPage loginpage = new LoginPage(driver);

			loginpage.ClickUserAccountLink();

			_test.log(LogStatus.INFO, "entering user name");
			loginpage.EnterUserMail(TestDataConstants.username);
			loginpage.ClickContinueButton();

			_test.log(LogStatus.INFO, "signing in without password");

			loginpage.ClickContinueButton();

			_test.log(LogStatus.INFO, "checking validation message");

			loginpage.CheckValidationMessageForEmptyPassword();

			_test.log(LogStatus.INFO, _test.addScreenCapture(Screenshot.TakeScreenShot(driver)) + "Screen Status");
			_test.log(LogStatus.PASS, "Test case passed for Login using Empty Password");
		} catch (Exception e) {
			_test.log(LogStatus.FAIL, "Test Case Failed for LoginValidationWithEmptyPasswordTest" + e.getMessage());
			_test.log(LogStatus.INFO, _test.addScreenCapture(Screenshot.TakeScreenShot(driver)) + "Test Failed");

			throw new Exception("Test Case Failed for LoginValidationWithEmptyPasswordTest" + e.getMessage());
		}
	}

	@Test(priority = 5)
	public void LoginValidationWithIncorrectPasswordTest() throws Exception {
		try {
			_test = _extent.startTest(new Object() {
			}.getClass().getEnclosingMethod().getName(), "Test Login using Incorrect Password in Amazon wesite");

			_test.log(LogStatus.INFO, "Opening amazon.in and Starting test");

			LoginPage loginpage = new LoginPage(driver);

			loginpage.ClickUserAccountLink();

			_test.log(LogStatus.INFO, "entering user name");
			loginpage.EnterUserMail(TestDataConstants.username);
			loginpage.ClickContinueButton();

			_test.log(LogStatus.INFO, "entering incorrect password");
			loginpage.EnterUserPassword("logeshpass");

			loginpage.ClickContinueButton();

			_test.log(LogStatus.INFO, "checking validation message");

			loginpage.CheckValidationMessageForInValidPassword();
			;

			_test.log(LogStatus.INFO, _test.addScreenCapture(Screenshot.TakeScreenShot(driver)) + "Screen Status");
			_test.log(LogStatus.PASS, "Test case passed for Login using Incorrect Password");
		} catch (Exception e) {
			_test.log(LogStatus.FAIL, "Test Case Failed for LoginValidationWithIncorrectPasswordTest" + e.getMessage());
			_test.log(LogStatus.INFO, _test.addScreenCapture(Screenshot.TakeScreenShot(driver)) + "Test Failed");

			throw new Exception("Test Case Failed for LoginValidationWithIncorrectPasswordTest" + e.getMessage());
		}
	}
}