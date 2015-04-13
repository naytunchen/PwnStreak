package motive.e_sportstreak.models;

import android.util.Log;

public class LocalUser extends User{
	private static String id = null;
	public LocalUser(String id, String first_name, String last_name, String username, String email, String password)
	{
		super(first_name, last_name, username, email, password);
		LocalUser.id = id;
		Log.i("TESTING TESTING TESTING LOCAL USER ID: ", LocalUser.id);
	}
	
	public static String getLocalUserID()
	{
		return id;
	}

	public String getLocalUsername()
	{
		return super.getUsername();
	}
}
