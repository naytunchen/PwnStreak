/*package motive.e_sportstreak.managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

 SELF NOTE: CHECK BACK WITH FragmentCollection Manager
 * Might have to add more files to here
 
import motive.e_sportstreak.fragments.yFrag;
import motive.e_sportstreak.fragments.todayFrag;
import motive.e_sportstreak.fragments.tomorrowFrag;

public class FragmentCollectionManager extends FragmentPagerAdapter{
	public FragmentCollectionManager(FragmentManager fm)
	{
		super(fm);
	}

	@Override
	public Fragment getItem(int i)
	{
		Fragment fragment = null;
		switch(i)
		{
			// Yesterday Matches
			case 0: fragment = new yFrag(); break;
			
			// Today Matches
			case 1: fragment = new todayFrag(); break;
			
			// Tomorrow Matches
			case 2: fragment = new tomorrowFrag(); break;
		}
		
		return fragment;
	}
	
	@Override
	public int getCount()
	{
		return 3;
	}
	
	
	 Needs to check the string must be the date and day of yesterday, today, and tomorrow of current date. 
	@Override
	public CharSequence getPageTitle(int position)
	{
		String tabTitle = null;
		
		switch(position)
		{
			case 0: tabTitle = "Yesterday"; break;
		
			case 1: tabTitle = "Today"; break;
		
			case 2: tabTitle = "Tomorrow"; break;
		}
		
		return tabTitle;
	}
}
*/