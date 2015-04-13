package motive.e_sportstreak.models;

import java.io.Serializable;

public class GameResult implements Serializable{
	private String selected_team;
	private int game_id;
	private int user_id;
	private String game_date;
	private String result;
	
	public GameResult(String selected_team, int game_id, int user_id, String game_date, String result)
	{
		super();
		this.selected_team = selected_team;
		this.game_id = game_id;
		this.user_id = user_id;
		this.game_date = game_date;
		this.result = result;
	}
	
	public String getSelected_team() {
		return selected_team;
	}

	public void setSelected_team(String selected_team) {
		this.selected_team = selected_team;
	}

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getGame_date() {
		return game_date;
	}

	public void setGame_date(String game_date) {
		this.game_date = game_date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
