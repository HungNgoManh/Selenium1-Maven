package Common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import net.bytebuddy.utility.RandomString;
import pages.LoginPage;

public class Utilities {
	
	WebDriver driver;
	LoginPage loginPage;
	
	public static String EmailGenerator () {
		
		String BaseEmail = "hung.ngo.test+";
		String EmailHost = "@gmail.com";
		String RandomEmail = BaseEmail + RandomString.make(5) + EmailHost;
		return RandomEmail;
		
	}
	
	public static String RandomPassword () {
		
		String randomPassword = RandomString.make(8);
		return randomPassword;
		
	}
	
	public void OpenChrome () {
		
		System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://logigearrailway.somee.com/Page/HomePage.cshtml");
		driver.manage().window().maximize();
	}
	
	public void CloseChome () {
		
		driver.quit();
	}

}
