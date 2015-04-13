package motive.e_sportstreak.models.data;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import motive.e_sportstreak.models.GameResult;
import motive.e_sportstreak.models.PlayerInfo;
import motive.e_sportstreak.utilities.JSONParser;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;


public class PlayerInfoDAO {

	private JSONParser jsonParser;
	private static final String TEAM1_FLAG = "team1";
	private static final String TEAM2_FLAG = "team2";
	private static String URL = "http://pwnstreak.com/api/game/select";
	private static Context context = null;
	private ArrayList<GameResult> currentMonthList;
	private JSONArray resultJSON = null;
	private int total_wins = 0;
	private int total_losses = 0;
	private int total_game_played = 0;
	private int total_win_percent = 0;
	private int total_cur_month_win=0;
	private int total_cur_month_loss=0;
	private int total_cur_month_played=0;
	private int total_cur_month_percent=0;

	
	private PlayerInfoDAO(Context context)
	{
		PlayerInfoDAO.context = context;
		jsonParser = new JSONParser();
	}
	
	public static PlayerInfoDAO getPlayerInfoDAO(Context context)
	{
		return new PlayerInfoDAO(context);
	}
	
	
	/* CALLED IN PLAYERINFOMANAGER.JAVA */
	public PlayerInfo update_game_picked(String game_id, String user_id, String team_picked) throws JSONException, ClientProtocolException, IOException
	{
		PlayerInfo info = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(URL);
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("game_id", game_id));
		pairs.add(new BasicNameValuePair("user_id", user_id));

		if(team_picked.equals("1"))
		{
			pairs.add(new BasicNameValuePair("team", TEAM1_FLAG));
		}

		else if(team_picked.equals("2"))
		{
			pairs.add(new BasicNameValuePair("team", TEAM2_FLAG));
		}
		else
		{
			pairs.add(new BasicNameValuePair("team", null));
		}
		
		post.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
		JSONObject json = jsonParser.getJSONFromUrl(URL, pairs);
		HttpResponse response = httpclient.execute(post);
		
		String resString = EntityUtils.toString(response.getEntity());
		Log.i("PlayerInfoDAO.java: update_game_picked(): HTTP RESPONSE: ", resString);
		return new PlayerInfo(user_id, game_id, team_picked);
	}

	
	/*NOT CALLLED */
	public void update_user_record(String user_id, int longest, int current) throws ClientProtocolException, IOException
	{
		String targetURL="http://pwnstreak.com/api/user/update_user_record";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(targetURL);
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("user_id", user_id));
		pairs.add(new BasicNameValuePair("longest_streak", Integer.toString(longest)));
		pairs.add(new BasicNameValuePair("current_streak", Integer.toString(current)));
		pairs.add(new BasicNameValuePair("streak_of_month", Integer.toString(3)));
		pairs.add(new BasicNameValuePair("wins_of_month", Integer.toString(this.total_cur_month_win)));
		pairs.add(new BasicNameValuePair("losses_of_month", Integer.toString(this.total_cur_month_loss)));
		pairs.add(new BasicNameValuePair("total_wins", Integer.toString(get_total_wins())));
		pairs.add(new BasicNameValuePair("total_losses", Integer.toString(get_total_losses())));
		pairs.add(new BasicNameValuePair("per_of_month", Integer.toString(this.total_cur_month_percent)));
		pairs.add(new BasicNameValuePair("total_percent", Integer.toString(get_total_win_percent())));
		
		post.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
//		JSONObject json = jsonParser.getJSONFromUrl(URL, pairs);
		HttpResponse response = httpclient.execute(post);
		
