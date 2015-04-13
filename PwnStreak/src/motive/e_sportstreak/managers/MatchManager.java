package motive.e_sportstreak.managers;


// check back with WineManager.java 
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;

import android.content.Context;

import motive.e_sportstreak.models.Matches;
import motive.e_sportstreak.models.data.MatchDAO;


public class MatchManager {
	private static Context context = null;
	private static MatchDAO dao = null;
	private final static boolean NOT_STARTED = false;
	private final static boolean STARTED = true;
	
	public MatchManager(Context context)
	{
		MatchManager.context = context;
		MatchManager.dao = MatchDAO.getMatchDAO(context);
	}
	
	public static MatchManager getMatchManager(Context context)
	{
		return new MatchManager(context);
	}
	
	
//	 Check back with WineManager for "public DetailedWine downloadDetailedWine Method" 
	
	public static ArrayList<Matches> fetchAllMatches() throws IOException, JSONException, Exception
	{
		return dao.downloadMatches();
	}
	
	public static ArrayList<Matches> fetchTodayMatches() throws IOException, JSONException, Exception
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		Date todayDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

		
		ArrayList<Matches> todayMatches = dao.downloadMatches();
		ArrayList<Matches> newList = new ArrayList<Matches>();
		for(Matches m : todayMatches)
		{
			Date matchDate = new SimpleDateFormat("yyyy-MM-dd").parse(m.getDate_time());
			if(todayDate.equals(matchDate))
			{
				Date currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
				Date matchTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(m.getDate_time());

				if(currentTime.compareTo(matchTime) > 0)
				{
					newList.add(new Matches(m.getGameID(), 0, 0, m.getTypeID(), m.getDate_time(), m.getTeam1(), m.getTeam2(), m.getTitle(), m.getPromptMsg(), STARTED));
				}
				else
				{
					newList.add(new Matches(m.getGameID(), 0, 0, m.getTypeID(), m.getDate_time(), m.getTeam1(), m.getTeam2(), m.getTitle(), m.getPromptMsg(),NOT_STARTED));
				}

			}

		}
		
		return newList;
	}
	
	
	public static ArrayList<Matches> fetchPrevMatches() throws IOException, JSONException, Exception
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterdayDate = dateFormat.format(cal.getTime());
		
		ArrayList<Matches> prevMatches = dao.downloadMatches();
		ArrayList<Matches> newList = new ArrayList<Matches>();
		for(Matches m : prevMatches)
		{
			Date matchDate = dateFormat.parse(m.getDate_time());
			if(yesterdayDate.equals(dateFormat.format(matchDate)))
			{
				newList.add(new Matches(m.getGameID(), 0, 0, m.getTypeID(), m.getDate_time(), m.getTeam1(), m.getTeam2(), m.getTitle(), m.getPromptMsg(), STARTED));
			}
		}
		return newList;
	}
	
	
	public static ArrayList<Matches> fetchTomorrowMatches() throws IOException, JSONException, Exception
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		String tomorrowDate = dateFormat.format(cal.getTime());
		
		ArrayList<Matches> tmrMatches = dao.downloadMatches();
		ArrayList<Matches> newList = new ArrayList<Matches>();
		for(Matches m : tmrMatches)
		{
			Date matchDate = dateFormat.parse(m.getDate_time());
			if(tomorrowDate.equals(dateFormat.format(matchDate)))
			{
				newList.add(new Matches(m.getGameID(), 0, 0, m.getTypeID(), m.getDate_time(), m.getTeam1(), m.getTeam2(), m.getTitle(), m.getPromptMsg(), NOT_STARTED));
			}
		}
		return newList;
	}

	
}
