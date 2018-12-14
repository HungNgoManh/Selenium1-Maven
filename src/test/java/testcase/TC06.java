package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Constant;
import pages.HomePage;
import pages.LoginPage;

public class TC06 extends TestBase {

	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	// Init Login page webdriver before starting
	public void Initdriver() {

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@Test(description = "Check if Additional pages display once user logged in")

	public void TC_06() {
		/*
		 * Step 1: Init Login Page driver and start chrome were done in BeforeMethod.
		 */
		// Step 2: Click Login Tab
		homePage.clickTab(Constant.TAB_LOGIN);
		// Step 3: Login with valid username , password.
		loginPage.Login(Constant.USERNAME, Constant.PASSWORD);

		// VP: "My ticket", "Change password" and "Logout" tabs are displayed.
		Assert.assertTrue(homePage.getTab(Constant.TAB_LOGOUT).isDisplayed(), "Tab Log out is not displayed");
		Assert.assertTrue(homePage.getTab(Constant.TAB_CHANGE_PASSWORD).isDisplayed(),
				"Tab Change password is not displayed");
		Assert.assertTrue(homePage.getTab(Constant.TAB_MY_TICKET).isDisplayed(), "Tab My Ticket is not displayed");

		// Step 4: Click My Ticket tab.
		homePage.clickTab(Constant.TAB_MY_TICKET);

		// VP: User will be directed to My ticket page
		Assert.assertEquals(homePage.getPageHeader(Constant.TAB_MY_TICKET),
				Constant.MessageMyTicketPage.PageHeaderText);

		// Step 5: Click Change password tab
		homePage.clickTab(Constant.TAB_CHANGE_PASSWORD);

		// VP: Click "Change password" tab, user will be directed to Change password
		// page
		Assert.assertEquals(homePage.getPageHeader(Constant.TAB_CHANGE_PASSWORD),
				Constant.MessageChangePasswordPage.PageHeaderText);

	}

}
