package motive.e_sportstreak.views;

import java.util.ArrayList;
import motive.e_sportstreak.MainActivity;
import motive.e_sportstreak.R;
import motive.e_sportstreak.R.id;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;

public class MainActivityViews extends View{
	
	private ViewPager mViewPager;
	private TabPageIndicator mTitleIndicator;
	private View view;
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private CardUI mCardView;
	private CardUI mCardName;
	private SearchView mSearchView;
	private ArrayList<String> drawerNames = new ArrayList<String>();
	
	public MainActivityViews(Activity activity){
		super(activity,null);
		
		view = MainActivity.mainActivity.getWindow().getDecorView().getRootView();
		
			
	}
	
	public ViewPager getmViewPager() {
		return mViewPager;
	}



	public TabPageIndicator getTitleIndicator() {
		return mTitleIndicator;
	}
	
	public void setmViewPager(ViewPager mViewPager) {
		this.mViewPager = mViewPager;
	}



	public void setTitleIndicator(TabPageIndicator titleIndicator) {
		this.mTitleIndicator = titleIndicator;
	}
	
	
	public DrawerLayout getmDrawerLayout() {
		return mDrawerLayout;
	}

	public ListView getmDrawerList() {
		return mDrawerList;
	}

	public ActionBarDrawerToggle getmDrawerToggle() {
		return mDrawerToggle;
	}

	public CharSequence getmDrawerTitle() {
		return mDrawerTitle;
	}

	public CharSequence getmTitle() {
		return mTitle;
	}

	public CardUI getmCardView() {
		return mCardView;
	}

	public CardUI getmCardName() {
		return mCardName;
	}

	public SearchView getmSearchView() {
		return mSearchView;
	}

	public void setmDrawerLayout(DrawerLayout mDrawerLayout) {
		this.mDrawerLayout = mDrawerLayout;
	}

	public void setmDrawerList(ListView mDrawerList) {
		this.mDrawerList = mDrawerList;
	}

	public void setmDrawerToggle(ActionBarDrawerToggle mDrawerToggle) {
		this.mDrawerToggle = mDrawerToggle;
	}

	public void setmDrawerTitle(CharSequence mDrawerTitle) {
		this.mDrawerTitle = mDrawerTitle;
	}

	public void setmTitle(CharSequence mTitle) {
		this.mTitle = mTitle;
	}

	public void setmCardView(CardUI mCardView) {
		this.mCardView = mCardView;
	}

	public void setmCardName(CardUI mCardName) {
		this.mCardName = mCardName;
	}

	public void setmSearchView(SearchView mSearchView) {
		this.mSearchView = mSearchView;
	}

	public ArrayList<String> getDrawerNames() {
		return drawerNames;
	}

	public void setDrawerNames(ArrayList<String> drawerNames) {
		this.drawerNames = drawerNames;
	}

}
