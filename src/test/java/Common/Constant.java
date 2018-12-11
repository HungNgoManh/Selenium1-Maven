package Common;


public class Constant {

	public static String URLTest="http://logigearrailway.somee.com";
	public static String USERNAME = "hung.ngo.test@gmail.com";
	public static String PASSWORD = "12345678";
	public static String WelcomeText = "Welcome "+ USERNAME;
    public static String EmailContact = "mailto:thanh.viet.le@logigear.com";
    public static String PID = "12345678";
    public static String MessageRegisterSuccess ="Thank you for registering your account";
    
  
    public static class MessageLoginPage{
    
    	public static String MessageLoginAttemptError = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
    	public static String MessageLoginError = "There was a problem with your login and/or errors exist in your form.";
    	public static String MessageInvalidUsernamePassword ="Invalid username or password. Please try again.";
    }
    
    public static class MessageRegisterPage{
        
    	public static String MessageFormError = "There're errors in the form. Please correct the errors and try again.";

    }
}
