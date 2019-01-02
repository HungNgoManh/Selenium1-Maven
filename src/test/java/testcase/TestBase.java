package testcase;

import java.util.Base64;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Common.Constant;
import pages.PageGenerator;

public class TestBase {
	public WebDriver driver;
	public WebDriverWait wait;
	public PageGenerator page;

	@BeforeMethod
	public void setup() {
		// Create a Chrome driver. All test classes use this.
		System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Decode password
		byte[] decodedBytes = Base64.getDecoder().decode(Constant.PASSWORD2);
		String pass = new String(decodedBytes);
		Constant.PASSWORD = pass;

		// Create a wait. All test classes use this.
		wait = new WebDriverWait(driver, 15);

		// Maximize Window
		driver.manage().window().maximize();

		// Instantiate the Page Class
		page = new PageGenerator(driver);
	}


	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
