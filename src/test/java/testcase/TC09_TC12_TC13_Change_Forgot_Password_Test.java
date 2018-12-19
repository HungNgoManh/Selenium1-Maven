package testcase;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Constant;
import Common.Utilities;
import net.bytebuddy.utility.RandomString;
import pages.ChangePasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class TC09_TC12_TC13_Change_Forgot_Password_Test extends TestBase {

	@Test(description = "User can change password !")
	public void TC_09() {

		/*
		 * Precondition: Create and activate a new account
		 */

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Create new account
		page.GetInstance(HomePage.class).goToRegisterPage().RegisterNewUser(Utilities.EmailGenerator(),
				Constant.PASSWORD, Constant.PASSWORD, Constant.PID);

		// Step 3: Active account
		page.GetInstance(RegisterPage.class).ActiveEmail();

		/*
		 * Testcase steps:
		 * 
		 */
		// Step 1: Navigate to Login page
		page.GetInstance(HomePage.class).goToLoginPage();

		// Step 2: Login with account has just been created
		page.GetInstance(LoginPage.class).loginToRailway(Constant.EMAIL_NEWLY_CREATE, Constant.PASSWORD);

		// Step 3: Go to Change password page
		page.GetInstance(HomePage.class).goToChangePasswordPage();

		// Step 4: Enter valid value into all fields and click change pass button
		page.GetInstance(ChangePasswordPage.class).ChangePassword(Constant.PASSWORD, Constant.NEW_PASSWORD,
				Constant.NEW_PASSWORD);

		// VP: Message "Your password has been updated" appears.
		Assert.assertEquals(page.GetInstance(ChangePasswordPage.class).GetMessageChangePassword(),
				Constant.MessageChangePasswordPage.MessageChangePasswordSuccess);

		/*
		 * Post condition: Login with new password
		 */
		// Log out first
		page.GetInstance(HomePage.class).clickTab(Constant.TAB_LOGOUT);
		// Login with new account and new password
		page.GetInstance(HomePage.class).goToLoginPage().loginToRailway(Constant.EMAIL_NEWLY_CREATE,
				Constant.NEW_PASSWORD);
		// VP: Verify welcome message is correct
		Assert.assertEquals(page.GetInstance(LoginPage.class).GetWelcomeText(), Constant.WelcomeMessageLogin);

	}

	@Test(description = "Errors display when password reset token is blank")
	public void TC_12() {

		/*
		 * Precondition: Create and activate a new account
		 */

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Create new account
		page.GetInstance(HomePage.class).goToRegisterPage().RegisterNewUser(Utilities.EmailGenerator(),
				Constant.PASSWORD, Constant.PASSWORD, Constant.PID);

		// Step 3: Active account
		page.GetInstance(RegisterPage.class).ActiveEmail();

		/*
		 * Testcase steps:
		 * 
		 */
		// Step 1: Navigate to QA Railway Login page
		page.GetInstance(HomePage.class).goToLoginPage();

		// Step 2: Click Forgot Password Link
		page.GetInstance(LoginPage.class).clickForgotPasswordLink();

		// Step 3: Enter the email address of the created account in Pre-condition
		page.GetInstance(LoginPage.class).setEmailResetPassword(Constant.EMAIL_NEWLY_CREATE);

		// Step 4: Click on "Send Instructions" button
		page.GetInstance(LoginPage.class).clickbtnSendInstruction();

		// Step 5: Open mailbox and click on reset password link
		page.GetInstance(LoginPage.class).OpenResetPasswordLink();

		// VP: "Password Change Form" page displays
		Assert.assertTrue(page.GetInstance(LoginPage.class).PasswordChangeForm().isDisplayed());

		// Step 6: Enter new passwords and remove the Password Reset Token
		page.GetInstance(LoginPage.class).resetPassword(Constant.NEW_PASSWORD, Constant.NEW_PASSWORD, Constant.EMPTY);

		// VP: Error message "The password reset token is incorrect or may be expired.
		// Visit the forgot password page to generate a new one." displays above the
		// form.
		Assert.assertEquals(page.GetInstance(LoginPage.class).GetMessageInform(),
				Constant.MessageLoginPage.MessageResetTokenIncorrect);

		// VP: Error message "The password reset token is invalid." displays next to the
		// "Password Reset Token" field.
		Assert.assertEquals(page.GetInstance(LoginPage.class).getResetTokenLabel(),
				Constant.MessageLoginPage.MessagePasswordTokenInvalid);

	}

	@Test(description = "Errors display if password and confirm password don't match when resetting password")
	public void TC13() {

		/*
		 * Precondition: Create and activate a new account
		 */

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Create new account
		page.GetInstance(HomePage.class).goToRegisterPage().RegisterNewUser(Utilities.EmailGenerator(),
				Constant.PASSWORD, Constant.PASSWORD, Constant.PID);

		// Step 3: Active account
		page.GetInstance(RegisterPage.class).ActiveEmail();

		/*
		 * Testcase steps:
		 * 
		 */
		// Step 1: Navigate to QA Railway Login page
		page.GetInstance(HomePage.class).goToLoginPage();

		// Step 2: Click Forgot Password Link
		page.GetInstance(LoginPage.class).clickForgotPasswordLink();

		// Step 3: Enter the email address of the created account in Pre-condition
		page.GetInstance(LoginPage.class).setEmailResetPassword(Constant.EMAIL_NEWLY_CREATE);

		// Step 4: Click on "Send Instructions" button
		page.GetInstance(LoginPage.class).clickbtnSendInstruction();

		// Step 5: Open mailbox and click on reset password link
		page.GetInstance(LoginPage.class).OpenResetPasswordLink();

		// VP: "Password Change Form" page displays
		Assert.assertTrue(page.GetInstance(LoginPage.class).PasswordChangeForm().isDisplayed());

		// Step 6: Enter different values for password fields
		page.GetInstance(LoginPage.class).resetPassword(Constant.NEW_PASSWORD,Utilities.RandomPassword(),
				Keys.chord(Keys.NULL));

		// VP: Error message "Could not reset password. Please correct the errors and
		// try again." displays above the form.
		Assert.assertEquals(page.GetInstance(LoginPage.class).GetMessageInform(),
				Constant.MessageChangePasswordPage.MessageCouldNotResetPassword);

		// VP: Error message "The password confirmation did not match the new password."
		// displays next to the confirm password field.
		Assert.assertEquals(page.GetInstance(LoginPage.class).getConfirmPasswordLabel(),
				Constant.MessageChangePasswordPage.MessagePasswordDidNotMatch);

	}
}
