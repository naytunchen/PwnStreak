package motive.e_sportstreak.managers;

// NOTE TO SELF: CHECK THIS BACK WITH WineInfoManager FOR EXAMPLE 

import android.app.Activity;
import motive.e_sportstreak.models.*; // Check back with WineInfoManager 

public class MainActivityManager extends Activity{
	// Might have to create other static objects
	//static DetailedMatches currDMatch;
	static Matches curMatch;
	
	public MainActivityManager()
	{
		
	}
	
	private void initMatches(String passedMatch)
	{
		//curMatch = matchManager.getMatchManager(MainActivity.mainActivity.getBaseContext()).downloadMatchByName(passedMatch);
	}
	
	private void initDetailedMatch(String passedMatch)
	{
		
	}
	
}
