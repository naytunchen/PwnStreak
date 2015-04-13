package motive.e_sportstreak.managers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import motive.e_sportstreak.models.GameResult;
import motive.e_sportstreak.models.PlayerInfo;
import motive.e_sportstreak.models.data.PlayerInfoDAO;
import android.content.Context;
import android.util.Log;

public class PlayerInfoManager {
	
	private final static String EMPTY = "";
	private final String WIN = "win";
	private static Context context = null;
	private static PlayerInfoDAO playerdao = null;
		
	private PlayerInfoManager(Context context)
	{
		PlayerInfoManager.context = context;
		playerdao = PlayerInfoDAO.getPlayerInfoDAO(context);
	}
	
	public static PlayerInfoManager getPlayerInfoManager(Context context)
	{
		return new PlayerInfoManager(context);
	}
	
	public PlayerInfo updateGameSelection(String game_id, String user_id, String teamPicked) throws ClientProtocolException, JSONException, IOException
	{
		return playerdao.update_game_picked(game_id, user_id, teamPicked);
	}
	
	public JSONObject get_user_picks(String user_id) throws UnsupportedEncodingException
	{
		return playerdao.get_user_picked_games(user_id);
	}
	public ArrayList<GameResult> get_current_month_stats(String user_id) throws JSONException, Exception
	{
		return playerdao.download_current_month_list(user_id);
	}
	
	
	public ArrayList<GameResult> get_stats_results(String user_id) throws JSONException, Exception
	{
		return playerdao.download_stats_results(user_id);
	}
	
	public int get_user_total_wins()
	{
		return playerdao.get_total_wins();
	}
	
	public int get_user_total_losses()
	{
		return playerdao.get_total_losses();
	}

	public int get_user_total_picked()
	{
		return playerdao.get_total_picked();
	}
	
	public int get_user_total_win_percent()
	{
		return playerdao.get_total_win_percent();
	}
	
	public int get_lifetime_longest_streak(String user_id) throws JSONException, Exception
	{
		
		ArrayList<GameResult> allGame = playerdao.download_stats_results(user_id);
		int longest_streak = 0;
		int streak = 0;
		for(int i = 0; i < allGame.size(); i++)
		{
			if(allGame.get(i).getResult().equals(WIN))
			{
				streak += 1;
			}
			else
			{
				if(streak > longest_streak)
				{
					longest_streak = streak;
				}
				streak = 1;
			}
			
		}
		return longest_streak;
	}
	
	public int get_month_longest_streak(String user_id) throws JSONException, Exception
	{
		
		ArrayList<GameResult> currentMonthList = playerdao.download_current_month_list(user_id);
		int longest_streak = 0;
		int streak = 0;
		for(int i = 0; i < currentMonthList.size(); i++)
		{
			if(currentMonthList.get(i).getResult().equals(WIN))
			{
				streak += 1;
			}
			else
			{
				if(streak > longest_streak)
				{
					longest_streak = streak;
				}
				streak = 1;
			}
			
		}
		return longest_streak;
	}
	
	public int get_current_month_streak(String user_id) throws JSONException, Exception
	{
		ArrayList<GameResult> currentMonthList = playerdao.download_current_month_list(user_id);
		int current_streak = 0;
		for(int i = 0; i < currentMonthList.size(); i++)
		{
			Log.e("CHECKING WIN/LOSSE IN PLAYERDAO", currentMonthList.get(i).getResult());
			if(currentMonthList.get(i).getResult().equals(WIN))
			{
				current_streak++;
				Log.e("CHECKING CURRENT_STREAK++ IN PLAYERDAO", Integer.toString(current_streak));
			}
			else
			{
				current_streak = 0;
				Log.e("CHECKING CURRENT_STREAK=0 IN PLAYERDAO", Integer.toString(current_streak));
			}
		}
		Log.e("CHECKING RESULTING CURRENT_STREAK IN PLAYERDAO", Integer.toString(current_streak));
		return current_streak;
	}
}