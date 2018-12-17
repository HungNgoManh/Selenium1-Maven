package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Constant;
import Common.Utilities;
import net.bytebuddy.utility.RandomString;
import pages.HomePage;
import pages.RegisterPage;

public class TC07_TC10_TC11_Register_Account_Test extends TestBase {

	@Test(description = "Check if user can create new account")
	public void TC_07() {

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Click Register Tab
		page.GetInstance(HomePage.class).goToRegisterPage();

		// Step 3: Enter valid information into all fields (Email, Pass, Confirm pass,
		// PID) and click Register button
		page.GetInstance(RegisterPage.class).RegisterNewUser(Utilities.EmailGenerator(), Constant.PASSWORD,
				Constant.PASSWORD, Constant.PID);

		// VP: New account is created and message "Thank you for registering your
		// account" appears.
		Assert.assertEquals(page.GetInstance(RegisterPage.class).GetRegisterSuccessMessage(),
				Constant.MessageRegisterSuccess);

		/*
		 * Post condition: 1: Active account 2: Re login using newly created account 3:
		 * Verify Welcome message displays correct email
		 */
		page.GetInstance(RegisterPage.class).ActiveEmail();
		page.GetInstance(HomePage.class).goToLoginPage().loginToRailway(Constant.EMAIL_NEWLY_CREATE, Constant.PASSWORD);
	}

	@Test(description = "User can't create account with \"Confirm password\" is not the same with \"Password\"")
	public void TC_10() {

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Click Register Tab
		page.GetInstance(HomePage.class).goToRegisterPage();

		// Step 3: Enter valid information into all fields except "Confirm password" is
		// not the same with "Password"
		page.GetInstance(RegisterPage.class).RegisterNewUser(Utilities.EmailGenerator(), Constant.PASSWORD,
				RandomString.make(8), Constant.PID);
		// VP: Message "There're errors in the form. Please correct the errors and try
		// again." appears.
		Assert.assertEquals(page.GetInstance(RegisterPage.class).GetMessageAfterRegisterAccount(),
				Constant.MessageRegisterPage.MessageFormError);

	}

	@Test(description = "User can't create account while password and PID fields are empty")
	public void TC_11() {

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Click Register Tab
		page.GetInstance(HomePage.class).goToRegisterPage();

		// Step 3: Enter valid information into all fields except "Confirm password" is
		// not the same with "Password"
		page.GetInstance(RegisterPage.class).RegisterNewUser(Utilities.EmailGenerator(), Constant.EMPTY, Constant.EMPTY,
				Constant.EMPTY);

		// VP: Message "There're errors in the form. Please correct the errors and try
		// again." appears.
		Assert.assertEquals(page.GetInstance(RegisterPage.class).GetMessageAfterRegisterAccount(),
				Constant.MessageRegisterPage.MessageFormError);

	}
}
