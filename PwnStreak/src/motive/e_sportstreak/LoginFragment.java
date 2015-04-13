package motive.e_sportstreak;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class LoginFragment extends Fragment {
	
	private static final String TAG = "LoginFragment";
	private TextView userInfoTextView;
	private static boolean loggedin = false;
	private UiLifecycleHelper uiHelper; 
	private View view;
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};

	public LoginFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_login, container, false);
		userInfoTextView = (TextView) view.findViewById(R.id.userInfoTextView);
		LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
		authButton.setFragment(this);
		authButton.setReadPermissions(Arrays.asList("user_location", "user_birthday", "user_likes"));

		return view;
	}
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) {
	        //Intent intent = new Intent(MainActivity.class);
	    	try
	    	{
	    		userInfoTextView.setVisibility(View.VISIBLE);
	    	} catch (NullPointerException e)
	    	{
	    		Log.i(TAG, "ERROR. NULL POINTER in LOGGED IN");
	    	}
	        
	    	Request.newMeRequest(session, new Request.GraphUserCallback() {

				@Override
				public void onCompleted(GraphUser user, Response response) {
					// TODO Auto-generated method stub
					/*if( session == Session.getActiveSession())
					{*/
						if( user != null)
						{
							userInfoTextView.setText(buildUserInfoDisplay(user));
							Log.i(TAG, "User Info:" + user.toString());
						}
					//}
				}
	    		}).executeAsync();
	    	
	        Log.i(TAG, "Logged in...");
	    } else if (state.isClosed()) {
	    	try
	    	{
	    		userInfoTextView.setVisibility(View.INVISIBLE);
	    	} catch (NullPointerException e)
	    	{
	    		Log.i(TAG, "ERROR: NULL POINTER in LOGGED OUT");
	    	}

	        Log.i(TAG, "Logged out...");
	    }
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
	
	private String buildUserInfoDisplay(GraphUser user) {
	    StringBuilder userInfo = new StringBuilder("");

	    // Example: typed access (name)
	    // - no special permissions required
	    userInfo.append(String.format("Name: %s\n\n", 
	        user.getName()));

	    // Example: typed access (birthday)
	    // - requires user_birthday permission
	    userInfo.append(String.format("Birthday: %s\n\n", 
	        user.getBirthday()));

	    // Example: partially typed access, to location field,
	    // name key (location)
	    // - requires user_location permission
	    userInfo.append(String.format("Location: %s\n\n", 
	        user.getLocation().getProperty("name")));

	    // Example: access via property name (locale)
	    // - no special permissions required
	    userInfo.append(String.format("Locale: %s\n\n", 
	        user.getProperty("locale")));

	    // Example: access via key for array (languages) 
	    // - requires user_likes permission
/*	    JSONArray languages = (JSONArray)user.getProperty("languages");
	    if (languages.length() > 0) {
	        ArrayList<String> languageNames = new ArrayList<String> ();
            for (int i=0; i < languages.length(); i++) {
	            JSONObject language = languages.optJSONObject(i);
	            // Add the language name to a list. Use JSON
	            // methods to get access to the name field. 
	            languageNames.add(language.optString("name"));
	        }    


                // Iterate through the list of languages
                for (MyGraphLanguage language : graphObjectLanguages) {
                    // Add the language name to a list. Use the name
                    // getter method to get access to the name field.
                    languageNames.add(language.getName());
                } 
	        userInfo.append(String.format("Languages: %s\n\n", 
	        languageNames.toString()));
	    }*/
	    
	    GraphObjectList<MyGraphLanguage> languages = (user.cast(MyGraphUser.class)).getLanguages();
	    if(languages.size() > 0)
	    {
	    	ArrayList<String> languageNames = new ArrayList<String>();
	    	
	    	for(MyGraphLanguage language: languages)
	    	{
	    		languageNames.add(language.getName());	    	
	    	}
	    	
	    	userInfo.append(String.format("Languages: %s\n\n", languageNames.toString()));
	    		
	    }
	    return userInfo.toString();
	}
	
	private interface MyGraphLanguage extends GraphObject {
	    // Getter for the ID field
	    String getId();
	    // Getter for the Name field
	    String getName();
	}
	
	private interface MyGraphUser extends GraphUser {
	    // Create a setter to enable easy extraction of the languages field
	    GraphObjectList<MyGraphLanguage> getLanguages();
	}

}


