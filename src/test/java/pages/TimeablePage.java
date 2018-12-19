package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TimeablePage extends BasePage {
	
	// *********Constructor*********
		public TimeablePage(WebDriver driver) {

			super(driver);
		}

		
		public void selectLink(String from, String to, String link) {
			driver.findElement(By.xpath(String.format(
					"//tr/td[text()='%s']//following-sibling::td[text()='%s']//following-sibling::td/a[text()='%s']", from,
					to, link))).click();
		}
}
 