package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Constant;
import Common.Utilities;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class TC12 extends TestBase {

	@BeforeMethod
	// Init Register page webdriver before starting
	public void Initdriver() {

		homePage = new HomePage(driver);
		registerPage = new RegisterPage(driver);
		loginPage = new LoginPage(driver);

	}

	@Test(description = "Errors display when password reset token is blank")
	public void TC_12() {

		/*
		 * Precondition: Create and activate a new account
		 */
		// Navigate to Register tab
		homePage.clickTab(Constant.TAB_REGISTER);

		// Create new account
		registerPage.RegisterNewUser(Utilities.EmailGenerator(), Constant.PASSWORD, Constant.PASSWORD, Constant.PID);

		// Print out new Email created.
		System.out.println("Account created: " + Constant.EMAIL_NEWLY_CREATE);

		// Active account
		registerPage.ActiveEmail();

		// Step 1: Navigate to QA Railway Login page
		homePage.clickTab(Constant.TAB_LOGIN);

		// Step 2: Click Forgot Password Link
		loginPage.clickForgotPasswordLink();

		// Step 3: Enter the email address of the created account in Pre-condition
		loginPage.setEmailResetPassword(Constant.EMAIL_NEWLY_CREATE);

		// Step 4: Click on "Send Instructions" button
		loginPage.clickbtnSendInstruction();

		// Step 5: Open mailbox and click on reset password link
		loginPage.OpenResetPasswordLink();

		// VP: "Password Change Form" page displays
		Assert.assertTrue(loginPage.PasswordChangeForm().isDisplayed());

		// Step 6: Enter new passwords and remove the Password Reset Token
		loginPage.resetPassword(Constant.NEW_PASSWORD, Constant.NEW_PASSWORD, Constant.EMAIL_NEWLY_CREATE);

		// VP: Error message "The password reset token is incorrect or may be expired.
		// Visit the forgot password page to generate a new one." displays above the
		// form.
		Assert.assertEquals(loginPage.GetLoginErrorMessage(), Constant.MessageLoginPage.MessageResetTokenIncorrect);
		
		//VP: Error message "The password reset token is invalid." displays next to the "Password Reset Token" field.
		Assert.assertEquals(loginPage.getResetTokenLabel(), Constant.MessageLoginPage.MessagePasswordTokenInvalid);

	}
}
