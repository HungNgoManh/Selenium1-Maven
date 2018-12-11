package testcase;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Common.Constant;
import Common.Utilities;
import net.bytebuddy.utility.RandomString;
import pages.RegisterPage;

public class TC10 extends TestBase {

	@BeforeMethod
	// Init Register page webdriver before starting
	public void Initdriver() {

		registerPage = new RegisterPage(driver);
	}

	@Test(description = "User can't create account with \"Confirm password\" is not the same with \"Password\"")
	public void TC_10() {
		/*
		 * Step 1: Init Login Page driver and start chrome were done in BeforeMethod.
		 */
		// Step 2: Click Register Tab
		registerPage.clickRegisterTab();
		// Step 3: Enter valid information into all fields except "Confirm password" is
		// not the same with "Password"
		registerPage.RegisterNewUser(Utilities.EmailGenerator(), Constant.PASSWORD, RandomString.make(8), Constant.PID);
		// VP: Message "There're errors in the form. Please correct the errors and try
		// again." appears.
		AssertJUnit.assertEquals(Constant.MessageRegisterPage.MessageFormError, registerPage.GetMessageError());

	}
}
