package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Constant;
import Common.Utilities;
import pages.LoginPage;
import pages.RegisterPage;

public class TC08 extends TestBase {

	String email = Utilities.EmailGenerator();

	@BeforeClass
	// Init Register page webdriver before starting
	public void Initdriver() {

		registerPage = new RegisterPage(driver);
		loginPage = new LoginPage(driver);
	}

	@BeforeMethod
	public void CreateAccount() {
		registerPage.clickRegisterTab();
		registerPage.RegisterNewUser(email, Constant.PASSWORD, Constant.PASSWORD, Constant.PID);

	}

	@Test(description = "User can't login with an account hasn't been activated")
	public void TC_08() {
		/*
		 * Step 1: Init Login Page driver and start chrome were done in BeforeClass
		 */
		// Step 2: Click Login Tab
		homePage.clickTab(Constant.TAB_LOGIN);
		// Step 3: Login with username and password of account hasn't been activated
		loginPage.Login(email, Constant.PASSWORD);
		// VP: User can't login and message "Invalid username or password. Please try
		// again." appears.
		Assert.assertEquals(loginPage.GetLoginErrorMessage(), Constant.MessageLoginPage.MessageInvalidUsernamePassword);

	}
}
