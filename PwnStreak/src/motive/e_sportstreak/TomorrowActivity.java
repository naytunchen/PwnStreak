package motive.e_sportstreak;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
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
import motive.e_sportstreak.models.MatchCard;
import motive.e_sportstreak.models.Matches;
//import motive.e_sportstreak.controllers.MainActivityController;
import motive.e_sportstreak.utilities.CheckNetwork;
//import motive.e_sportstreak.views.MainActivityViews;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class TomorrowActivity extends Activity
{
	public final String TAG = "Tomorrow Activity";
	public static TomorrowActivity tomoActivity;
	
	Button bHome, bStats, bLeaders, bSettings;
	Button bYes, bToday, bTomo;
	TextView current_streak;
	CardUI mCardView;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tomorrow);
		CheckNetwork.isInternetAvailable(getApplicationContext());
		
		
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
		
		final Intent yesIntent = new Intent(this, PrevActivity.class);
		final Intent todayIntent = new Intent(this, MainActivity.class);
		
		bYes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(yesIntent);
				overridePendingTransition(0,0);
				
			}
		});
		
		bToday.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(todayIntent);
				overridePendingTransition(0,0);

			}
		});
				
		ArrayList<Matches> matchList = new ArrayList<Matches>();
		// Inflate the layout for this fragment
		MatchManager mManager = MatchManager.getMatchManager(getBaseContext());
		PlayerInfoManager pManager = PlayerInfoManager.getPlayerInfoManager(getBaseContext());
		UserManager uManager = UserManager.getUserManager(getBaseContext());
		this.current_streak = (TextView) findViewById(R.id.current_streak_text);
		try {
			pManager.get_current_month_stats(UserManager.getLocalUser().getLocalUserID());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			current_streak.setText(Integer.toString(pManager.get_current_month_streak(UserManager.getLocalUser().getLocalUserID())));
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
			matchList = MatchManager.fetchTomorrowMatches();
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
			playerInfo_json = pManager.get_user_picks(UserManager.getLocalUser().getLocalUserID());
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

		mCardView.refresh();
		tomoActivity = this;
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
