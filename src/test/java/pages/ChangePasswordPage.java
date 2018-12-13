package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage {

	/**
	 * All WebElements are identified by @FindBy annotation
	 */
	WebDriver driver;

	@FindBy(id = "currentPassword")
	WebElement currentpassword;

	@FindBy(id = "newPassword")
	WebElement newpassword;

	@FindBy(id = "confirmPassword")
	WebElement confirmpassword;

	@FindBy(xpath = "//input[@title='Change password']")
	WebElement btnChangePassword;

	@FindBy(xpath = "//p[@class='message success']")
	WebElement messageChangepassword;

	public ChangePasswordPage(WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	public String GetMessageChangePasswordSuccess() {

		return messageChangepassword.getText();
	}
	// Change password function

	public void ChangePassword(String currentpass, String newpass, String confirmpass) {

		this.currentpassword.sendKeys(currentpass);
		this.newpassword.sendKeys(newpass);
		this.confirmpassword.sendKeys(confirmpass);

		// Scroll to bottom page and click Change password button
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		this.btnChangePassword.click();
	}

}
