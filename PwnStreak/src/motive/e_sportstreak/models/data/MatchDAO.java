package motive.e_sportstreak.models.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import motive.e_sportstreak.models.Matches;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class MatchDAO {
	
	public static final String TAG_GAMES ="games";
	public static final String TAG_ID ="game_id";
	public static final String TAG_DATE ="date";
	public static final String TAG_TEAM1 ="team1";
	public static final String TAG_TEAM2 ="team2";
	public static final String TAG_TEAM1_PICKED = "team1_win";
	public static final String TAG_TEAM2_PICKED = "team2_win";
	public static final String TAG_TITLE ="title";
	public static final String TAG_PROMPT = "prop";
	public static final String TAG_TYPE_ID ="type_id";
	public static final String TAG_USER_ID ="user_id";
	public static final String TAG_PLAYER_TEAM1 ="player_team1";
	public static final String TAG_PLAYER_TEAM2 ="player_team2";

	private JSONArray gameJSON = null;
//	private static SQLiteDatabase matchDatabase = null;
	private static Context context = null;
	private JSONArray matchJSON = null;
	
	private MatchDAO(Context context)
	{
		MatchDAO.context = context;
//		open();
	}

	
	public static MatchDAO getMatchDAO(Context context)
	{
		return new MatchDAO(context);
	}
	
	public ArrayList<Matches> downloadMatches() throws IOException, JSONException, Exception
	{
		ArrayList<Matches> matchList = new ArrayList<Matches>();
		JSONObject json = readJsonFromUrl("http://pwnstreak.com/api/game/list");
		gameJSON = json.getJSONArray("games");
		JSONObject tempMatch = gameJSON.getJSONObject(0);
		Log.i("JSON STRING: ", tempMatch.getString(TAG_TEAM1));

		
		
		for(int i = 0; i < gameJSON.length(); i++)
		{
			JSONObject currMatch = gameJSON.getJSONObject(i);
			Log.i("JSON STRING: ", currMatch.getString(TAG_TEAM1));
			Matches newMatch = new Matches(
					currMatch.getInt(TAG_ID), 0, 0,
//					currMatch.getString(TAG_TEAM1_PICKED),
//					currMatch.getString(TAG_TEAM2_PICKED),
					currMatch.getInt(TAG_TYPE_ID),
					currMatch.getString(TAG_DATE),
					currMatch.getString(TAG_TEAM1),
					currMatch.getString(TAG_TEAM2),
					currMatch.getString(TAG_TITLE),
					currMatch.getString(TAG_PROMPT),
//					currMatch.getString(TAG_USER_ID),
//					currMatch.getString(TAG_PLAYER_TEAM1),
//					currMatch.getString(TAG_PLAYER_TEAM2),
					false);
			if(!("".equals(newMatch.getTeam1())))
				Log.e("MatchDAO.java: ", "Match addition succeeded!!");
		    matchList.add(newMatch);			
		}
		
		Log.i("CURRENT MATCH TEST: ", json.toString());
		Log.i("JSON STRING: ", json.get("games").toString());
		
		return matchList;
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

}