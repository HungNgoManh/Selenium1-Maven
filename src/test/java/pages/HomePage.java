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
	
	@FindBy(xpath = "//span[normalize-space()='Timetable']")
	WebElement TimetableTab;

	@FindBy(xpath = "//h1")
	WebElement pageTitle;

	// Go to Homepage
	public void OpenHomePage() {

		driver.get(Constant.URLTest);
	}

	// Go to LoginPage
	public LoginPage goToLoginPage() {

		click(loginTab);
		new PageFactory();
		// I want to chain LoginPage's methods so I return LoginPage by initializing its
		// elements
		return PageFactory.initElements(driver, LoginPage.class);
	}

	// Go to Contact
	public ContactPage goToContactPage() {

		click(contactTab);

		new PageFactory();
		return PageFactory.initElements(driver, ContactPage.class);
	}

	// Go to Register Page
	public RegisterPage goToRegisterPage() {

		click(registerTab);

		new PageFactory();
		return PageFactory.initElements(driver, RegisterPage.class);
	}

	// Go to Change Password Page
	public ChangePasswordPage goToChangePasswordPage() {

		click(changePasswordTab);

		new PageFactory();
		return PageFactory.initElements(driver, ChangePasswordPage.class);
	}

	// Go to Book Ticket Page
	public BookTicketPage goToBookTicketPage() {

		click(bookTicketTab);

		new PageFactory();
		return PageFactory.initElements(driver, BookTicketPage.class);
	}
	
	// Go to Timetable page
		public TimeTablePage goToTimetablePage() {

			click(TimetableTab);

			new PageFactory();
			return PageFactory.initElements(driver, TimeTablePage.class);
		}

	
	// Get Tab to check if Tab is displayed??
	public WebElement getTab(String tabName) {
		return driver.findElement(By.xpath(String.format("//span[normalize-space()='%s']", tabName)));
	}

	// Get page header content for all pages
	public String getPageHeader() {

		WebElement header = driver.findElement(By.xpath("//h1[@align='center']"));
		return header.getText();

	}

	public void clickTab(String tabname) {

		WebElement TabElement = getTab(tabname);
		TabElement.click();
	}

	// Get page header
	public String getCurrentHeader() {

		return pageTitle.getText();
	}

}
