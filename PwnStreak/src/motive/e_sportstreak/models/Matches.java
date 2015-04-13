package motive.e_sportstreak.models;

// CHECK BACK WITH Review.java 

import java.io.Serializable;



/* This class provides getter and setter methods to
 * provide information for matchCard
 */
 
public class Matches implements Serializable {

//	 Will have to add/remove later 
	private int gameID;
	private int team1Picked;
	private int team2Picked;
	private int type_id;
	private boolean started = false;
	private String date_time;
	private String team1;
	private String team2;
	private String promptMsg;
	private String team1Percent;
	private String team2Percent;
	private String title;
	private String user_id;
	private String player_team1;
	private String player_team2;
	
	// Constructor
/*	public Matches(int gameID, int team1Picked, int team2Picked, int type_id,
			String date_time, String team1, String team2, String promptMsg,
			String team1Percent, String team2Percent)*/
	
	public Matches(int gameID, int team1Picked, int team2Picked, int type_id,
			String date_time, String team1, String team2, String title, String promptMsg, boolean started)
	{
		super();
		this.gameID = gameID;
		this.team1Picked = team1Picked;
		this.team2Picked = team2Picked;
		this.type_id = type_id;
		this.date_time = date_time;
		this.team1 = team1;
		this.team2 = team2;
//		this.team1Percent = team1Percent;
//		this.team2Percent = team2Percent;
		this.promptMsg = promptMsg;
		this.title = title;
		this.started = started;
//		this.user_id = user_id;
//		this.player_team1 = player_team1;
//		this.player_team2 = player_team2;
	}
	


	// Getters
	public int getGameID()
	{
		return this.gameID;
	}
	
//	 Might not need team1Picked and team2Picked 
	public int getTeam1Picked()
	{
		return this.team1Picked;
	}
	
	public int getTeam2Picked()
	{
		return this.team2Picked;
	}
	
	public int getTypeID()
	{
		return this.type_id;
	}
	
	public String getDate_time()
	{
		return this.date_time;
	}
	
	public String getTeam1()
	{
		return this.team1;
	}
	
	public String getTeam2()
	{
		return this.team2;
	}
	
	public String getPromptMsg()
	{
		return this.promptMsg;
	}
	
	public String getTeam1Percent()
	{
		return this.team1Percent;
	}
	
	public String getTeam2Percent()
	{
		return this.team2Percent;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	
	
	// Setters
	public void setGameID(int gameID)
	{
		this.gameID = gameID;
	}
	
	public void setTeam1Picked(int team1Picked)
	{
		this.team1Picked = team1Picked;
	}
	
	public void setTeam2Picked(int team2Picked)
	{
		this.team2Picked = team2Picked;
	}
	
	public void setTypeID(int id)
	{
		this.type_id = id;
	}
	
	public void setDateTime(String datetime)
	{
		this.date_time = datetime;
	}
	
	public void setTeam1(String team1)
	{
		this.team1 = team1;
	}
	
	public void setTeam2(String team2)
	{
		this.team2 = team2;
	}
	
	public void setPromptMsg(String promptMsg)
	{
		this.promptMsg = promptMsg;
	}
	
	public void setTeam1Percent(String team1Percent)
	{
		this.team1Percent = team1Percent;
	}
	
	public void setTeam2Percent(String team2Percent)
	{
		this.team2Percent = team2Percent;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public boolean getStarted()
	{
		return this.started;
	}
	
	public void setStarted(boolean started)
	{
		this.started = started;
	}
	
	public String getUser_id()
	{
		return this.user_id;
	}
	
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	
	public String getPlayer_team1()
	{
		return this.player_team1;
	}
	
	public void setPlayer_team1(String player_team1)
	{
		this.player_team1 = player_team1;
	}
	
	public String getPlayer_team2()
	{
		return this.player_team2;
	}
	
	public void setPlayer_team2(String player_team2)
	{
		this.player_team2 = player_team2;
	}
	
}
