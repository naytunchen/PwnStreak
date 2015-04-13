package motive.e_sportstreak.models;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import motive.e_sportstreak.MainActivity;
import motive.e_sportstreak.MyStatsActivity;
import motive.e_sportstreak.R;
import motive.e_sportstreak.Signup_login_Activity;
import motive.e_sportstreak.managers.PlayerInfoManager;
import motive.e_sportstreak.managers.UserManager;
import motive.e_sportstreak.managers.UserStatsManager;
import motive.e_sportstreak.utilities.DatabaseHandler;



// NOTE TO SELF: Check this with ReviewCard 

public class MatchCard extends Card{
//	 Check with database table to retrieve the stuffs you want 
	int gameID;
	boolean team1Picked = false;
	boolean team2Picked = false;
	int team1Win;
	int team2Win;
	int type_id;
	String date_time;
	String team1;
	String team2;
	String promptMsg;
	String team1Percent;
	String team2Percent;
	String user_id;
	String player_team1;
	String player_team2;
	//String preview; //Might not need this. This is for preview link
	String title;
	boolean passed;
	CheckBox cb1Team;
	CheckBox cb2Team;
	Context context;
	private PlayerInfoManager pManager = null;
	private UserManager uManager = null;
	private UserStatsManager usManager = null;
	private DatabaseHandler db = new DatabaseHandler(Signup_login_Activity.sign_log_activity.getApplicationContext()); 

	
	// Constructor
	public MatchCard(int gameID, int team1Win, int team2Win, int type_id, String date_time, String team1, String team2,
					String promptMsg, String team1Percent, String team2Percent, String title, String player_team1, String player_team2, boolean passed)
	{
		this.gameID = gameID;
		this.date_time = date_time;
		this.team1 = team1;
		this.team2 = team2;
//		 ** Might have to set it to false as default** 
		this.team1Win = team1Win;
		this.team2Win = team2Win;
		this.title = title;
		this.type_id = type_id;
		this.team1Percent = team1Percent;
		this.team2Percent = team2Percent;
		this.promptMsg = promptMsg;
		this.passed = passed;
//		this.user_id = user_id;
		this.player_team1 = player_team1;
		this.player_team2 = player_team2;
		
	}
	
//	 Need to work on this method 
	
	
//	 This method retrieves the card view from infoCard.xml
	@Override
	public View getCardContent(Context context)
	{
//		*//** NOTE TO SELF: R.layout.review **//*
		View view;
		this.context = context;
		this.pManager = PlayerInfoManager.getPlayerInfoManager(MainActivity.mainActivity.getBaseContext());
		this.uManager = UserManager.getUserManager(Signup_login_Activity.sign_log_activity.getApplicationContext());
		this.usManager = UserStatsManager.getUserStatsManager(MainActivity.mainActivity.getBaseContext());


		if( passed )
		{
			view = LayoutInflater.from(context).inflate(R.layout.disabled_card, null);
			final CheckBox cbTeam1 = ((CheckBox) view.findViewById(R.id.checkBoxTeam1));
			final CheckBox cbTeam2 = ((CheckBox) view.findViewById(R.id.checkBoxTeam2));

			if(this.player_team1.equals("1"))
			{
				cbTeam1.toggle();
				cbTeam2.setChecked(false);
			}
			else if(this.player_team2.equals("1"))
			{
				cbTeam2.toggle();
				cbTeam1.setChecked(false);
			}
			else
			{
				cbTeam1.setChecked(false);
				cbTeam2.setChecked(false);
			}
			cbTeam1.setEnabled(false);
			cbTeam2.setEnabled(false);

		}
		else
		{
			view = LayoutInflater.from(context).inflate(R.layout.info_card, null);
			final CheckBox cbTeam1 = ((CheckBox) view.findViewById(R.id.checkBoxTeam1));
			final CheckBox cbTeam2 = ((CheckBox) view.findViewById(R.id.checkBoxTeam2));
			

			if(this.player_team1.equals("1"))
			{
				cbTeam1.toggle();
				cbTeam2.setChecked(false);
				team1Picked = true;
				team2Picked = false;
			}
			else if(this.player_team2.equals("1"))
			{
				cbTeam2.toggle();
				cbTeam1.setChecked(false);
				team1Picked = false;
				team2Picked = true;
			}
			else
			{
				cbTeam1.setChecked(false);
				cbTeam2.setChecked(false);
			}
			
			cbTeam1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)
					{
						cbTeam2.setChecked(false);
						try {
							pManager.updateGameSelection(Integer.toString(gameID), UserManager.getLocalUser().getLocalUserID(), "1");
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						team1Picked = true;
						team2Picked = false;
					}
					else if(!isChecked && !cbTeam2.isChecked())
					{
						try {
							pManager.updateGameSelection(Integer.toString(gameID), UserManager.getLocalUser().getLocalUserID() , "");
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						cbTeam1.setChecked(false);
						cbTeam2.setChecked(false);
						team1Picked = false;
						team2Picked = false;
					}
					cbTeam1.setEnabled(true);

				}
				
			});
			
			cbTeam2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){



				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)
					{
						cbTeam1.setChecked(false);
						try {
							pManager.updateGameSelection(Integer.toString(gameID), UserManager.getLocalUser().getLocalUserID(), "2");
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						team2Picked = true;
						team1Picked = false;
					}

					else if(!isChecked && !cbTeam1.isChecked())
					{
						try {
							pManager.updateGameSelection(Integer.toString(gameID), UserManager.getLocalUser().getLocalUserID() , "");
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						cbTeam1.setChecked(false);
						cbTeam2.setChecked(false);
						team1Picked = false;
						team2Picked = false;
					}
					cbTeam2.setEnabled(true);
				}
				
			});

		}
		
//		 Check if the passed in Winning Chance of both Team 1 and Team 2, if there's no information available,
//		 * display "N/A" on respectively team.
		 
		if(team1Percent.contains("0.0%"))   // Might have to change this 0.0% to something else
		{
			((TextView) view.findViewById(R.id.team1Percent)).setText("N/A");
		}
		
		if(team2Percent.contains("0.0%"))  // Might have to change this 0.0% to something else
		{
			((TextView) view.findViewById(R.id.team2Percent)).setText("N/A");
		}
		
		((TextView) view.findViewById(R.id.team1)).setText(team1);
		((TextView) view.findViewById(R.id.team2)).setText(team2);
		((TextView) view.findViewById(R.id.prompt)).setText(promptMsg);
		((TextView) view.findViewById(R.id.time)).setText(date_time);
		((TextView) view.findViewById(R.id.gametype)).setText(title);
		
		usManager.store_user_stats(UserManager.getLocalUser().getLocalUserID(), 
				MainActivity.number_of_wins,
				MainActivity.number_of_losses, 
				MainActivity.number_of_picks, 
				MainActivity.curr_streak,
				MainActivity.curr_longest_streak,
				MainActivity.total_win_percent,
				MainActivity.total_wins,
				MainActivity.total_losses,
				MainActivity.total_picks,
				MainActivity.lifetime_longest,
				MainActivity.lifetime_total_percent);

		return view;
	}

	@Override
	public boolean convert(View convertCardView) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isTeam1Picked()
	{
		return team1Picked;
	}
	
	public boolean isTeam2Picked()
	{
		return team2Picked;
	}
	
	public CheckBox getTeam1CB()
	{
		return this.cb1Team;
	}
	
	public CheckBox getTeam2CB()
	{
		return this.cb2Team;
	}
	
}
