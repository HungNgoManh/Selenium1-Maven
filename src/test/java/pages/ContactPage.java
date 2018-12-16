package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactPage  extends BasePage{
	
	//*********Constructor*********
    public ContactPage(WebDriver driver) {
        super(driver);
    }
    
    
    /**
	 * All WebElements are identified by @FindBy annotation
	 */
	
    @FindBy(xpath = "//a[contains(@href,'mailto')]")
		WebElement EmailContact;

  //*********Page Methods*********
    
    public  String getEmailContact () {
    	
    	return EmailContact.getAttribute("href");
    }
}

