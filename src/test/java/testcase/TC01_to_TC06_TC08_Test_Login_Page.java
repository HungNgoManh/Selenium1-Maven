package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Constant;
import Common.Utilities;
import net.bytebuddy.utility.RandomString;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;

public class TC01_to_TC06_TC08_Test_Login_Page extends TestBase {

	@Test(description = "Test Login success with correct welcome message")
	public void TC01() {

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Go to Login page and login with valid username/password
		page.GetInstance(HomePage.class).goToLoginPage().loginToRailway(Constant.USERNAME, Constant.PASSWORD);

		// VP: Verify welcome message is correct
		Assert.assertEquals(page.GetInstance(LoginPage.class).GetWelcomeText(), Constant.WelcomeMessageLogin);

	}

	@Test(description = "Test Login with blank user name")
	public void TC02() {

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Go to Login page and login with empty username and valid password
		page.GetInstance(HomePage.class).goToLoginPage().loginToRailway(Constant.EMPTY, Constant.PASSWORD);

		// VP: User can't login and message "There was a problem with your login and/or
		// errors exist in your form. " appears.
		Assert.assertEquals(page.GetInstance(LoginPage.class).GetMessageInform(),
				Constant.MessageLoginPage.MessageLoginError);

	}

	@Test(description = "Test Login with invalid password")
	public void TC03() {

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Go to Login page and login with empty username and valid password
		page.GetInstance(HomePage.class).goToLoginPage().loginToRailway(Constant.USERNAME, Utilities.RandomPassword());

		// VP: User can't login and message "There was a problem with your login and/or
		// errors exist in your form. " appears.
		Assert.assertEquals(page.GetInstance(LoginPage.class).GetMessageInform(),
				Constant.MessageLoginPage.MessageLoginError);

	}

	@Test(description = "Test Contact Email contains correct href value")
	public void TC04() {
		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Go to Contact page
		page.GetInstance(HomePage.class).goToContactPage();

		// VP: Email address's href is "mailto:thanh.viet.le@logigear.com"
		Assert.assertEquals(Constant.EmailContact, page.GetInstance(ContactPage.class).getEmailContact());
	}

	@Test(description = "Check Login attempts error - more than 3 times")

	public void TC05() {
		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Go to Login page and login with valid username and valid password for
		// 4 time
		page.GetInstance(HomePage.class).goToLoginPage().LoginMultiTime(Constant.USERNAME, Utilities.RandomPassword(),
				4);
		// VP: User can't login and message "You have used 4 out of 5 login attempts.
		// After all 5 have been used, you will be unable to login for 15 minutes."
		// appears.
		Assert.assertEquals(page.GetInstance(LoginPage.class).GetMessageInform(),
				Constant.MessageLoginPage.MessageLoginError);

	}

	@Test(description = "Check if Additional pages display once user logged in")
	public void TC_06() {

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Go to Login page and login with valid username/password
		page.GetInstance(HomePage.class).goToLoginPage().loginToRailway(Constant.USERNAME, Constant.PASSWORD);

		// VP: "My ticket", "Change password" and "Logout" tabs are displayed.
		Assert.assertTrue(page.GetInstance(HomePage.class).getTab(Constant.TAB_LOGOUT).isDisplayed(),
				"Tab Log out is not displayed");
		Assert.assertTrue(page.GetInstance(HomePage.class).getTab(Constant.TAB_CHANGE_PASSWORD).isDisplayed(),
				"Tab Change password is not displayed");
		Assert.assertTrue(page.GetInstance(HomePage.class).getTab(Constant.TAB_MY_TICKET).isDisplayed(),
				"Tab My Ticket is not displayed");

		// Step 3: Click My Ticket tab.
		page.GetInstance(HomePage.class).clickTab(Constant.TAB_MY_TICKET);

		// VP: User will be directed to My ticket page
		Assert.assertEquals(page.GetInstance(HomePage.class).getPageHeader(Constant.TAB_MY_TICKET),
				Constant.MessageMyTicketPage.PageHeaderText);

		// Step 4: Click Change password tab
		page.GetInstance(HomePage.class).clickTab(Constant.TAB_CHANGE_PASSWORD);

		// VP: Click "Change password" tab, user will be directed to Change password
		// page
		Assert.assertEquals(page.GetInstance(HomePage.class).getPageHeader(Constant.TAB_CHANGE_PASSWORD),
				Constant.MessageChangePasswordPage.PageHeaderText);

	}

	@Test(description = "User can't login with an account hasn't been activated")
	public void TC_08() {

		/*
		 * Precondition: Create and activate a new account
		 */
		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Create new account
		page.GetInstance(HomePage.class).goToRegisterPage().RegisterNewUser(Utilities.EmailGenerator(),
				Constant.PASSWORD, Constant.PASSWORD, Constant.PID);

		/*
		 * Testcase steps:
		 * 
		 */
		// Step 1: Go to Login Page
		page.GetInstance(HomePage.class).goToLoginPage();

		// Step 3: Login with username and password of account hasn't been activated
		page.GetInstance(LoginPage.class).loginToRailway(Constant.EMAIL_NEWLY_CREATE, Constant.PASSWORD);

		// VP: User can't login and message "Invalid username or password. Please try
		// again." appears.
		Assert.assertEquals(page.GetInstance(LoginPage.class).GetMessageInform(),
				Constant.MessageLoginPage.MessageInvalidUsernamePassword);

	}

}