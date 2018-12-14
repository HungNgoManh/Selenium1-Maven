package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Common.Constant;
import Common.Utilities;

public class LoginPage {
	/**
	 * All WebElements are identified by @FindBy annotation
	 */
	WebDriver driver;

	@FindBy(id = "username")
	WebElement UID;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "newPassword")
	WebElement newPassword;

	@FindBy(id = "confirmPassword")
	WebElement confirmPassword;
	
	@FindBy(xpath="//input[@id='resetToken']")
	WebElement resetToken;
	
	@FindBy (xpath="//input[@title='Reset password']")
	WebElement btnResetPassword;

	@FindBy(xpath = "//div[@class='account']//strong")
	WebElement WelcomeText;

	@FindBy(xpath = "//input[@title='Login']")
	WebElement btnLogin;
	// For test xpath training ^^
	@FindBy(xpath = "//body/div//div[@id='content']/following-sibling::div//p")
	WebElement PageFooter;

	@FindBy(xpath = "//body/child::div/div[@id='content']/p[starts-with(@class,'message')]")
	WebElement LoginPageErrorMessage;
	
	@FindBy(xpath = "//label[@class='validation-error' and @for='resetToken']")
	WebElement resetTokenlabel;
	
	// For test xpath training ^^
	@FindBy(xpath = "//body/child::div/div[@id='content']/p[normalize-space(@text())='']")
	WebElement LoginErrorMessage2;
	// For test xpath training ^^
	@FindBy(xpath = "//ancestor::select[@name='Date']")
	WebElement drbDepartDate;
	// For test xpath training ^^
	@FindBy(xpath = "//div[@id='page']/div//li/parent::ul/preceding::div/following::div/h1[text()='Book ticket']")
	WebElement BookTicketPageTitle;

	@FindBy(xpath = "//a[contains(@href,'mailto')]")
	WebElement EmailContact;

	@FindBy(xpath = "//a[@href='/Account/ForgotPassword.cshtml']")
	WebElement ForgotPasswordLink;

	@FindBy(xpath = "//label[@for='email']/following::input[@id='email']")
	WebElement EmailResetPasswordInput;

	@FindBy(xpath = "//input[@value='Send Instructions']")
	WebElement btnSendInstruction;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	public WebElement getTab(String tabName) {
		return driver.findElement(By.xpath(String.format("//span[normalize-space()='%s']", tabName)));
	}

	public WebElement PasswordChangeForm() {

		return driver.findElement(By.xpath("//legend[contains(text(),'Password Change Form')]"));
	}
	
	public WebElement ResetTokenTextBox() {

		return driver.findElement(By.id("resetToken"));
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
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		btnSendInstruction.click();
	}
	
	// Get reset token Label text
		public String getResetTokenLabel() {

			return resetTokenlabel.getText();
		}
	
	// Click reset password button
	public void clickbtnResetPassword() {
		
		btnResetPassword.click();
	}

	// Get Welcome user message
	public String GetWelcomeText() {

		return WelcomeText.getText();
	}

	public void Login(String strUserName, String strPasword) {
		// Fill user name
		this.setUserName(strUserName);
		// Fill password
		this.setPassword(strPasword);
		// Click Login button
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		this.clickLoginButton();
		Constant.WelcomeMessageLogin = Constant.WELCOME + strUserName;
	}

	public String GetLoginErrorMessage() {

		return LoginPageErrorMessage.getText();

	}

	public void ClearTextBox() {

		UID.clear();
		password.clear();
	}

	public String GetEmailContact() {

		return EmailContact.getAttribute("href");

	}

	// Login multi time with give username, password
	public void LoginMultiTime(String strUserName, String strPasword, int time) {

		ClearTextBox();
		for (int i = 1; i <= time; i++) {
			Login(strUserName, strPasword);
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
	
	public void resetPassword(String newpass, String confirmpass, String resettoken) {
		
		this.newPassword.clear();
		this.confirmPassword.clear();
		this.newPassword.sendKeys(newpass);
		this.confirmPassword.sendKeys(confirmpass);
		this.resetToken.sendKeys((Keys.chord(Keys.CONTROL, "a")),resettoken);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		this.btnResetPassword.click();
				
		
	}

}
