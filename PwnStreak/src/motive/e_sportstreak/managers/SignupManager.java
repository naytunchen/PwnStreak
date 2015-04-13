package motive.e_sportstreak.managers;

import motive.e_sportstreak.SignupActivity;
import motive.e_sportstreak.models.User;
import motive.e_sportstreak.models.data.UserDAO;
import android.util.Log;


public class SignupManager {

	private User currUser;
	
	public SignupManager()
	{
		this.currUser = null;
	}
	
	public User createUser(String first_name, String last_name, String username,
			String email, String password, String company, String phone) {
		currUser = null;
		try{
			currUser = UserDAO.getUserDAO(SignupActivity.signupAct.getApplication().getApplicationContext()).createUserOnServer(first_name, last_name, username, email, password);
			Log.e("SignupManager.java/createUser","Creation Successful?");
		} catch (Exception e){
			Log.e("SignupManager.java/createUser","Inside Catch");
			e.printStackTrace();
		}finally{return currUser;}
	}
}
