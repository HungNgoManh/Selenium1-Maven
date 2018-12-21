package finaltest;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Constant;
import Common.Utilities;
import Common.Constant.ListBookTicket;
import pages.HomePage;
import pages.LoginPage;
import pages.MyTicketPage;
import pages.RegisterPage;
import testcase.TestBase;

public class FTTC01_FTTC02 extends TestBase {

	@Test(description = "User can filter Manage Ticket table with Depart Station")
	public void FTTC01() {

		// Create var to store number of ticket BEFORE and AFTER applying Filter
		int ticketnumberBeforeFilter, ticketnumberAfterFilter;
		
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

		// Step 3: Book more than 6 tickets
		page.GetInstance(HomePage.class).goToBookTicketPage().bookTickets(7);

		// Step 4: Go to My Ticket page
		page.GetInstance(HomePage.class).goToMyTicketPage();

		// Step 5-1: Check amount of target ticket before applying filter
		ticketnumberBeforeFilter = page.GetInstance(MyTicketPage.class).getAmountFollowDepartStationOfFirstTicket();

		// Step 5-2: Apply the filter for the 1st found ticket
		page.GetInstance(MyTicketPage.class).applyFilter(ListBookTicket.FilterDpStation,
				page.GetInstance(MyTicketPage.class).getDepartStationFirstTicket());

		// Step 5-3: Check a mount of target ticket after applying filter
		ticketnumberAfterFilter = page.GetInstance(MyTicketPage.class).getAmountFollowDepartStationOfFirstTicket();
		
		/* VP ideas:
		 *   - Compare the ticket for target Depart station Before and After filter, they must be the same
		 *   - In the Filter of target Depart station, there must be NO different depart station ticket. 
		 */
		
		// VP: Compare the amount
		Assert.assertEquals(ticketnumberBeforeFilter, ticketnumberAfterFilter);
		
		//VP: Verify NO other ticket displayed on this filter
		Assert.assertFalse(page.GetInstance(MyTicketPage.class).isWrongTicketDisplay(ListBookTicket.FilterDpStation));
		
	}

	@Test(description = "Error displays when user applies filter with un-existed value of the Status in Manage Ticket table")
	public void FTTC02() {

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

		// Step 3: Book more than 6 tickets
		page.GetInstance(HomePage.class).goToBookTicketPage().bookTickets(7);

		// Step 4: Go to My Ticket page
		page.GetInstance(HomePage.class).goToMyTicketPage();

		// Step 5: Select Paid for status
		page.GetInstance(MyTicketPage.class).applyFilter(ListBookTicket.FilterStatus, "Paid");

		// VP: Manage Ticket table say an error message: Sorry, can't find any results
		// that match your filters.
		// Please change the filters and try again.
		Assert.assertEquals(page.GetInstance(MyTicketPage.class).getErrorMessage(),
				Constant.MessageMyTicketPage.FilterErrorMessage);

	}

}
