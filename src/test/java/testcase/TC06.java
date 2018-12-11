package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Constant;
import pages.LoginPage;

public class TC06 extends TestBase {

	@BeforeMethod
	// Init Login page webdriver before starting
	public void Initdriver() {

		loginPage = new LoginPage(driver);
	}

	@Test(description = "Check if Additional pages display once user logged in")

	public void TC_06() {
		/*
		 * Step 1: Init Login Page driver and start chrome were done in BeforeMethod. 
		 */
		//Step 2: 
		loginPage.clickLoginTab();
		loginPage.Login(Constant.USERNAME, Constant.PASSWORD);
		Assert.assertTrue(loginPage.getTab("Log out").isDisplayed(), "Tab Log out is not displayed");
		Assert.assertTrue(loginPage.getTab("Change password").isDisplayed(), "Tab Change password is not displayed");
		Assert.assertTrue(loginPage.getTab("My ticket").isDisplayed(), "Tab My Ticket is not displayed");

	}

}
