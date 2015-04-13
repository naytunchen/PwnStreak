package motive.e_sportstreak.managers;

import motive.e_sportstreak.Signup_login_Activity;
import motive.e_sportstreak.models.User;
import motive.e_sportstreak.models.data.UserDAO;

import android.widget.Toast;

public class Signup_login_Manager {
	public boolean login(String id, String pass)
	{
		User curr = null;
		if((curr = UserDAO.getUserDAO(Signup_login_Activity.sign_log_activity.getApplicationContext()).loginServer(id, pass)) == null){

			return false;
			
		}else{
			UserManager.getUserManager(Signup_login_Activity.sign_log_activity.getApplicationContext()).setLocalUser(curr.getFirst_name(), curr.getLast_name(), curr.getUsername(), curr.getEmail(), curr.getPassword());
			Toast.makeText(Signup_login_Activity.sign_log_activity.getApplicationContext(), "Retrieving Data...", Toast.LENGTH_LONG).show();

		}
		return true;
	}
	
}
