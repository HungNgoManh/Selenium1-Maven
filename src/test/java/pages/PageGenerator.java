package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//Note (OB):
//Without Page Factory we can generate and return a new instance of a page by using below line.
//return pageClass.getDeclaredConstructor(WebDriver.class, WebDriverWait.class).newInstance(this.driver, this.wait);

public class PageGenerator {

	public WebDriver driver;

	// Constructor
	public PageGenerator(WebDriver driver) {

		this.driver = driver;
	}

	public <TPage extends BasePage> TPage GetInstance(Class<TPage> pageClass) {

		// Initialize the Page with its elements and return it.
		return PageFactory.initElements(driver, pageClass);

	}
}