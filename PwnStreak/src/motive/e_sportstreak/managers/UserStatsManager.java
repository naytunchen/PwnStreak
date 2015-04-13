package motive.e_sportstreak.managers;

import java.util.ArrayList;

import org.json.JSONException;

import motive.e_sportstreak.models.UserStats;
import motive.e_sportstreak.models.data.UserDAO;
import android.content.Context;

public class UserStatsManager {
	private static Context context = null;
	private static UserDAO dao = null;
	
	private UserStatsManager(Context context)
	{
		UserStatsManager.context = context;
		dao = UserDAO.getUserDAO(context);
	}
	
	public static UserStatsManager getUserStatsManager(Context context)
	{
		return new UserStatsManager(context);
	}
	
	public void store_user_stats(String user_id, int curr_wins, int curr_losses, int curr_played, int streak, int longest_streak, int curr_percent
			,int total_wins, int total_losses, int total_played, int total_longest_streak, int total_percent)
	{
		String month_wins = Integer.toString(curr_wins);
		String month_losses = Integer.toString(curr_losses);
		String month_played = Integer.toString(curr_played);
		String curr_streak = Integer.toString(streak);
		String curr_longest = Integer.toString(longest_streak);
		String month_percent = Integer.toString(curr_percent);
		String t_wins = Integer.toString(total_wins);
		String t_losses = Integer.toString(total_losses);
		String t_played = Integer.toString(total_played);
		String t_longest_streak = Integer.toString(total_longest_streak);
		String t_percent = Integer.toString(total_percent);
		
		dao.store_user_stats(user_id, month_wins, month_losses, month_played, curr_streak, curr_longest, 
				month_percent, t_wins, t_losses, t_played, t_longest_streak, t_percent);
	}
	
	public ArrayList<UserStats> get_highest_streak() throws JSONException, Exception
	{
		return dao.get_highest_streak();
	}
	
	public ArrayList<UserStats> get_longest_current_streak() throws JSONException, Exception
	{
		return dao.get_longest_current_streak();
	}
	
	public ArrayList<UserStats> get_most_wins() throws JSONException, Exception
	{
		return dao.get_most_wins();
	}
	
	public ArrayList<UserStats> get_lifetime_longest() throws JSONException, Exception
	{
		return dao.get_lifetime_longest();
	}
	
	public ArrayList<UserStats> get_lifetime_wins() throws JSONException, Exception
	{
		return dao.get_lifetime_wins();
	}
	
	public ArrayList<UserStats> get_lifetime_losses() throws JSONException, Exception
	{
		return dao.get_lifetime_losses();
	}
}
