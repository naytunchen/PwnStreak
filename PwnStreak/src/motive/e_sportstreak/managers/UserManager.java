package motive.e_sportstreak.managers;

import java.io.UnsupportedEncodingException;
import motive.e_sportstreak.models.LocalUser;
import motive.e_sportstreak.models.User;
import motive.e_sportstreak.models.data.UserDAO;

import android.content.Context;
import android.util.Log;

public class UserManager {
	
	private final static String EMPTY = "";
	private static Context context = null;
	private static UserDAO dao = null; 
	private static LocalUser localUser;
	
	/* Probably has to add MatchDAO */
	
	private UserManager(Context context)
	{
		UserManager.context = context;
		dao = UserDAO.getUserDAO(context);
		/* MatchDAO */
	}
	
	public static UserManager getUserManager(Context context)
	{
		return new UserManager(context);
	}
	
	public User createUserOnServer(String first_name, String last_name, String username, String email, String password) throws UnsupportedEncodingException{
		return dao.createUserOnServer(first_name, last_name, username, email, password);
	}
	
	public User loginToServer(String username,String password) throws UnsupportedEncodingException{

		return dao.loginServer(username, password);
	}
	
	
	public void setLocalUser(String firstname, String lastname, String username, String email, String password)
	{
		localUser = new LocalUser(this.getUsernameFromLocal(), firstname, lastname, username, email, password);
	}
	public static LocalUser getLocalUser() {
		return localUser;
	}
	
	
	public String getUsernameFromLocal()
	{
		
		Log.i("TESTING 22222222222222 DAO GETUSERIDFROMDB: ", dao.getUserIDFromDB(UserManager.context));
		return dao.getUserIDFromDB(UserManager.context);
	}
	

}
