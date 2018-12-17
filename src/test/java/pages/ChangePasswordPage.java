package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePasswordPage extends BasePage {

	// *********Constructor*********
	public ChangePasswordPage(WebDriver driver) {

		super(driver);
	}

	/**
	 * All WebElements are identified by @FindBy annotation
	 */

	@FindBy(id = "currentPassword")
	WebElement currentpassword;

	@FindBy(id = "newPassword")
	WebElement newpassword;

	@FindBy(id = "confirmPassword")
	WebElement confirmpassword;

	@FindBy(xpath = "//input[@title='Change password']")
	WebElement btnChangePassword;

	@FindBy(xpath = "//p[starts-with(@class,'message')]")
	WebElement messageChangepassword;

	@FindBy(xpath="//label[@class='validation-error' and @for ='confirmPassword']")
	WebElement lblConfirmPassword;

	// Get message after change password
	public String GetMessageChangePassword() {

		return messageChangepassword.getText();
	}
	
	// Get label text of Confirm password
	
	public String GetLabelConfirmPassword () {
		
		return lblConfirmPassword.getText();
	}
	
	// Change password function

	public void ChangePassword(String currentpass, String newpass, String confirmpass) {

		this.currentpassword.sendKeys(currentpass);
		this.newpassword.sendKeys(newpass);
		this.confirmpassword.sendKeys(confirmpass);

		// Scroll to bottom page and click Change password button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", btnChangePassword);
		this.btnChangePassword.click();
	}

}
