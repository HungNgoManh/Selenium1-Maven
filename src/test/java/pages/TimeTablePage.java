package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TimeTablePage extends BasePage {

	// *********Constructor*********
	public TimeTablePage(WebDriver driver) {

		super(driver);
	}

	/**
	 * All WebElements are identified by @FindBy annotation
	 */

	@FindBy(xpath = "//table[@class='MyTable WideTable']")
	WebElement trainTimetable;
	
	@FindBy(xpath = "//h1 [@align='center']")
	WebElement pageHeader;
	

	// To locate rows of table.
	
	// To locate column of table
	//List<WebElement> Columns_row = rows_table.get(1).findElements(By.tagName("td"));

	public int TrainNumber(String from, String to) {

		int row;
		String[] celtext = { "", "" };
		List<WebElement> rows_table = trainTimetable.findElements(By.tagName("tr"));
		// To calculate no of rows In table.
		int rows_count = rows_table.size();
		
		// Loop will execute till the last row of table.
		for (row = 1; row < rows_count; row++) {			
					
			// To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			// To calculate no of columns (cells). In that specific row.
			// int columns_count = Columns_row.size();
			int columns_count = 3;
			// System.out.println("Number of cells In Row " + row + " are " +
			// columns_count);
			
			if ((celtext[0] == from ) && (celtext[1] == to)) break;
			// Loop will execute till the last cell of that specific row.
			for (int column = 2; column <= columns_count; column++) {
				
				String abc = Columns_row.get(column).getText();
				int i = 0;
				celtext[i] = abc;
				i++;
				
				
				// System.out.println("Cell Value of row number " + row + " and column number "
				// + column + " Is " + celtext);
			}  
			
		} return row;
		
		
	}

	public void selectLink(String from, String to, String link) {
		
		int linenumber = TrainNumber(from, to); 
		
		driver.findElement(By.xpath(String.format(
				"//tr[%s]//following-sibling::td/a[text()='%s']", String.valueOf(linenumber), link))).click();
		
	}
	
	
	
}
