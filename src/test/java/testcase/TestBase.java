package testcase;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Common.Constant;
import Common.EmailUtils;
import pages.LoginPage;
import pages.RegisterPage;

public class TestBase {

	WebDriver driver;
	LoginPage loginPage;
	RegisterPage registerPage;
	EmailUtils emailUtils;
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constant.URLTest);
		driver.manage().window().maximize();
	}
	

//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
	
	public void connectToEmail() {
		try {
			emailUtils = new EmailUtils("hung.ngo.test@gmail.com", "Matkhau~1", "smtp.gmail.com",
					EmailUtils.EmailFolder.INBOX);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	public String testVerificationCode() {
		try {
			// TODO: Execute actions to send verification code to email

			String verificationCode = emailUtils.getAuthorizationCode();

			// TODO: Enter verification code on screen and submit
			// String CurrentString = "Fruit = they taste good";
			String[] separated = verificationCode.split(". ");

			String finalcode = separated[0].trim();
			// this will contain "Fruit"
			// this will contain "they teste good"
			return finalcode;

			// TODO: add assertions

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		return null;
	}

	
	
}
