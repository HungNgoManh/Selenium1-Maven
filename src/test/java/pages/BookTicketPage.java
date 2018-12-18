package pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import Common.Constant.ListBookTicket;
import Common.Constant.MyTicketColumn;
import Common.TicketInfo;
import Common.Utilities;


public class BookTicketPage extends BasePage {

	// *********Constructor*********
	public BookTicketPage(WebDriver driver) {

		super(driver);
	}

	// ******Elements*********//
	/**
	 * All WebElements are identified by @FindBy annotation
	 */

	@FindBy(xpath = "//input[@value='Book ticket']")
	WebElement btnBookTicket;

	public void selectFromList(ListBookTicket bookOptions, String item) {
		Select list = new Select(
			driver.findElement(By.xpath(String.format("//select[@name='%s']", bookOptions))));
		list.selectByVisibleText(item);
	}
	
	public By selectList(ListBookTicket listing) {
		return By.xpath(String.format("//select[@name='%s']", listing ));
	}
	
	// Book ticket function
	public void bookTicket(TicketInfo infoTicket) {
		
		selectFromList(ListBookTicket.Date, infoTicket.getDepartDate());
		
		// wait for the dropdown display value
	//	Utilities.waitForDropDownDisplays(selectList(ListBookTicket.DepartStation));
		
		
		try {
			
			selectFromList(ListBookTicket.DepartStation, infoTicket.getDepartFrom());
			Thread.sleep(2000);
			selectFromList(ListBookTicket.ArriveStation, infoTicket.getArriveAt());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// wait for the dropdown display value
	//	Utilities.waitForDropDownDisplays(selectList(ListBookTicket.ArriveStation));
		
		
		
		selectFromList(ListBookTicket.SeatType, infoTicket.getSeatType());
		
		selectFromList(ListBookTicket.TicketAmount, infoTicket.getAmount());
		
		//Click on Book Ticket button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", btnBookTicket);
		btnBookTicket.click();
	}
	
	public String bookRandomDate() {
		LocalDate baseDate = LocalDate.now().plusDays(3);
		Integer maxRandomValue = 27;
		Integer randomDays = (int) (maxRandomValue * Math.random());
		LocalDate randomDate = baseDate.plusDays(randomDays);
		return DateTimeFormatter.ofPattern("M/d/yyyy").format(randomDate);
	}

	
	public String getCellContent(int row, MyTicketColumn column) {
		return driver.findElement(By.xpath(String.format("//tr[%s]/td[%s]", row, column.getValue())))
				.getText();
	}

//	public void Select() {
//		// testtttttttttt
//
//		Select dropdown = new Select(departDate);
//		dropdown.selectByIndex(2);
//
//	}

}
