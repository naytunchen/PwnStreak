package motive.e_sportstreak.models;

public class UserStats {

	private String user_id;
	private String username;
	private int curr_month_wins;
	private int curr_month_losses;
	private int curr_month_total_played;
	private int curr_streak;
	private int curr_month_longest_streak;
	private int curr_month_percent;
	private int total_wins;
	private int total_losses;
	private int total_played;
	private int total_longest_streak;
	private int total_percent;
	
	public UserStats(String user_id, String username, int curr_wins, int curr_losses, int curr_played, int streak, int curr_longest_streak, int curr_percent,
			int total_wins, int total_losses, int total_played, int total_longest_streak, int total_percent)
	{
		this.user_id = user_id;
		this.username = username;
		this.curr_month_wins = curr_wins;
		this.curr_month_losses = curr_losses;
		this.curr_month_total_played = curr_played;
		this.curr_streak = streak;
		this.curr_month_longest_streak = curr_longest_streak;
		this.curr_month_percent = curr_percent;
		this.total_wins = total_wins;
		this.total_losses = total_losses;
		this.total_played = total_played;
		this.total_longest_streak = total_longest_streak;
		this.total_percent = total_percent;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}

	public int getCurr_month_wins() {
		return curr_month_wins;
	}

	public void setCurr_month_wins(int curr_month_wins) {
		this.curr_month_wins = curr_month_wins;
	}

	public int getCurr_month_losses() {
		return curr_month_losses;
	}

	public void setCurr_month_losses(int curr_month_losses) {
		this.curr_month_losses = curr_month_losses;
	}

	public int getCurr_month_total_played() {
		return curr_month_total_played;
	}

	public void setCurr_month_total_played(int curr_month_total_played) {
		this.curr_month_total_played = curr_month_total_played;
	}

	public int getCurr_streak() {
		return curr_streak;
	}

	public void setCurr_streak(int curr_streak) {
		this.curr_streak = curr_streak;
	}

	public int getCurr_month_longest_streak() {
		return curr_month_longest_streak;
	}

	public void setCurr_month_longest_streak(int curr_month_longest_streak) {
		this.curr_month_longest_streak = curr_month_longest_streak;
	}

	public int getCurr_month_percent() {
		return curr_month_percent;
	}

	public void setCurr_month_percent(int curr_month_percent) {
		this.curr_month_percent = curr_month_percent;
	}

	public int getTotal_wins() {
		return total_wins;
	}

	public void setTotal_wins(int total_wins) {
		this.total_wins = total_wins;
	}

	public int getTotal_losses() {
		return total_losses;
	}

	public void setTotal_losses(int total_losses) {
		this.total_losses = total_losses;
	}

	public int getTotal_played() {
		return total_played;
	}

	public void setTotal_played(int total_played) {
		this.total_played = total_played;
	}

	public int getTotal_longest_streak() {
		return total_longest_streak;
	}

	public void setTotal_longest_streak(int total_longest_streak) {
		this.total_longest_streak = total_longest_streak;
	}

	public int getTotal_percent() {
		return total_percent;
	}

	public void setTotal_percent(int total_percent) {
		this.total_percent = total_percent;
	}
	
	
}
