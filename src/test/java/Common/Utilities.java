package Common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
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
	
	// Generate random date
		
	public static String bookRandomDate() {
		LocalDate baseDate = LocalDate.now().plusDays(3);
		Integer maxRandomValue = 27;
		Integer randomDays = (int) (maxRandomValue * Math.random());
		LocalDate randomDate = baseDate.plusDays(randomDays);
		return DateTimeFormatter.ofPattern("M/d/yyyy").format(randomDate);
	}
	
		
	/*
	 * Connect to gmail smtp service to check the new message
	 * 
	 */
	public static void connectToEmail() {
		
		byte[] decodedBytes = Base64.getDecoder().decode(Constant.PASSWORD2);
		String pass = new String(decodedBytes);

		try {
			Thread.sleep(4000);
			emailUtils = new EmailUtils(Constant.USERNAME, pass , Constant.gmail_service,
					EmailUtils.EmailFolder.INBOX);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	// Get email content that contains the link for activating account

		public static String GetActivationEmail() {
			try {
				Thread.sleep(1000);
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
