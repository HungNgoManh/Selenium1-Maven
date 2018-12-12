package Common;

import org.openqa.selenium.WebDriver;
import net.bytebuddy.utility.RandomString;

public class Utilities {

	WebDriver driver;

	public static String EmailGenerator() {

		String BaseEmail = "hung.ngo.test+";
		String EmailHost = "@gmail.com";
		String RandomEmail = BaseEmail + RandomString.make(8) + EmailHost;
		Constant.EMAIL_NEWLY_CREATE = RandomEmail;
		return Constant.EMAIL_NEWLY_CREATE;

	}

	public static String RandomPassword() {

		String randomPassword = RandomString.make(8);
		return randomPassword;

	}

}
