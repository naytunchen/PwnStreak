package motive.e_sportstreak;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.TargetApi;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import motive.e_sportstreak.managers.MatchManager;
import motive.e_sportstreak.managers.PlayerInfoManager;
import motive.e_sportstreak.managers.UserManager;
import motive.e_sportstreak.managers.UserStatsManager;
import motive.e_sportstreak.models.GameResult;
import motive.e_sportstreak.models.LocalUser;
import motive.e_sportstreak.models.MatchCard;
import motive.e_sportstreak.models.Matches;
import motive.e_sportstreak.models.PlayerInfo;
//import motive.e_sportstreak.controllers.MainActivityController;
import motive.e_sportstreak.utilities.CheckNetwork;
//import motive.e_sportstreak.views.MainActivityViews;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity
{
	public final String TAG = "Main Activity";
	public static MainActivity mainActivity;
	
	public static int number_of_picks = 0;
	public static int number_of_wins = 0;
	public static int number_of_losses = 0;
	public static int curr_longest_streak = 0;
	public static int total_win_percent = 0;
	public static int curr_streak = 0;
	
	public static int total_wins = 0;
	public static int total_losses = 0;
	public static int total_picks = 0;
	public static int lifetime_longest = 0;
	public static int lifetime_total_percent = 0;
	
	Button bHome, bStats, bLeaders, bSettings;
	Button bYes, bToday, bTomo;
	TextView current_streak_tv;
	CardUI mCardView;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CheckNetwork.isInternetAvailable(getApplicationContext());
		
		ArrayList<Matches> matchList = new ArrayList<Matches>();
		// Inflate the layout for this fragment
		MatchManager mManager = MatchManager.getMatchManager(getBaseContext());
		PlayerInfoManager pManager = PlayerInfoManager.getPlayerInfoManager(getBaseContext());
		UserManager uManager = UserManager.getUserManager(getBaseContext());
		UserManager.getLocalUser();
		String userID = LocalUser.getLocalUserID();
		UserStatsManager usManager = UserStatsManager.getUserStatsManager(getBaseContext());
		
		number_of_picks = 0;
		number_of_wins = 0;
		number_of_losses = 0;
		curr_longest_streak = 0;
		total_win_percent = 0;
		total_wins = 0;
		total_losses = 0;
		total_picks = 0;
		lifetime_longest = 0;
		lifetime_total_percent = 0;
		
//		String username = uManager.getLocalUser().getLocalUsername();
		ArrayList<GameResult> list = null;
		ArrayList<GameResult> cml = null;
		try {
			
			list = pManager.get_stats_results(userID);
			cml = pManager.get_current_month_stats(userID);
			for(GameResult g : cml)
			{
				Log.e("GAME RESULT:", g.getResult());
				if(g.getResult().equals("win"))
				{
					number_of_wins += 1;
					number_of_picks += 1;
					Log.e("GAME RESULT number_of_picks:", Integer.toString(number_of_picks));
					Log.e("GAME RESULT number_of_wins:", Integer.toString(number_of_wins));
				}
				else if(g.getResult().equals("lose"))
				{
					number_of_losses += 1;
					number_of_picks += 1;
					
					Log.e("GAME RESULT number_of_picks:", Integer.toString(number_of_picks));
					Log.e("GAME RESULT number_of_losses:", Integer.toString(number_of_losses));
				}
			}
			
			for(GameResult l : list)
			{
				if(l.getResult().equals("win"))
				{
					total_wins += 1;
					total_picks += 1;
					Log.e("GAME RESULT: total_picks:", Integer.toString(total_picks));
					Log.e("GAME RESULT: total_wins:", Integer.toString(total_wins));
				}
				else if(l.getResult().equals("lose"))
				{
					total_losses += 1;
					total_picks += 1;
					Log.e("GAME RESULT: total_picks:", Integer.toString(total_picks));
					Log.e("GAME RESULT: total_losses:", Integer.toString(total_losses));
				}
			}
			
			total_win_percent = (number_of_wins * 100) / number_of_picks;
			lifetime_total_percent = (total_wins * 100) / total_picks;
			
			curr_longest_streak = pManager.get_month_longest_streak(userID);
			lifetime_longest = pManager.get_lifetime_longest_streak(userID);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.current_streak_tv = (TextView) findViewById(R.id.current_streak_text);
		/* Setting up the button menu bar */
		this.bHome = (Button) findViewById(R.id.homeButton);
		this.bStats = (Button) findViewById(R.id.statButton);
		this.bLeaders = (Button) findViewById(R.id.leaderButton);
		this.bSettings = (Button) findViewById(R.id.settingsButton);
		final Intent statIntent = new Intent(this, MyStatsActivity.class);
		final Intent setIntent = new Intent(this, SettingsActivity.class);
		final Intent leaderIntent = new Intent(this, LeaderboardActivity.class);
		
		bStats.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(statIntent);
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
		
		this.bYes = (Button) findViewById(R.id.yesterdayButton);
		this.bToday = (Button) findViewById(R.id.todayButton);
		this.bTomo = (Button) findViewById(R.id.tomorrowButton);
		
		final Intent yesIntent = new Intent(this, PrevActivity.class);
		final Intent tomoIntent = new Intent(this, TomorrowActivity.class);
		bYes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(yesIntent);
				overridePendingTransition(0,0);
				
			}
		});
		
		bTomo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(tomoIntent);
				overridePendingTransition(0,0);

			}
		});
				
		try {
			Log.e("TESTING CURRENT STREAK IN MAIN ACTIVYT:", Integer.toString(pManager.get_current_month_streak(userID)));
			curr_streak = pManager.get_current_month_streak(userID);
			current_streak_tv.setText(Integer.toString(curr_streak));
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		mCardView = (CardUI) findViewById(R.id.cardsview2);
		mCardView.setSwipeable(false);
		
		try {
			matchList = MatchManager.fetchTodayMatches();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject playerInfo_json = null;
		try {
			playerInfo_json = pManager.get_user_picks(userID);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		MatchCard currMCard = null;
		for(Matches m : matchList)
		{
			Log.i("GAME ID IS (CURRENT TEST):", Integer.toString(m.getGameID()));
			Log.i("playerInfo DATA:", playerInfo_json.toString());

			if(m.getStarted())
			{
				if(playerInfo_json.has(Integer.toString(m.getGameID())))
				{
					try {
						Log.i("FROM JSONOBJECT TESTING CURRENT TEST:", playerInfo_json.getJSONObject(Integer.toString(m.getGameID())).toString());
						currMCard = new MatchCard(m.getGameID(), m.getTeam1Picked(), m.getTeam2Picked(), m.getTypeID(), m.getDate_time(), m.getTeam1(), m.getTeam2(),
								m.getPromptMsg(), "", "", m.getTitle(), playerInfo_json.getJSONObject(Integer.toString(m.getGameID())).getString("player_team1"),playerInfo_json.getJSONObject(Integer.toString(m.getGameID())).getString("player_team2"), true);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				else
				{
					currMCard = new MatchCard(m.getGameID(), m.getTeam1Picked(), m.getTeam2Picked(), m.getTypeID(), m.getDate_time(), m.getTeam1(), m.getTeam2(),
							m.getPromptMsg(), "", "", m.getTitle(), "0", "0", true);
				}
			}
			else if(!m.getStarted())
			{
				if(playerInfo_json.has(Integer.toString(m.getGameID())))
				{
					try {
						Log.i("PLAYER TEAM 1 ID:", playerInfo_json.getJSONObject(Integer.toString(m.getGameID())).getString("player_team1"));
						currMCard = new MatchCard(m.getGameID(), m.getTeam1Picked(), m.getTeam2Picked(), m.getTypeID(), m.getDate_time(), m.getTeam1(), m.getTeam2(),
								m.getPromptMsg(), "", "", m.getTitle(), playerInfo_json.getJSONObject(Integer.toString(m.getGameID())).getString("player_team1"),playerInfo_json.getJSONObject(Integer.toString(m.getGameID())).getString("player_team2"), false);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				else
				{
					currMCard = new MatchCard(m.getGameID(), m.getTeam1Picked(), m.getTeam2Picked(), m.getTypeID(), m.getDate_time(), m.getTeam1(), m.getTeam2(),
							m.getPromptMsg(), "", "", m.getTitle(), "0", "0", false);
				}
			}
			mCardView.addCard(currMCard);
		}
		
//		try {
//			pManager.get_current_month_stats(userID);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		mCardView.refresh();
		mainActivity = this;
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
	}
	
	@Override
	public void onStop()
	{
		super.onStop();
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
}