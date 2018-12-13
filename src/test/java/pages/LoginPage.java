package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	/**
	 * All WebElements are identified by @FindBy annotation
	 */
	WebDriver driver;

	@FindBy(id = "username")
	WebElement UID;

	@FindBy(id="password")
	WebElement password;

	@FindBy(xpath = "//div[@class='account']//strong")
	WebElement WelcomeText;

	@FindBy(xpath = "//input[@title='Login']")
	WebElement btnLogin;

	@FindBy(xpath = "//body/div//div[@id='content']/following-sibling::div//p")
	WebElement PageFooter;

	@FindBy(xpath = "//body/child::div/div[@id='content']/p[starts-with(@class,'message')]")
	WebElement LoginErrorMessage;

	@FindBy(xpath = "//body/child::div/div[@id='content']/p[normalize-space(@text())='']")
	WebElement LoginErrorMessage2;

	@FindBy(xpath = "//ancestor::select[@name='Date']")
	WebElement drbDepartDate;

	@FindBy(xpath = "//div[@id='page']/div//li/parent::ul/preceding::div/following::div/h1[text()='Book ticket']")
	WebElement BookTicketPageTitle;

	@FindBy(xpath = "//a[contains(@href,'mailto')]")
	WebElement EmailContact;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	public WebElement getTab(String tabName) {
		return driver.findElement(By.xpath(String.format("//span[normalize-space()='%s']", tabName)));
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
	}

	public String GetLoginErrorMessage() {

		return LoginErrorMessage.getText();

	}
	
	public void ClearTextBox () {
		
		UID.clear();
		password.clear();
	}

	public String GetEmailContact() {

		return EmailContact.getAttribute("href");

	}

	public void LoginMultiTime(String strUserName, String strPasword, int time) {
		
		ClearTextBox();
		for (int i = 1; i <= time; i++) {
			Login(strUserName, strPasword);
		}
	}
}
