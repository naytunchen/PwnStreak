package motive.e_sportstreak;

import java.util.ArrayList;

import motive.e_sportstreak.managers.PlayerInfoManager;
import motive.e_sportstreak.managers.UserManager;
import motive.e_sportstreak.managers.UserStatsManager;
import motive.e_sportstreak.models.UserStats;

import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Leader_Most_Win_Activity extends Activity{
	private static Context context;
	private PlayerInfoManager pManager = null;
	private UserManager uManager = null;
	private UserStatsManager usManager = null;
	
	Button bHome, bStats, bSettings, bCurrent, bLifeTime;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leader_most_win);
		this.pManager = PlayerInfoManager.getPlayerInfoManager(MainActivity.mainActivity.getBaseContext());
		this.uManager = UserManager.getUserManager(Signup_login_Activity.sign_log_activity.getApplicationContext());
		this.usManager = UserStatsManager.getUserStatsManager(MainActivity.mainActivity.getBaseContext());
		
		
		TextView current_streak = (TextView) findViewById(R.id.current_streak_text);
		TextView most_wins_tv = (TextView) findViewById(R.id.most_win_month_wins_text);
		
		try {
			current_streak.setText(Integer.toString(pManager.get_current_month_streak(UserManager.getLocalUser().getLocalUserID())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ArrayList<UserStats> list = null;
		try {
			list = usManager.get_most_wins();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		TableLayout l1 = (TableLayout) findViewById(R.id.most_win_tbl);
		
		
		Log.e("LIST SIZE IN MOST WIN JAVA:", Integer.toString(list.size()));
		for(int i = 0; i < list.size(); i++)
		{
			TableRow row = new TableRow(this);
			TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			
			row.setLayoutParams(tableRowParams);
			row.setBackgroundColor(Color.parseColor("#BEC2C3"));
	        l1.addView(row, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	        
	        TextView tv = new TextView(this);
	        tv.setGravity(Gravity.CENTER);
	        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.black));
	        row.addView(tv, new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.5f));
	        tv.setText(list.get(i).getUsername());
	        tv.setTextColor(Color.parseColor("#000000"));
	        
	        
	        tv = new TextView(this);
	        tv.setGravity(Gravity.CENTER);
	        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange_black));
	        row.addView(tv, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 0.5f));
	        tv.setText(Integer.toString(list.get(i).getCurr_month_wins()));
	        tv.setTextColor(Color.parseColor("#ffffff"));

	        
	        tv = new TextView(this);
	        tv.setGravity(Gravity.CENTER);
	        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.black));
	        row.addView(tv, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 0.5f));
	        tv.setText(Integer.toString(list.get(i).getCurr_streak()));
	        tv.setTextColor(Color.parseColor("#000000"));
	        
	        tv = new TextView(this);
	        tv.setGravity(Gravity.CENTER);
	        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.black));
	        row.addView(tv, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 0.5f));
	        tv.setText(Integer.toString(list.get(i).getCurr_month_percent()));
	        tv.setTextColor(Color.parseColor("#000000"));

		}
		
		
		/* Setting up the button menu bar */
		this.bHome = (Button) findViewById(R.id.homeButton);
		this.bStats = (Button) findViewById(R.id.statButton);
		this.bSettings = (Button) findViewById(R.id.settingsButton);
		this.bCurrent = (Button) findViewById(R.id.most_win_button_tbr).findViewById(R.id.current_longest_button);
		this.bLifeTime = (Button) findViewById(R.id.most_win_button_tbr).findViewById(R.id.lifetimeButton);
		final Intent homeIntent = new Intent(this, MainActivity.class);
		final Intent setIntent = new Intent(this, SettingsActivity.class);
		final Intent statIntent = new Intent(this, MyStatsActivity.class);
		final Intent currentIntent = new Intent(this, LeaderboardActivity.class);
		final Intent lifeTimeIntent = new Intent(this, Leader_Lifetime_Activity.class);
		
		bHome.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(homeIntent);
				overridePendingTransition(0,0);
			}
		});
		
		bSettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(setIntent);
				overridePendingTransition(0,0);
			}
		});
		
		bStats.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(statIntent);
				overridePendingTransition(0,0);

			}
		});
		
		bCurrent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(currentIntent);
				overridePendingTransition(0,0);
			}
		});
		
		bLifeTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(lifeTimeIntent);
				overridePendingTransition(0,0);
			}
		});



	}

	public void longest_clicked()
	{
		
	}
	
	public void current_clicked()
	{
		
	}
	
	public static Context getContext()
	{
		return context;
	}
}

