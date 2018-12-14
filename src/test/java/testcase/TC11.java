package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Constant;
import Common.Utilities;
import pages.HomePage;
import pages.RegisterPage;

public class TC11 extends TestBase {
	
	private RegisterPage registerPage;
	private HomePage homePage;


	@BeforeMethod
	// Init Register page webdriver before starting
	public void Initdriver() {

		homePage = new HomePage(driver);
		registerPage = new RegisterPage(driver);
	}

	@Test(description = "User can't create account while password and PID fields are empty")
	public void TC_11() {
		/*
		 * Step 1: Init Login Page driver and start chrome were done in BeforeMethod.
		 */
		// Step 2: Click Register Tab
		homePage.clickTab(Constant.TAB_REGISTER);

		// Step 3:Enter valid email address and leave other fields empty then click
		// Register button
		registerPage.RegisterNewUser(Utilities.EmailGenerator(), Constant.EMPTY, Constant.EMPTY, Constant.EMPTY);

		// VP 1: Message "There're errors in the form. Please correct the errors and try
		// again." appears above the form.
		Assert.assertEquals(registerPage.GetMessageError(), Constant.MessageRegisterPage.MessageFormError);

		// VP 2: Next to password fields, error message "Invalid password length."
		// displays
		Assert.assertEquals(registerPage.GetPasswordLabel(), Constant.MessageRegisterPage.PasswordValidationError);

		// VP 3: Next to password fields, error message "Invalid password length."
		// displays
		Assert.assertEquals(registerPage.GetPIDLabel(), Constant.MessageRegisterPage.PIDValidationError);

	}

}
