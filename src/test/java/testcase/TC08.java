package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Constant;
import Common.Utilities;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class TC08 extends TestBase {

	private HomePage homePage;
	private RegisterPage registerPage;
	private LoginPage loginPage;

	@BeforeMethod
	// Init Register page webdriver before starting
	public void Initdriver() {

		registerPage = new RegisterPage(driver);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@Test(description = "User can't login with an account hasn't been activated")
	public void TC_08() {

		/*
		 * Precondition: Create and activate a new account
		 */
		// Navigate to Register tab
		homePage.clickTab(Constant.TAB_REGISTER);

		// Create new account
		registerPage.RegisterNewUser(Utilities.EmailGenerator(), Constant.PASSWORD, Constant.PASSWORD, Constant.PID);

		// Print out new Email created.
		System.out.println("Account created: " + Constant.EMAIL_NEWLY_CREATE);

		// Step 1: Init Login Page driver and start chrome were done in BeforeClass
		// Step 2: Click Login Tab
		homePage.clickTab(Constant.TAB_LOGIN);

		// Step 3: Login with username and password of account hasn't been activated
		loginPage.Login(Constant.EMAIL_NEWLY_CREATE, Constant.PASSWORD);

		// VP: User can't login and message "Invalid username or password. Please try
		// again." appears.
		Assert.assertEquals(loginPage.GetLoginErrorMessage(), Constant.MessageLoginPage.MessageInvalidUsernamePassword);

	}
}
