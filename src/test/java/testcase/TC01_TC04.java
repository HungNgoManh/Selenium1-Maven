package testcase;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Constant;
import pages.LoginPage;


public class TC01_TC04 extends TestBase {
	
	@BeforeMethod
	// Init Login page webdriver before starting
	public void Initdriver() {

		loginPage = new LoginPage(driver);
	}


	@Test(description = "Test Login success with correct welcome message", priority = 1)
	public void TC01() {
		/*
		 * Step 1: Init Login Page driver and start chrome were done in BeforeMethod. 
		 */
		// Step 2: Click Login Tab
		loginPage.clickLoginTab();
		
		// Step 3: Enter valid Email and Password and click Login button
		loginPage.Login(Constant.USERNAME, Constant.PASSWORD);
		
		// VP: Verify welcome message is correct
		Assert.assertEquals(Constant.WelcomeText, loginPage.GetWelcomeText());

	}
	
	@Test(description = "Test Contact Email contains correct href value", priority = 3)
	public void TC04() {
		/*
		 * Inherit Step 1 from TC01
		 * 
		 */
		// Step 2: Click Contact Tab
		
		loginPage.clickContactTab();
		// VP: Email address's href is "mailto:thanh.viet.le@logigear.com"
		AssertJUnit.assertEquals(Constant.EmailContact, loginPage.GetEmailContact());
	}

	

}