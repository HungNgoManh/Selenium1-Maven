package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Constant;
import Common.Utilities;
import pages.HomePage;
import pages.LoginPage;

public class TC02_TC03_TC05 extends TestBase {

	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	// Init Login page webdriver before starting
	public void Initdriver() {

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@Test(description = "Test Login with blank user name")
	public void TC02() {
		/*
		 * Step 1: Init Login Page driver and start chrome were done in BeforeMethod.
		 */

		// Step 2: Click Login Tab
		homePage.clickTab(Constant.TAB_LOGIN);

		// Step 3: Login with blank user name and correct password and click Login
		loginPage.Login(Constant.EMPTY, Constant.PASSWORD);

		// VP: User can't login and message "There was a problem with your login and/or
		// errors exist in your form. " appears.
		Assert.assertEquals(loginPage.GetLoginErrorMessage(),Constant.MessageLoginPage.MessageLoginError);

	}

	@Test(description = "Test Login with invalid password")
	public void TC03() {
		/*
		 * Inherit Step 1 ,2 from TC2, clear to text box and perform another login
		 */
		loginPage.ClearTextBox();

		// Step 3: Enter valid Email and invalid Password
		loginPage.Login(Constant.USERNAME, Utilities.RandomPassword());

		// VP: Error message "There was a problem with your login and/or errors exist in
		// your form." is displayed
		Assert.assertEquals(loginPage.GetLoginErrorMessage(), Constant.MessageLoginPage.MessageLoginError);

	}

	@Test(description = "Check Login attempts error - more than 3 times")

	public void TC05() {
		/*
		 * Inherit Step 1 ,2 from TC2, clear to text box and perform another login
		 */
	
		// Step 3: Enter valid information into "Username" textbox except "Password"
		// textbox for 4 times
		loginPage.LoginMultiTime(Constant.USERNAME, Utilities.RandomPassword(), 4);
		
		// VP: User can't login and message "You have used 4 out of 5 login attempts.
		// After all 5 have been used, you will be unable to login for 15 minutes."
		// appears.
		Assert.assertEquals(loginPage.GetLoginErrorMessage(), Constant.MessageLoginPage.MessageLoginAttemptError);

	}

}