//		String resString = EntityUtils.toString(response.getEntity());
//		Log.i("PlayerInfoDAO.java: UPDATE_USER_RECORD(): HTTP RESPONSE: ", resString);
	}
	
	/* CALLED FROM PlayerInfoManager.java: get_user_picks(String user_id) */
	public JSONObject get_user_picked_games(String user_id) throws UnsupportedEncodingException
	{
		String url = "http://pwnstreak.com/api/user/get_selected_games";
		url += "?user_id=" + URLEncoder.encode(user_id, "UTF-8");
		
		
		ArrayList<PlayerInfo> player_info_list = new ArrayList<PlayerInfo>();
		String data = "ERROR";
        try 
        {
           HttpClient client = new DefaultHttpClient();
           URI website = new URI(url);
           HttpGet request = new HttpGet();
           request.setURI(website);
           HttpResponse response = client.execute(request);
           data = EntityUtils.toString(response.getEntity());
           JSONObject json = readJsonFromUrl(url);

           return json.getJSONObject("message");
        } catch(Exception e)
        {
        	Log.e("ERROR FROM PlayerInfoDAO.get_user_picked_games()", data);
        }
        return null;
    }
	
	/* CALLED FROM PlayerInfoManager.java: set_user_total_wins_and_losses(String user_id) 
	 * also used to compute PICK History in MyStatsActivity.java */
	public ArrayList<GameResult> download_current_month_list(String user_id) throws JSONException, Exception
	{
		String url = "http://pwnstreak.com/api/user/get_user_game_results";
		url += "?user_id=" + URLEncoder.encode(user_id, "UTF-8");
		
		ArrayList<GameResult> game_result_list = new ArrayList<GameResult>();
		currentMonthList = new ArrayList<GameResult>();
		HttpClient client = new DefaultHttpClient();
        URI website = new URI(url);
        HttpGet request = new HttpGet();
        request.setURI(website);                                                                                                                               
        HttpResponse response = client.execute(request);
        String data = EntityUtils.toString(response.getEntity());
        JSONObject json = readJsonFromUrl(url);
        resultJSON = json.getJSONArray("message");
        
        for(int i = 0; i < resultJSON.length(); i++)
        {
        	JSONObject currResult = resultJSON.getJSONObject(i);
        	
        	if(isCurrentMonth(currResult.getString("game_date")))
        	{
        		GameResult newResult = new GameResult(
        			currResult.getString("selected_team"),
        			currResult.getInt("game_id"),
        			currResult.getInt("user_id"),
        			strip_off_time(currResult.getString("game_date")),
        			currResult.getString("result"));
        		
            	game_result_list.add(newResult);
        	}
        }        
        return game_result_list;
        
	}
	
	/* CALLED FROM PlayerInfoManager: get_stats_results(String user_id) */
	public ArrayList<GameResult> download_stats_results(String user_id) throws JSONException, Exception
	{
		String url = "http://pwnstreak.com/api/user/get_user_game_results";
		url += "?user_id=" + URLEncoder.encode(user_id, "UTF-8");
		
		ArrayList<GameResult> game_result_list = new ArrayList<GameResult>();
		HttpClient client = new DefaultHttpClient();
        URI website = new URI(url);
        HttpGet request = new HttpGet();
        request.setURI(website);                                                                                                                               
        HttpResponse response = client.execute(request);
        String data = EntityUtils.toString(response.getEntity());
        JSONObject json = readJsonFromUrl(url);
        resultJSON = json.getJSONArray("message");
        
        for(int i = 0; i < resultJSON.length(); i++)
        {
        	JSONObject currResult = resultJSON.getJSONObject(i);
        	GameResult newResult = new GameResult(
        			currResult.getString("selected_team"),
        			currResult.getInt("game_id"),
        			currResult.getInt("user_id"),
        			strip_off_time(currResult.getString("game_date")),
        			currResult.getString("result"));

        	game_result_list.add(newResult);
        	
        }       
        return game_result_list;
        
	}
	
	public ArrayList<GameResult> get_current_month_list()
	{
		return this.currentMonthList;
	}
	public int get_total_win_percent()
	{
		return this.total_win_percent;
	}
	
	public int get_total_picked()
	{
		return this.total_game_played;
	}
	public int get_total_wins()
	{
		return this.total_wins;
	}
	
	public int get_total_losses()
	{
		return this.total_losses;
	}
	
	
	public static JSONObject readJsonFromUrl(String url) throws Exception, IOException, JSONException
	{
		InputStream is = new URL(url).openStream();
		try
		{
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally
		{
			is.close();
		}
	}
	
	private static String readAll(Reader rd) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		int cp;
		while((cp = rd.read()) != -1)
		{
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	
	public String strip_off_time(String date) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd");
		Date stripped = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		return df.format(stripped);
	}
	
	public boolean isCurrentMonth(String date) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Date checkDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		Date todayDate = new Date();
		String thisMonth = df.format(todayDate);
		String toCheck = df.format(checkDate);
		Log.i("TESTING DATE DATE DATE:", thisMonth);
		Log.i("TESTING DATE DATE DATE:", toCheck);
		if(thisMonth.equals(toCheck))
		{
			return true;
		}
		return false;
	}
}
