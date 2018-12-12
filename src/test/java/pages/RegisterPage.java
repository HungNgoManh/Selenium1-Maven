package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Common.EmailUtils;

public class RegisterPage {

	static EmailUtils emailUtils;

	/**
	 * All WebElements are identified by @FindBy annotation
	 */
	WebDriver driver;

	// Register Tab
	@FindBy(xpath = "//span[normalize-space()='Register']")
	WebElement RegisterTab;
	// Register successful message
	@FindBy(xpath = "//h1[normalize-space(text())='Thank you for registering your account']")
	WebElement RegisterSuccessMessage;
	// Email input text box
	@FindBy(xpath = "//input[@id='email']")
	WebElement registerEmail;
	// Password input text box
	@FindBy(xpath = "//input[@id='password']")
	WebElement registerPassword;
	// Confirm password text box
	@FindBy(xpath = "//input[@id='confirmPassword']")
	WebElement registerConfirmPassword;
	// Input Register PID text box
	@FindBy(xpath = "//input[@id='pid']")
	WebElement registerPID;
	// Register button
	@FindBy(xpath = "//input[@title='Register']")
	WebElement registerButton;
	// Message Error
	@FindBy(xpath = "//p[@class='message error']")
	WebElement MessageError;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	// Click Register tab
	public void clickRegisterTab() {

		RegisterTab.click();
	}

	// Set user name in textbox
	public void setUserEmail(String strUserEmail) {
		registerEmail.sendKeys(strUserEmail);
	}

	// Set password in password textbox
	public void setPassword(String strPassword) {
		registerPassword.sendKeys(strPassword);
	}

	// Confirm password again
	public void setPasswordConfirm(String strPassword2) {

		registerConfirmPassword.sendKeys(strPassword2);
	}

	// Set PID
	public void setPID(String strPID) {

		registerPID.sendKeys(strPID);
	}

	// Get Register successful message

	public String GetRegisterSuccessMessage() {

		return RegisterSuccessMessage.getText();

	}

	public String GetMessageError() {

		return MessageError.getText();
	}

	// Fill all info and click Register button
	public void RegisterNewUser(String strUserEmail, String strPassword, String strPassword2, String PID) {

		this.setUserEmail(strUserEmail);
		this.setPassword(strPassword);
		this.setPasswordConfirm(strPassword2);
		this.setPID(PID);
		// scroll to the bottom page to be able to click Register button
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		registerButton.click();

	}

	public static void connectToEmail() {
		try {
			emailUtils = new EmailUtils("hung.ngo.test@gmail.com", "Matkhau~1", "smtp.gmail.com",
					EmailUtils.EmailFolder.INBOX);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	// Get email content that contains the link for activating account

	public String GetEmailContent() {
		try {
			// TODO: Execute actions to send verification code to email

			String EmailContent = emailUtils.getAuthorizationCode();
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

	public List<String> extractUrls(String text) {
		List<String> containedUrls = new ArrayList<String>();
		String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
		Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
		Matcher urlMatcher = pattern.matcher(text);

		while (urlMatcher.find()) {
			containedUrls.add(text.substring(urlMatcher.start(0), urlMatcher.end(0)));
		}

		return containedUrls;
	}

	public void ActiveEmail() {

		connectToEmail();
		String EmailContent = GetEmailContent();
		List<String> ULRLink = extractUrls(EmailContent);
		driver.get(ULRLink.get(0));

	}

}
