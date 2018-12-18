package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BookTicketPage extends BasePage {

	// *********Constructor*********
	public BookTicketPage(WebDriver driver) {

		super(driver);
	}
	
	// ******Elements*********//
	/**
	 * All WebElements are identified by @FindBy annotation
	 */
	
	@FindBy(xpath="//select[@name='Date']")
	WebElement departDate;
	
	@FindBy(xpath="//select[@name='DepartStation']")
	WebElement departFrom;
	
	@FindBy(xpath="//select[@name='ArriveStation']")
	WebElement ArriveAt;
	
	@FindBy(xpath="//select[@name='SeatType']")
	WebElement SeatType;
	
	@FindBy(xpath="//select[@name='TicketAmount']")
	WebElement ticketAmount;
	
	@FindBy (xpath="//input[@value='Book ticket']")
	WebElement btnBookTicket;
	
	public void Select () {
		// testtttttttttt

	Select dropdown= new Select(departDate);
	dropdown.selectByIndex(2);
	
	}
	
	

}
