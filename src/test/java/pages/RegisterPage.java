package pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Common.Constant;
import Common.EmailUtils;
import Common.Utilities;

public class RegisterPage extends BasePage {

	static EmailUtils emailUtils;
	
	// *********Constructor*********
		public RegisterPage (WebDriver driver) {
			super(driver);
		}

	/**
	 * All WebElements are identified by @FindBy annotation
	 */
	// Register successful message
	@FindBy(xpath = "//h1[normalize-space(text())='Thank you for registering your account']")
	WebElement RegisterSuccessMessage;
	// Email input text box
	@FindBy(id = "email")
	WebElement registerEmail;
	// Password input text box
	@FindBy(id = "password")
	WebElement registerPassword;
	// Confirm password text box
	@FindBy(id = "confirmPassword")
	WebElement registerConfirmPassword;
	// Input Register PID text box
	@FindBy(id = "pid")
	WebElement registerPID;
	// Register button
	@FindBy(xpath = "//input[@title='Register']")
	WebElement registerButton;
	// Message after register an account
	@FindBy(xpath = "//p[starts-with(@class,'message')]")
	WebElement RegisterMessages;
	// Password Label
	@FindBy(xpath = "//label[@class='validation-error' and @for='password']")
	WebElement passwordlabel;
	// PID Label
	@FindBy(xpath = "//label[@class='validation-error' and @for='pid']")
	WebElement PIDlabel;

	
	// Get password Label text
	public String GetPasswordLabel() {

		return passwordlabel.getText();
	}

	// Get PID Label text
	public String GetPIDLabel() {

		return PIDlabel.getText();
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

	public String GetMessageAfterRegisterAccount() {

		return RegisterMessages.getText();
	}

	// Fill all info and click Register button
	public void RegisterNewUser(String strUserEmail, String strPassword, String strPassword2, String PID) {

		this.setUserEmail(strUserEmail);
		this.setPassword(strPassword);
		this.setPasswordConfirm(strPassword2);
		this.setPID(PID);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", registerButton);
		registerButton.click();

	}

	
	/*
	 * Connect to email to get the message contains URL for activation, then
	 * navigate web driver to the link
	 * 
	 */
	public void ActiveEmail() {

		Utilities.connectToEmail();
		// Get Email contents, this contains the link for activation
		String EmailContent = Utilities.GetActivationEmail();
		// Get URL link
		List<String> Link = Utilities.extractUrls(EmailContent);
		// Navigate to the link
		driver.navigate().to(Link.get(0));
		// Print out info: New account has just been activated
		System.out.println(" Your new account: " + Constant.EMAIL_NEWLY_CREATE + " has just been activated!");

	}

}
