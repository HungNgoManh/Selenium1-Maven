package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Constant.ListBookTicket;


public class MyTicketPage extends BasePage {
	
	// *********Constructor*********
		public MyTicketPage (WebDriver driver) {
			super(driver);
		}

	/**
	 * All WebElements are identified by @FindBy annotation
	 */
	
		public String getErrorMessage() {
			return driver.findElement(By.xpath("//div[contains(@class,'error')]")).getText();
		}
		
		// Select item from the list and click apply Filter		
		public void applyFilter(ListBookTicket listing,String item) {
			selectFromList(listing,item);
			driver.findElement(By.xpath(String.format("//input[@value='Apply filter']"))).click();
		}
		
		// Select an item from the list
		public void selectFromList(ListBookTicket listing, String item) {
			Select list = new Select(
					driver.findElement(By.xpath(String.format("//select[@name='%s']", listing))));
			list.selectByVisibleText(item);
		}
		
		// Get the 1st element of Depart station
		public String getDepartStationFirstTicket() {
			return driver.findElement(By.xpath(String.format("//table[@class='MyTable']//td[(count(//tr/th[.='Depart Station']/preceding-sibling::th)+1)]"))).getText();
		}
		
		// Get current text of selected item on the list
		public String getTextOfCurrentSelectedItem(ListBookTicket listing) {
			return driver
					.findElement(By
							.xpath(String.format("//select[@name='%s']//option[@selected='selected']", listing)))
					.getText();
		}
		
		// to display how many ticket after filtering it
		public int getAmountFollowDepartStationOfFirstTicket () {
			List<WebElement> displays = driver.findElements(By.xpath(String.format("//table[@class='MyTable']//td[(count(//tr/th[.='Depart Station']/preceding-sibling::th)+1)][normalize-space()='%s']",getDepartStationFirstTicket())));
			return displays.size();
		}
		
		// To check if a ticket we don't select but it displays on the table
	
		public boolean isWrongTicketDisplay(ListBookTicket listing) {
			List<WebElement> wrongTickets = driver.findElements(By.xpath(String.format("//table[@class='MyTable']//td[(count(//tr/th[.='Depart Station']/preceding-sibling::th)+1)][normalize-space()!='%s']",getTextOfCurrentSelectedItem(listing))));
			int amount = wrongTickets.size();
			if (amount > 0) {
				return true;
			} else {
				return false;
			}
		}
	
}
