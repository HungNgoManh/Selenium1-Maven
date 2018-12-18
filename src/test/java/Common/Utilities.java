package Common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.bytebuddy.utility.RandomString;

public class Utilities {

	static WebDriver driver;
	static EmailUtils emailUtils;

	public static String EmailGenerator() {

		String BaseEmail = "hung.ngo.test+";
		String EmailHost = "@gmail.com";
		String RandomEmail = BaseEmail + RandomString.make(7) + EmailHost;
		Constant.EMAIL_NEWLY_CREATE = RandomEmail;
		return Constant.EMAIL_NEWLY_CREATE;

	}

	public static String RandomPassword() {

		String randomPassword = RandomString.make(8);
		return randomPassword;

	}
	
	public static void waitForDropDownDisplays(By webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
		} catch (NoSuchElementException e) {
		}
	}
	
	
	/*
	 * Connect to gmail smtp service to check the new message
	 * 
	 */
	public static void connectToEmail() {

		try {
			Thread.sleep(5000);
			emailUtils = new EmailUtils(Constant.USERNAME, Constant.PASSWORD, Constant.gmail_service,
					EmailUtils.EmailFolder.INBOX);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	// Get email content that contains the link for activating account

		public static String GetActivationEmail() {
			try {

				String EmailContent = emailUtils.getAuthorizationCode(Constant.EMAIL_NEWLY_CREATE);
				return EmailContent;

			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail(e.getMessage());
			}
			return null;
		}
		
		// Get email content that contains the link for Reset Token

				public static String GetResetTokenEmail() {
					try {

						String EmailContent = emailUtils.getResetToken();
						return EmailContent;

					} catch (Exception e) {
						e.printStackTrace();
						Assert.fail(e.getMessage());
					}
					return null;
				}

		/**
		 * Returns a list with all links contained in the input
		 */

		public static List<String> extractUrls(String text) {
			List<String> containedUrls = new ArrayList<String>();
			String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
			Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
			Matcher urlMatcher = pattern.matcher(text);

			while (urlMatcher.find()) {
				containedUrls.add(text.substring(urlMatcher.start(0), urlMatcher.end(0)));
			}

			return containedUrls;
		}

		
}
