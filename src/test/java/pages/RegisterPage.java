package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

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
	//Message Error
	@FindBy (xpath="//p[@class='message error']")
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
	
	public String GetMessageError () {
		
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

}
