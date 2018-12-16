package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Constant;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;

public class TC01_TC04 extends TestBase {

	@Test(description = "Test Login success with correct welcome message")
	public void TC01() {

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Go to Login page and login with valid username/password
		page.GetInstance(HomePage.class).goToLoginPage().loginToRailway(Constant.USERNAME, Constant.PASSWORD);

		// VP: Verify welcome message is correct
		Assert.assertEquals(page.GetInstance(LoginPage.class).GetWelcomeText(), Constant.WelcomeMessageLogin);

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

}