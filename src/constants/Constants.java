package constants;

public class Constants {

	public static String appURL = "https://www.amazon.in/";
	public static String browserType = "Chrome";

	public static String chromeDriverPath = "D:\\Logesh\\Java\\Jars\\chromedriver.exe";

	// for platform independent test run
	public static boolean headLessMode = true;

	// for running test on Selenium RemoteDriver (Grid)
	public static boolean remoteDriver = false;

	// Home page URL
	public static String homePageURL = "https://www.amazon.in/?ref_=nav_ya_signin";

	// Invalid Error message text
	public static String invalidEmailErrorMessage = "We cannot find an account with that email address";

	public static String invalidPasswordErrorMessage = "Your password is incorrect";

	public static String noResultsfoundMessage = "No results for";
}
