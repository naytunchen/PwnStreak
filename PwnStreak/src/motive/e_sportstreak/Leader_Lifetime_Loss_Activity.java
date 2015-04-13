package motive.e_sportstreak;

import java.util.ArrayList;

import org.json.JSONException;

import motive.e_sportstreak.managers.PlayerInfoManager;
import motive.e_sportstreak.managers.UserManager;
import motive.e_sportstreak.managers.UserStatsManager;
import motive.e_sportstreak.models.UserStats;
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

public class Leader_Lifetime_Loss_Activity extends Activity{
	private static Context context;
	private PlayerInfoManager pManager = null;
	private UserManager uManager = null;
	private UserStatsManager usManager = null;
	
	Button bHome, bStats, bSettings, bCurrent, bMostWins;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leader_lifetime_l);
		this.pManager = PlayerInfoManager.getPlayerInfoManager(MainActivity.mainActivity.getBaseContext());
		this.uManager = UserManager.getUserManager(Signup_login_Activity.sign_log_activity.getApplicationContext());
		this.usManager = UserStatsManager.getUserStatsManager(MainActivity.mainActivity.getBaseContext());
		
		
		TextView current_streak = (TextView) findViewById(R.id.current_streak_text);
		TextView best_tv = (TextView) findViewById(R.id.lifetime_l_best_text);
		TextView win_tv = (TextView) findViewById(R.id.lifetime_l_w_text);
		
		try {
			current_streak.setText(Integer.toString(pManager.get_current_month_streak(UserManager.getLocalUser().getLocalUserID())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ArrayList<UserStats> list = null;
		try {
			list = usManager.get_lifetime_losses();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		TableLayout l1 = (TableLayout) findViewById(R.id.lifetime_l_tbl);
		
		
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
	        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.pale_orange));
	        row.addView(tv, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 0.5f));
	        tv.setText(Integer.toString(list.get(i).getTotal_longest_streak()));
	        tv.setTextColor(Color.parseColor("#ffffff"));

	        
	        tv = new TextView(this);
	        tv.setGravity(Gravity.CENTER);
	        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.pale_orange));
	        row.addView(tv, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 0.5f));
	        tv.setText(Integer.toString(list.get(i).getTotal_wins()));
	        tv.setTextColor(Color.parseColor("#ffffff"));
	        
	        tv = new TextView(this);
	        tv.setGravity(Gravity.CENTER);
	        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange_black));
	        row.addView(tv, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 0.5f));
	        tv.setText(Integer.toString(list.get(i).getTotal_losses()));
	        tv.setTextColor(Color.parseColor("#ffffff"));
	        
	        tv = new TextView(this);
	        tv.setGravity(Gravity.CENTER);
	        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.black));
	        row.addView(tv, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 0.5f));
	        tv.setText(Integer.toString(list.get(i).getTotal_percent()));
	        tv.setTextColor(Color.parseColor("#000000"));

		}
		
		
		final Intent lf_BestIntent = new Intent(this, Leader_Lifetime_Activity.class);
		final Intent lf_WinIntent = new Intent(this, Leader_Lifetime_Win_Activity.class);
		best_tv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(lf_BestIntent);
				overridePendingTransition(0,0);
			}
		});
		
		win_tv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(lf_WinIntent);
				overridePendingTransition(0,0);
			}
		});
		
		
		/* Setting up the button menu bar */
		this.bHome = (Button) findViewById(R.id.homeButton);
		this.bStats = (Button) findViewById(R.id.statButton);
		this.bSettings = (Button) findViewById(R.id.settingsButton);
		this.bCurrent = (Button) findViewById(R.id.lifetime_button_tbr).findViewById(R.id.current_longest_button);
		this.bMostWins = (Button) findViewById(R.id.lifetime_button_tbr).findViewById(R.id.most_wins_button);
		final Intent homeIntent = new Intent(this, MainActivity.class);
		final Intent setIntent = new Intent(this, SettingsActivity.class);
		final Intent statIntent = new Intent(this, MyStatsActivity.class);
		final Intent currentIntent = new Intent(this, LeaderboardActivity.class);
		final Intent mostWinsIntent = new Intent(this, Leader_Most_Win_Activity.class);
		
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
		
		bMostWins.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(mostWinsIntent);
				overridePendingTransition(0,0);
			}
		});



	}

	
	public static Context getContext()
	{
		return context;
	}


}
