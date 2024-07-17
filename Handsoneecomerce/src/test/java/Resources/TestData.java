package Resources;

import Pojose.Login;
import Pojose.Parentcreateorderplace;
import Pojose.childcreateorderplace;

public class TestData {
	Login lp;
	Parentcreateorderplace cpo;
	public Login loginauthenticate(String UserEmail, String UserPassword )
	{
	 lp = new Login();
	lp.setUserEmail(UserEmail);
	lp.setUserPassword(UserPassword);
	return lp;
	}
	
	
	
	
	
}
