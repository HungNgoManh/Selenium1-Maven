package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	/**
	 * All WebElements are identified by @FindBy annotation
	 */
	WebDriver driver;

	public HomePage (WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
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
