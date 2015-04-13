package motive.e_sportstreak.models;

public class PlayerInfo {

	private String user_id;
	private String match_id;
	private String user_name;
	private String team_picked;
	private Matches matchPlayed;
	private String date_picked;
	
	public PlayerInfo(String user_id, String match_id, String team_picked)
	{
		this.user_id = user_id;
		this.match_id = match_id;
		this.team_picked = team_picked;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMatch_id() {
		return match_id;
	}

	public void setMatch_id(String match_id) {
		this.match_id = match_id;
	}

	public String getUser_name() {
		return user_name;
	}
	
	public String getDate_Picked()
	{
		return date_picked;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getTeam_picked() {
		return team_picked;
	}

	public void setTeam_picked(String team_picked) {
		this.team_picked = team_picked;
	}

	public Matches getMatchPlayed() {
		return matchPlayed;
	}

	public void setMatchPlayed(Matches matchPlayed) {
		this.matchPlayed = matchPlayed;
	}
}
