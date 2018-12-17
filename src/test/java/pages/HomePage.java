package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Common.Constant;

public class HomePage extends BasePage {

	// *********Constructor*********

	public HomePage(WebDriver driver) {
		super(driver);
	}

	/**
	 * All WebElements are identified by @FindBy annotation
	 */

	@FindBy(xpath = "//span[normalize-space()='Login']")
	WebElement loginTab;

	@FindBy(xpath = "//span[normalize-space()='Register']")
	WebElement registerTab;

	@FindBy(xpath = "//span[normalize-space()='Contact']")
	WebElement contactTab;

	@FindBy(xpath = "//span[normalize-space()='Ticket price']")
	WebElement ticketPriceTab;

	@FindBy(xpath = "//span[normalize-space()='Book ticket']")
	WebElement bookTicketTab;

	@FindBy(xpath = "//span[normalize-space()='Change password']")
	WebElement changePasswordTab;

	// Go to Homepage
	public void OpenHomePage() {

		driver.get(Constant.URLTest);
	}

	// Go to LoginPage
	public LoginPage goToLoginPage() {

		click(loginTab);
		// I want to chain LoginPage's methods so I return LoginPage by initializing its
		// elements
		return new PageFactory().initElements(driver, LoginPage.class);
	}

	// Go to Contact
	public ContactPage goToContactPage() {

		click(contactTab);

		return new PageFactory().initElements(driver, ContactPage.class);
	}

	// Go to Register Page
	public RegisterPage goToRegisterPage() {

		click(registerTab);

		return new PageFactory().initElements(driver, RegisterPage.class);
	}

	// Go to Change Password Page
	public ChangePasswordPage goToChangePasswordPage() {

		click(changePasswordTab);

		return new PageFactory().initElements(driver, ChangePasswordPage.class);
	}

	public WebElement getTab(String tabName) {
		return driver.findElement(By.xpath(String.format("//span[normalize-space()='%s']", tabName)));
	}

	public String getPageHeader(String tabname) {

		WebElement header = driver.findElement(By.xpath("//h1[@align='center']"));
		return header.getText();

	}

	public void clickTab(String tabname) {

		WebElement TabElement = getTab(tabname);
		TabElement.click();
	}

}
