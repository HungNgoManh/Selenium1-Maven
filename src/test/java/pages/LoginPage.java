package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Common.Constant;
import Common.Utilities;

public class LoginPage extends BasePage {

	// *********Constructor*********
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * All WebElements are identified by @FindBy annotation
	 */

	@FindBy(id = "username")
	WebElement UID;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "newPassword")
	WebElement newPassword;

	@FindBy(id = "confirmPassword")
	WebElement confirmPassword;

	@FindBy(xpath = "//input[@id='resetToken']")
	WebElement resetToken;

	@FindBy(xpath = "//input[@title='Reset password']")
	WebElement btnResetPassword;

	@FindBy(xpath = "//div[@class='account']//strong")
	WebElement WelcomeText;

	@FindBy(xpath = "//input[@title='Login']")
	WebElement btnLogin;
	
	@FindBy(xpath = "//body/child::div/div[@id='content']/p[starts-with(@class,'message')]")
	WebElement MessageLoginForm;

	@FindBy(xpath = "//label[@class='validation-error' and @for='resetToken']")
	WebElement resetTokenlabel;
	
	@FindBy(xpath="//label[@class='validation-error' and @for ='confirmPassword']")
	WebElement lblConfirmPassword;

	@FindBy(xpath = "//a[@href='/Account/ForgotPassword.cshtml']")
	WebElement ForgotPasswordLink;

	@FindBy(xpath = "//label[@for='email']/following::input[@id='email']")
	WebElement EmailResetPasswordInput;

	@FindBy(xpath = "//input[@value='Send Instructions']")
	WebElement btnSendInstruction;

	// *********Page Methods*********
	public void loginToRailway(String strUserName, String strPasword) {
		// Enter Username(Email)
		writeText(UID, strUserName);
		// Enter Password
		writeText(password, strPasword);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", btnLogin);
		// Click Login Button
		click(btnLogin);
		// Update Welcome message for logged user
		Constant.WelcomeMessageLogin = Constant.WELCOME + strUserName;
	}

	// Get Tab to check if it displayed or not
	public WebElement getTab(String tabName) {
		return driver.findElement(By.xpath(String.format("//span[normalize-space()='%s']", tabName)));
	}

	// Get the form to check if it displayed or not
	public WebElement PasswordChangeForm() {

		return driver.findElement(By.xpath("//legend[contains(text(),'Password Change Form')]"));
	}


	// Set user name in textbox
	public void setUserName(String strUserName) {
		UID.sendKeys(strUserName);
	}

	// Set password in password textbox
	public void setPassword(String strPassword) {
		password.sendKeys(strPassword);
	}

	// Click on login button
	public void clickLoginButton() {
		btnLogin.click();
	}

	// Click Forgot password link
	public void clickForgotPasswordLink() {

		ForgotPasswordLink.click();
	}

	// Set new password in password textbox
	public void setnewPassword(String strPassword) {
		newPassword.sendKeys(strPassword);
	}

	// Set confirm password in confirm password textbox
	public void setconfirmPassword(String strPassword) {
		confirmPassword.sendKeys(strPassword);
	}

	// Set Email reset password
	public void setEmailResetPassword(String emailreset) {

		EmailResetPasswordInput.sendKeys(emailreset);
	}

	// Click button Send Instruction
	public void clickbtnSendInstruction() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", btnSendInstruction);
		btnSendInstruction.click();
	}

	// Get reset token Label text
	public String getResetTokenLabel() {

		return resetTokenlabel.getText();
	}
	
	//Get Confirm password label
	
	public String getConfirmPasswordLabel() {
		
		return lblConfirmPassword.getText();
	}

	// Click reset password button
	public void clickbtnResetPassword() {

		btnResetPassword.click();
	}

	
	// Get Welcome user message
	public String GetWelcomeText() {

		return WelcomeText.getText();
	}

	public String GetMessageInform() {

		return MessageLoginForm.getText();

	}

	public void ClearTextBox() {

		UID.clear();
		password.clear();
	}

	// Login multi time with give username, password
	public void LoginMultiTime(String strUserName, String strPasword, int time) {

		for (int i = 1; i <= time; i++) {
			ClearTextBox();
			loginToRailway(strUserName, strPasword);

		}
	}

	// Open reset password link
	public void OpenResetPasswordLink() {

		// Connect to mail
		Utilities.connectToEmail();
		// Get mail content for the reset token
		String EmailContent = Utilities.GetResetTokenEmail();
		// Get URL link
		List<String> Link = Utilities.extractUrls(EmailContent);
		// Navigate to the link
		driver.navigate().to(Link.get(0));

	}
	// Reset password function
	public void resetPassword(String newpass, String confirmpass, String resettoken) {

		this.newPassword.clear();
		this.confirmPassword.clear();
		this.newPassword.sendKeys(newpass);
		this.confirmPassword.sendKeys(confirmpass);
		this.resetToken.sendKeys((Keys.chord(Keys.CONTROL, "a")), resettoken);
		// Scroll to Reset password button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", btnResetPassword);
		
		this.btnResetPassword.click();

	}

}
