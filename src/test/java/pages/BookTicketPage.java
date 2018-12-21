package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import Common.Constant;
import Common.Constant.ListBookTicket;
import Common.Constant.MyTicketColumn;
import Common.TicketInfo;

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

	// Select item from the dropdown
	public void selectFromList(ListBookTicket bookOptions, String item) {
		Select list = new Select(driver.findElement(By.xpath(String.format("//select[@name='%s']", bookOptions))));
		list.selectByVisibleText(item);
	}

	public By selectList(ListBookTicket listing) {
		return By.xpath(String.format("//select[@name='%s']", listing));
	}

	// Book ticket function
	public void bookTicket(TicketInfo infoTicket) {

		selectFromList(ListBookTicket.Date, infoTicket.getDepartDate());

		try {

			selectFromList(ListBookTicket.DepartStation, infoTicket.getDepartFrom());
			Thread.sleep(2000);
			selectFromList(ListBookTicket.ArriveStation, infoTicket.getArriveAt());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectFromList(ListBookTicket.SeatType, infoTicket.getSeatType());

		selectFromList(ListBookTicket.TicketAmount, infoTicket.getAmount());

		// Click on Book Ticket button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", btnBookTicket);
		btnBookTicket.click();
	}

	// Select random 1 item from the drop down list
	public void selectRandomFromList(ListBookTicket listing) {
		Select list = new Select(driver.findElement(By.xpath(String.format("//select[@name='%s']", listing))));
		Random rand = new Random();
		List<WebElement> listAll = list.getOptions();
		listAll.get(rand.nextInt(listAll.size())).click();
	}

	// Book randomly 1 ticket 
	public void bookTicketRandomly() {
		
		selectRandomFromList(ListBookTicket.Date);
		
		try {
			
		selectRandomFromList(ListBookTicket.DepartStation);
		
		Thread.sleep(1000);
		
		selectRandomFromList(ListBookTicket.ArriveStation);
		
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		selectRandomFromList(ListBookTicket.SeatType);
		selectFromList(ListBookTicket.TicketAmount, "1");
		// Click on Book Ticket button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", btnBookTicket);
		btnBookTicket.click();
	}
	
	//Book randomly many ticket 
	public void bookTickets(int tickets) {
		for (int i = 1; i <= tickets; i++) {
			bookTicketRandomly();
			clickTab(Constant.TAB_BOOK_TICKET);
		}
	}
	

	// Get content from cell of table for checking ticket content
	public String getCellContent(int row, MyTicketColumn column) {
		return driver.findElement(By.xpath(String.format("//tr[%s]/td[%s]", row, column.getValue()))).getText();
	}

}
