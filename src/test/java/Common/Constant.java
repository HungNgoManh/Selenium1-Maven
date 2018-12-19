package Common;

/*
 * 
 * Define all Constants  ....
 */
public class Constant {

	public static String URLTest = "http://logigearrailway.somee.com";
	public static String USERNAME = "hung.ngo.test@gmail.com";
	public static String PASSWORD = "Matkhau~1";
	public static String NEW_PASSWORD = "87654321";
	public static String WELCOME = "Welcome ";
	public static String EmailContact = "mailto:thanh.viet.le@logigear.com";
	public static String PID = "12345678";
	public static String MessageRegisterSuccess = "Thank you for registering your account";
	public static String EMAIL_NEWLY_CREATE = "";
	public static String WelcomeMessageLogin = "";
	public static String EMPTY = "";
	public static String gmail_service= "smtp.gmail.com";

	public static String TAB_LOGOUT = "Log out";
	public static String TAB_LOGIN = "Login";
	public static String TAB_CHANGE_PASSWORD = "Change password";
	public static String TAB_MY_TICKET = "My ticket";
	public static String TAB_CONTACT = "Contact";
	public static String TAB_REGISTER = "Register";

	public enum ListBookTicket {
		
		Date, DepartStation, ArriveStation,SeatType, TicketAmount,FilterDpStation,FilterStatus
	}
	
	public enum MyTicketColumn {
		DEPART_STATION("1"), ARRIVE_STATION("2"), SEAT_TYPE("3"), DEPART_DATE("4"), AMOUNT("7");

		private String value;

		public String getValue() {
			return value;
		}

		private MyTicketColumn(String value) {
			this.value = value;
		}
	}
	
	public static class MessageLoginPage {

		public static String MessageLoginAttemptError = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
		public static String MessageLoginError = "There was a problem with your login and/or errors exist in your form.";
		public static String MessageInvalidUsernamePassword = "Invalid username or password. Please try again.";
		public static String MessageResetTokenIncorrect = "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.";
		public static String MessagePasswordTokenInvalid="The password reset token is invalid.";	
	
	}
	
	

	public static class MessageRegisterPage {

		public static String MessageFormError = "There're errors in the form. Please correct the errors and try again.";
		public static String PasswordValidationError = "Invalid password length.";
		public static String PIDValidationError = "Invalid PID length.";

	}

	public static class MessageHeaderPage {

		public static String ManageTicketPageHeaderText = "Manage ticket";
		public static String TimetablePageHeaderText ="Ticket Price";

	}
	
	public static class MessageBookTicketPage {
		
		public static String BookSuccessfulMessage ="Ticket booked successfully!";
	}

	public static class MessageChangePasswordPage {

		public static String PageHeaderText = "Change password";
		public static String MessageChangePasswordSuccess = "Your password has been updated!";
		public static String MessagePasswordDidNotMatch ="The password confirmation did not match the new password.";
		public static String MessageCouldNotResetPassword ="Could not reset password. Please correct the errors and try again.";

	}

}
