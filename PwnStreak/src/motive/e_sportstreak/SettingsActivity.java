package motive.e_sportstreak;

import motive.e_sportstreak.models.data.UserDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class SettingsActivity extends Activity { 
	
	Button btnLogout;
	private static UserDAO dao = null;
	Button bHome, bStats, bLeaders;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		dao = UserDAO.getUserDAO(Signup_login_Activity.sign_log_activity.getApplicationContext());
		if (dao.isUserLoggedIn(getApplicationContext()))
		{
			setContentView(R.layout.settings_activity);
			btnLogout = (Button) findViewById(R.id.btnLogout);
			
			btnLogout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dao.logoutUser(getApplicationContext());
					Log.i("SettingActivity.java: onCreate(): logoutUser(): ", "Logout Successful!!!!");
					
					Intent login = new Intent(getApplicationContext(), Signup_login_Activity.class);
					startActivity(login);
					finish();
				}
			});

		}
		
		else
		{
			Intent login = new Intent(getApplicationContext(), Signup_login_Activity.class);
			login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(login);
			finish();
		}
		
		Intent intent = getIntent();
		
		/* Setting up the button menu bar */
		this.bHome = (Button) findViewById(R.id.homeButton);
		this.bStats = (Button) findViewById(R.id.statButton);
		this.bLeaders = (Button) findViewById(R.id.leaderButton);
		final Intent statIntent = new Intent(this, MyStatsActivity.class);
		final Intent homeIntent = new Intent(this, MainActivity.class);
		final Intent leaderIntent = new Intent(this, LeaderboardActivity.class);
		
		bStats.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(statIntent);
				overridePendingTransition(0,0);
				
			}
		});
		
		bHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(homeIntent);
				overridePendingTransition(0,0);
			}
		});
		
		bLeaders.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(leaderIntent);
				overridePendingTransition(0,0);

			}
		});
		
		Log.i("Settings Activity", "Initiated");
	}
	
	

}
