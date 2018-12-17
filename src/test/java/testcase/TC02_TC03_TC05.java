package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Constant;
import net.bytebuddy.utility.RandomString;
import pages.HomePage;
import pages.LoginPage;

public class TC02_TC03_TC05 extends TestBase {

	@Test(description = "Test Login with blank user name")
	public void TC02() {

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Go to Login page and login with empty username and valid password
		page.GetInstance(HomePage.class).goToLoginPage().loginToRailway(Constant.EMPTY, Constant.PASSWORD);

		// VP: User can't login and message "There was a problem with your login and/or
		// errors exist in your form. " appears.
		Assert.assertEquals(page.GetInstance(LoginPage.class).GetLoginErrorMessage(),
				Constant.MessageLoginPage.MessageLoginError);

	}

	@Test(description = "Test Login with invalid password")
	public void TC03() {

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Go to Login page and login with empty username and valid password
		page.GetInstance(HomePage.class).goToLoginPage().loginToRailway(Constant.USERNAME, RandomString.make(8));

		// VP: User can't login and message "There was a problem with your login and/or
		// errors exist in your form. " appears.
		Assert.assertEquals(page.GetInstance(LoginPage.class).GetLoginErrorMessage(),
				Constant.MessageLoginPage.MessageLoginError);

	}

	@Test(description = "Check Login attempts error - more than 3 times")

	public void TC05() {
		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Go to Login page and login with valid username and valid password for
		// 4 time
		page.GetInstance(HomePage.class).goToLoginPage().LoginMultiTime(Constant.USERNAME, RandomString.make(8), 4);
		// VP: User can't login and message "You have used 4 out of 5 login attempts.
		// After all 5 have been used, you will be unable to login for 15 minutes."
		// appears.
		Assert.assertEquals(page.GetInstance(LoginPage.class).GetLoginErrorMessage(),
				Constant.MessageLoginPage.MessageLoginError);

	}

}
