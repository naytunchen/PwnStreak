package motive.e_sportstreak;

import java.util.ArrayList;
import org.json.JSONException;

import motive.e_sportstreak.managers.PlayerInfoManager;
import motive.e_sportstreak.managers.UserManager;
import motive.e_sportstreak.managers.UserStatsManager;
import motive.e_sportstreak.models.GameResult;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MyStatsActivity extends Activity {
	
	private static Context context;
	private PlayerInfoManager pManager = null;
	private UserManager uManager = null;
	private UserStatsManager usManager = null;
	private ArrayList<GameResult> list = null;
	private ArrayList<GameResult> cml = null;
	
	public static int number_of_picks = 0;
	public static int number_of_wins = 0;
	public static int number_of_losses = 0;
	public static int total_win_percent = 0;
	
	TextView total_picks_tv = null;
	TextView total_wins_tv = null;
	TextView total_losses_tv = null;
	TextView longest_streak_tv = null;
	TextView win_percent_tv = null;
	
	Button bHome, bLeaders, bSettings;
	

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mystats);
		this.pManager = PlayerInfoManager.getPlayerInfoManager(MainActivity.mainActivity.getBaseContext());
		this.uManager = UserManager.getUserManager(Signup_login_Activity.sign_log_activity.getApplicationContext());
		
		UserManager.getLocalUser();
		String userID = LocalUser.getLocalUserID();
		String username = UserManager.getLocalUser().getLocalUsername();
		try {
			list = pManager.get_stats_results(userID);
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		total_picks_tv = (TextView)findViewById(R.id.total_picks);
		total_picks_tv.setText(Integer.toString(MainActivity.number_of_picks));
		
		total_wins_tv = (TextView)findViewById(R.id.total_wins);
		total_wins_tv.setText(Integer.toString(MainActivity.number_of_wins));
		
		total_losses_tv = (TextView)findViewById(R.id.total_losses);
		total_losses_tv.setText(Integer.toString(MainActivity.number_of_losses));
		
		longest_streak_tv = (TextView)findViewById(R.id.longest_streak);
		longest_streak_tv.setText(Integer.toString(MainActivity.curr_longest_streak));

		
		
		win_percent_tv = (TextView)findViewById(R.id.winning_percentage);
		win_percent_tv.setText(Integer.toString(MainActivity.total_win_percent) + "%");
		
		TableLayout l1 = (TableLayout) findViewById(R.id.history_table);
		TextView user_stats_tv = (TextView) findViewById(R.id.user_stats);
		user_stats_tv.setText(username +"'s Statistics");
		TextView title_date = (TextView) findViewById(R.id.stats_date_id);
		TextView title_pick = (TextView) findViewById(R.id.stats_pick_id);
		TextView title_result = (TextView) findViewById(R.id.stats_result_id);
		TextView current_streak = (TextView) findViewById(R.id.current_streak_text);
		try {
			current_streak.setText(Integer.toString(pManager.get_current_month_streak(userID)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getResult().equals("win") || list.get(i).getResult().equals("lose"))
			{
		        TableRow row= new TableRow(this);
		        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		      
		        row.setLayoutParams(tableRowParams);
		        l1.addView(row, new TableLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		        
		        TextView tv = new TextView(this);
		        row.addView(tv, new TableRow.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
		        tv.setText(list.get(i).getGame_date());
		        tv.setTextColor(Color.parseColor("#747474"));
		        tv.setPadding(title_date.getPaddingLeft(), 0, title_date.getPaddingRight(), 0);
		        
		        tv = new TextView(this);
		        row.addView(tv, new TableRow.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 3.3f));
		        tv.setText(list.get(i).getSelected_team());
		        tv.setTextColor(Color.parseColor("#747474"));
		        tv.setPadding(title_pick.getPaddingLeft(), 0, title_pick.getPaddingRight(), 0);

		        
		        tv = new TextView(this);
		        row.addView(tv, new TableRow.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 3.3f));
		        tv.setGravity(Gravity.RIGHT);
		        tv.setText(list.get(i).getResult().toUpperCase());
		        tv.setTextColor(Color.parseColor("#f27a3f"));
		        tv.setPadding(title_result.getPaddingLeft(), 0, title_result.getPaddingRight(), 0);

			}
		}
		
		
		/* Setting up the button menu bar */
		this.bHome = (Button) findViewById(R.id.homeButton);
		this.bLeaders = (Button) findViewById(R.id.leaderButton);
		this.bSettings = (Button) findViewById(R.id.settingsButton);
		final Intent homeIntent = new Intent(this, MainActivity.class);
		final Intent setIntent = new Intent(this, SettingsActivity.class);
		final Intent leaderIntent = new Intent(this, LeaderboardActivity.class);
		
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
		
		bLeaders.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(leaderIntent);
				overridePendingTransition(0,0);

			}
		});
	}
	
	public static Context getContext()
	{
		return context;
	}
	
}