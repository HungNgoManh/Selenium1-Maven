package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Constant;
import Common.Utilities;
import pages.ChangePasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class TC09 extends TestBase {

	@BeforeMethod
	// Init Register page webdriver before starting
	public void Initdriver() {

		homePage = new HomePage(driver);
		registerPage = new RegisterPage(driver);
		changePassword = new ChangePasswordPage(driver);
		loginPage = new LoginPage(driver);

	}

	@Test(description = "Check if user can create new account")
	public void TC_09() {

		/*
		 * Precondition: Create and activate a new account
		 */
		// Navigate to Register tab
		homePage.clickTab(Constant.TAB_REGISTER);

		// Create new account
		registerPage.RegisterNewUser(Utilities.EmailGenerator(), Constant.PASSWORD, Constant.PASSWORD, Constant.PID);

		// Print out new Email created.
		System.out.println("Account created: "+ Constant.EMAIL_NEWLY_CREATE);

		// Active account
		registerPage.ActiveEmail();

		// Step 1: Navigate to Login page
		homePage.clickTab(Constant.TAB_LOGIN);

		// Step 2: Login with account has just been created
		loginPage.Login(Constant.EMAIL_NEWLY_CREATE, Constant.PASSWORD);

		// Step 3: Click on Change password tab
		homePage.clickTab(Constant.TAB_CHANGE_PASSWORD);

		// Step 4: Enter valid value into all fields and click change pass button
		changePassword.ChangePassword(Constant.PASSWORD, Constant.NEW_PASSWORD, Constant.NEW_PASSWORD);

		// VP: Message "Your password has been updated" appears.
		Assert.assertEquals(Constant.MessageChangePasswordPage.MessageChangePasswordSuccess,
				changePassword.GetMessageChangePasswordSuccess());
		
		/*
		 * 
		 * Post condition: Login with new password
		 */
		// Log out first
		homePage.clickTab(Constant.TAB_LOGOUT);
		// Click Login Tab again.
		homePage.clickTab(Constant.TAB_LOGIN);
		loginPage.Login(Constant.EMAIL_NEWLY_CREATE, Constant.NEW_PASSWORD);
		// VP: Verify welcome message is correct
		Assert.assertEquals(loginPage.GetWelcomeText(), Constant.WelcomeText);

	}

}
