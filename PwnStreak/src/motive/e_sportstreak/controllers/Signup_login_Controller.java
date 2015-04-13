package motive.e_sportstreak.controllers;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import motive.e_sportstreak.MainActivity;
import motive.e_sportstreak.SignupActivity;
import motive.e_sportstreak.Signup_login_Activity;
import motive.e_sportstreak.managers.Signup_login_Manager;
import motive.e_sportstreak.views.Signup_login_View;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Signup_login_Controller {
	protected static final Context Context = null;
	private Signup_login_Manager sign_log_manager;
	private Signup_login_View sign_log_view;

	public Signup_login_Controller(Activity activity, Signup_login_View signup_log_view) throws ClientProtocolException, JSONException, IOException
	{
		this.sign_log_manager = new Signup_login_Manager();
		this.sign_log_view = signup_log_view;
		
//		PlayerInfoDAO.getPlayerInfoDAO(activity.getApplicationContext()).update_game_picked("4", "2", "");
		
		login();
		signup();
	}
	
	public void login()
	{
		sign_log_view.getLoginButton().setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean success;

				String login_username = sign_log_view.getId().getText().toString();

				success = sign_log_manager.login(login_username, sign_log_view.getPassword().getText().toString());
				
					if (!success)
					{
						Toast.makeText(Signup_login_Activity.sign_log_activity.getApplication().getApplicationContext(), "Invalid Username or Password. Try Again!", Toast.LENGTH_LONG).show();
						sign_log_view.clearText();

					}
					else
					{
						// May have to change MainActivity.class to some other class
						Intent i = new Intent(Signup_login_Activity.sign_log_activity.getApplication().getApplicationContext(), MainActivity.class);

				    	Signup_login_Activity.sign_log_activity.startActivity(i);
				    	Signup_login_Activity.sign_log_activity.finish();
					}
				}
			
			
		});
	}
	
	public void signup()
	{
		sign_log_view.getSignUpButton().setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				/* CREATE NEWUSER ACTIVITY */
				
				Intent i = new Intent(Signup_login_Activity.sign_log_activity.getApplication().getApplicationContext(), SignupActivity.class);
				//Log.e("SUCESSFUL", "YUP");
				Signup_login_Activity.sign_log_activity.startActivity(i);
			}
			
		});
	}

}
