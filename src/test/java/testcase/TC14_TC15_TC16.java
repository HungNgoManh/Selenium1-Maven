package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Constant;
import Common.Utilities;
import Common.Constant.MyTicketColumn;
import Common.TicketInfo;
import pages.BookTicketPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import pages.TimeTablePage;

public class TC14_TC15_TC16 extends TestBase {

	// Init new ticket need to book! using a random depart date
	TicketInfo newTicket = new TicketInfo(Utilities.bookRandomDate(), "Sài Gòn", "Nha Trang",
			"Soft bed with air conditioner", "1");

	@Test(description = "User can book 1 ticket at a time")
	public void TC14() {

		/*
		 * Precondition: Create and activate a new account
		 */

		// Step 1: Open Railway page
		page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Create new account
		page.GetInstance(HomePage.class).goToRegisterPage().RegisterNewUser(Utilities.EmailGenerator(),
				Constant.PASSWORD, Constant.PASSWORD, Constant.PID);

		// Step 3: Active account
		page.GetInstance(RegisterPage.class).ActiveEmail();

		/*
		 * Testcase steps:
		 * 
		 */
		// Step 1: Navigate to QA Railway Login page
		page.GetInstance(HomePage.class).goToLoginPage();

		// Step 2: Login with account has just been created
		page.GetInstance(LoginPage.class).loginToRailway(Constant.EMAIL_NEWLY_CREATE, Constant.PASSWORD);

		// Step 3: Go to Book ticket page
		page.GetInstance(HomePage.class).goToBookTicketPage();

		// Step 4: Book needed ticket
		page.GetInstance(BookTicketPage.class).bookTicket(newTicket);

		// VP: Message "Ticket booked successfully!" displays.
		Assert.assertEquals(page.GetInstance(HomePage.class).getCurrentHeader(),
				Constant.MessageBookTicketPage.BookSuccessfulMessage);

		// VP: Ticket information display correctly (Depart Date, Depart Station, Arrive
		// Station, Seat Type, Amount)
		Assert.assertEquals(page.GetInstance(BookTicketPage.class).getCellContent(2, MyTicketColumn.DEPART_DATE),
				newTicket.getDepartDate());
		Assert.assertEquals(page.GetInstance(BookTicketPage.class).getCellContent(2, MyTicketColumn.DEPART_STATION),
				newTicket.getDepartFrom());
		Assert.assertEquals(page.GetInstance(BookTicketPage.class).getCellContent(2, MyTicketColumn.ARRIVE_STATION),
				newTicket.getArriveAt());
		Assert.assertEquals(page.GetInstance(BookTicketPage.class).getCellContent(2, MyTicketColumn.SEAT_TYPE),
				newTicket.getSeatType());
		Assert.assertEquals(page.GetInstance(BookTicketPage.class).getCellContent(2, MyTicketColumn.AMOUNT),
				newTicket.getAmount());

	}

	/*
	 * 
	 *  
	 *  TC 15, 16 : NOT COMPLETED YET 
	 *  
	 *  
	 *  
	 *  
	 *  
	 */
	@Test(description = "\"Ticket price\" page displays with ticket details after clicking on \"check price\" link in \"Train timetable\" page")
	public void TC15() {

		/*
		 * Precondition: Create and activate a new account
		 */

		// Step 1: Open Railway page
		//page.GetInstance(HomePage.class).OpenHomePage();

		// Step 2: Create new account
	//	page.GetInstance(HomePage.class).goToRegisterPage().RegisterNewUser(Utilities.EmailGenerator(),
			//	Constant.PASSWORD, Constant.PASSWORD, Constant.PID);

		// Step 3: Active account
		//page.GetInstance(RegisterPage.class).ActiveEmail();

		/*
		 * Testcase steps:
		 * 
		 */
		// Step 1: Login with valid account
		// test:
		page.GetInstance(HomePage.class).OpenHomePage();
		page.GetInstance(HomePage.class).goToLoginPage().loginToRailway(Constant.USERNAME, Constant.PASSWORD);

		// Step 2: Go to Timetable Page.
		page.GetInstance(HomePage.class).goToTimetablePage();

		// Step 3: Click on "check price" link of the route from "ÄÃ  Náºµng" to "SÃ i GÃ²n"
		page.GetInstance(TimeTablePage.class).selectLink("Đà Nẵng", "Sài Gòn", "check price");

		// VP: "Ticket Price" page is loaded.
		Assert.assertEquals(page.GetInstance(HomePage.class).getPageHeader(),
				Constant.MessageHeaderPage.TimetablePageHeaderText);

	}

	@Test(description = "User can cancel a ticket")
	public void TC16() {

	}

}
