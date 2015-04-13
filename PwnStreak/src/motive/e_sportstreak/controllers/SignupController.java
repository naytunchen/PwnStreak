package motive.e_sportstreak.controllers;


import java.io.UnsupportedEncodingException;

import motive.e_sportstreak.MainActivity;
import motive.e_sportstreak.SignupActivity;
import motive.e_sportstreak.managers.SignupManager;
import motive.e_sportstreak.managers.UserManager;
import motive.e_sportstreak.models.User;
import motive.e_sportstreak.utilities.FormValidation;
import motive.e_sportstreak.views.SignupView;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class SignupController {
	private SignupManager signManager;
	private SignupView signView;
	private User currUser = null;;
	
	public SignupController(Activity activity, SignupView signView)
	{
		this.signView = signView;
		this.signManager = new SignupManager();
		
		createUser();
	}

	public void createUser()
	{

		signView.getButton().setOnClickListener(new OnClickListener()
		{
			private String first_name, last_name, username, email, password, password2, company, phone;
			
			User currUser = null;

			@Override
			public void onClick(View view)
			{
				password = signView.getPass().getText().toString();
				password2 = signView.getConfirmPass().getText().toString();
				email = signView.getEmail().getText().toString();
				first_name = signView.getFirst_name().getText().toString();
				last_name = signView.getLast_name().getText().toString();
				username = signView.getUsername().getText().toString();
				company = signView.getCompany().getText().toString();
				phone = signView.getPhone().getText().toString();
				if(password.length() == 0)
					Log.e("LENGTH == 0", "UEAFHAF");
				else
					Log.e("LENGHT!=0",""+ password.length());

				if( password.length() == 0 || email.length() == 0 || first_name.length() == 0 || last_name.length() == 0 || username.length() == 0 || password2.length() == 0 || company.length() == 0 || signView.getPhone().getText().toString().length() == 0)
				{
					Log.e("ERROR","LAHDKLAHSDKLJAHSDLKJHASKLDJHALKSDJHAKLSJDHALKJSHDKLAJSHDLHJ");
					Toast.makeText(SignupActivity.signupAct.getApplication().getApplicationContext(), "All fields required!", Toast.LENGTH_LONG).show();
				}
				
				else{
					if( !FormValidation.checkNoDigit(first_name))
					{
						Toast.makeText(SignupActivity.signupAct.getApplication().getApplicationContext(), "Invalid First Name! Only letters are allowed.", Toast.LENGTH_LONG).show();
					}
					else if( !FormValidation.checkNoDigit(last_name))
					{
						Toast.makeText(SignupActivity.signupAct.getApplication().getApplicationContext(), "Invalid Last Name! Only letters are allowed", Toast.LENGTH_LONG).show();
					}
					else if( !FormValidation.isValidEmail(email))
					{
						Toast.makeText(SignupActivity.signupAct.getApplication().getApplicationContext(), "Invalid Email!", Toast.LENGTH_LONG).show();
					}
					else if(!FormValidation.isValidPassword(password) || !FormValidation.isValidPassword(password2))
					{
						Toast.makeText(SignupActivity.signupAct.getApplication().getApplicationContext(), "Password must be between 5-12 char!", Toast.LENGTH_LONG).show();
						signView.clearPasswords();
					}
					else if( !FormValidation.confirmPassword(password, password2) )
					{
						Toast.makeText(SignupActivity.signupAct.getApplication().getApplicationContext(), "Your passwords do not match! Please try again.", Toast.LENGTH_LONG).show();
						signView.clearPasswords();
					}
					else
					{

						currUser = signManager.createUser(first_name, last_name, username, email, password, company, phone);
						if( currUser == null )
						{
							Log.e("SignupController.java/ifCurrUser==Null","CurrUser==Null");
							Toast.makeText(SignupActivity.signupAct.getApplication().getApplicationContext(), "ID already exists!!!", Toast.LENGTH_LONG).show();

						}
						else
						{
							Log.e("SignupController.java/else","should not be here");

							Toast.makeText(SignupActivity.signupAct.getApplication().getApplicationContext(), "Account Created!", Toast.LENGTH_LONG).show();
							try
							{
								currUser = UserManager.getUserManager(SignupActivity.signupAct.getApplication().getApplicationContext()).loginToServer(this.username, this.password);
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							/*** Sign User in locally ***/
							String full_name = currUser.getFirst_name() + " " + currUser.getLast_name();
							UserManager.getUserManager(SignupActivity.signupAct.getApplicationContext()).setLocalUser(currUser.getFirst_name(), currUser.getLast_name(), currUser.getUsername(), currUser.getEmail(), currUser.getPassword());

							Intent i = new Intent(SignupActivity.signupAct.getApplication().getApplicationContext(), MainActivity.class);
//							UserManager.clearLocalWineCellar();
							SignupActivity.signupAct.startActivity(i);
							SignupActivity.signupAct.finish();
						}
					}
				}
			}

			private String convertEmailtoID(String emailAdd) {
				char[] emailarray = emailAdd.toCharArray();
				StringBuilder s = new StringBuilder(emailarray.length);
				for(int i = 0; i< emailarray.length; i++)
				{
					s.append((int)emailarray[i]);
				}
				String id1 = s.toString();
				Log.e("tag", id1);
				return id1;
			}
		});	
	}
}

