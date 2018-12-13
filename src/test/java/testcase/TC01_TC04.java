package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Constant;
import pages.HomePage;
import pages.LoginPage;


public class TC01_TC04 extends TestBase {
	
	@BeforeMethod
	// Init Login page webdriver before starting
	public void Initdriver() {

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}


	@Test(description = "Test Login success with correct welcome message")
	public void TC01() {
		/*
		 * Step 1: Init Login Page driver and start chrome were done in BeforeMethod. 
		 */
		// Step 2: Click Login Tab
		homePage.clickTab(Constant.TAB_LOGIN);
		
		// Step 3: Enter valid Email and Password and click Login button
		loginPage.Login(Constant.USERNAME, Constant.PASSWORD);
		
		// VP: Verify welcome message is correct
		Assert.assertEquals(Constant.WelcomeMessageLogin, loginPage.GetWelcomeText());

	}
	
	@Test(description = "Test Contact Email contains correct href value")
	public void TC04() {
		/*
		 * Inherit Step 1 from TC01
		 * 
		 */
		
		// Step 2: Click Contact Tab
		homePage.clickTab(Constant.TAB_CONTACT);
		
		// VP: Email address's href is "mailto:thanh.viet.le@logigear.com"
		Assert.assertEquals(Constant.EmailContact, loginPage.GetEmailContact());
	}

	

}