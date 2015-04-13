package motive.e_sportstreak.models.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import motive.e_sportstreak.Signup_login_Activity;
import motive.e_sportstreak.models.User;
import motive.e_sportstreak.models.UserStats;
import motive.e_sportstreak.utilities.DatabaseHandler;
import motive.e_sportstreak.utilities.JSONParser;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class UserDAO {
	
	private JSONParser jsonParser;
	private static String loginURL = "http://pwnstreak.com/api/user/login";
	private static String registerURL = "http://pwnstreak.com/api/user/register";

	
	JSONArray stuffs = null;


	private static Context context = null;
	private UserDAO(Context context) {
		UserDAO.context = context;
		jsonParser = new JSONParser();
	}
	
	public static UserDAO getUserDAO(Context context) {
		return new UserDAO(context);
	}
	
	
	public User createUserOnServer(String first_name, String last_name, String username, String email, String password) throws UnsupportedEncodingException
	{
		String tempString = "";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(registerURL);
			try
			{
			
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new BasicNameValuePair("username", username));
				pairs.add(new BasicNameValuePair("password", password));
				pairs.add(new BasicNameValuePair("email", email));
				pairs.add(new BasicNameValuePair("first_name", first_name));
				pairs.add(new BasicNameValuePair("last_name", last_name));
				post.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
				JSONObject json = jsonParser.getJSONFromUrl(registerURL, pairs);
		        try {
					if("error".equals(json.getString("status")))
					{
						return null;
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				HttpResponse response = client.execute(post);
				
				String resString = EntityUtils.toString(response.getEntity());	
				tempString = resString;
				Log.i("UserDAO.java: createOnServer(): HTTP RESPONSE: ", resString);
			} catch (ClientProtocolException e){
				
			} catch(IOException e)
			{
				
			}
			
			return (new User (first_name, last_name, username, email, password));
	}
	
	public User loginServer (String username, String password)
	{
		String tempString = "";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(loginURL);
		JSONObject json_user = null;
		User tempUser = null;
		
		/* Checking if the username and password string is null or empty, if they are, return null */
		if( username == null || "".equals(username) || password == null || "".equals(password))
		{
			return null;
		}
		
		/* Otherwise, continue posting the user information to the server, and save it to database */
		try
		{
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("username", username));
	        nameValuePairs.add(new BasicNameValuePair("password", password));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
	        JSONObject json = jsonParser.getJSONFromUrl(loginURL, nameValuePairs);
	        try {
				if("error".equals(json.getString("status")))
				{
					return null;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        
	        DatabaseHandler db = new DatabaseHandler(Signup_login_Activity.sign_log_activity.getApplicationContext());   
	        try {
				json_user = json.getJSONObject("user_info");
				
				//Clear all previous data in database
				logoutUser(Signup_login_Activity.sign_log_activity.getApplicationContext());
				db.addUser(json_user.getString("id"), json_user.getString("username"), json_user.getString("first_name"), json_user.getString("last_name"), json_user.getString("email"), json_user.getString("created_on"));
				/*Map<String, String> tempMap = db.getUserDetails();
				for (Map.Entry entry : tempMap.entrySet()) {
				    Log.i("testing LOCAL DATABASE", entry.getKey() + ", " + entry.getValue());
				}*/
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			
		}catch (IOException e) {
			
		}
		
		try {
			
			Log.i("UserDAO.java: tempUser TRY: username: ", json_user.getString("username"));
			tempUser = new User(json_user.getString("first_name"), json_user.getString("last_name"), json_user.getString("username"), json_user.getString("email"), json_user.getString("password"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tempUser;
	
	}
	
	
	public void store_user_stats(String user_id, String curr_wins, String curr_losses, String curr_played, String streak, String longest_streak, String curr_percent
			,String total_wins, String total_losses, String total_played, String total_longest_streak, String total_percent)
	{
		String url = "http://pwnstreak.com/api/user/store_user_stats";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		
		/* Otherwise, continue posting the user information to the server, and save it to database */
		try
		{
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
	        nameValuePairs.add(new BasicNameValuePair("curr_month_wins", curr_wins));
	        nameValuePairs.add(new BasicNameValuePair("curr_month_losses", curr_losses));
	        nameValuePairs.add(new BasicNameValuePair("curr_month_total_played", curr_played));
	        nameValuePairs.add(new BasicNameValuePair("curr_streak", streak));
	        nameValuePairs.add(new BasicNameValuePair("curr_month_longest_streak", longest_streak));
	        nameValuePairs.add(new BasicNameValuePair("curr_month_percent", curr_percent));
	        nameValuePairs.add(new BasicNameValuePair("total_wins", total_wins));
	        nameValuePairs.add(new BasicNameValuePair("total_losses", total_losses));
	        nameValuePairs.add(new BasicNameValuePair("total_played", total_played));
	        nameValuePairs.add(new BasicNameValuePair("total_longest_streak", total_longest_streak));
	        nameValuePairs.add(new BasicNameValuePair("total_percent", total_percent));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
	        JSONObject json = jsonParser.getJSONFromUrl(url, nameValuePairs);
	        try {
				if("error".equals(json.getString("status")))
				{
					Log.e("ERROR IN store_user_stats", json.toString());
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}catch (IOException e) {
			
		}
	
	}
	
	public ArrayList<UserStats> get_highest_streak() throws JSONException, Exception
	{
		ArrayList<UserStats> highest_streak_list = new ArrayList<UserStats>();
		JSONArray resultJSON;
		String url = "http://pwnstreak.com/api/user/get_highest_current_streak";
		HttpClient client = new DefaultHttpClient();
        URI website = new URI(url);
        HttpGet request = new HttpGet();
        request.setURI(website);                                                                                                                               
        HttpResponse response = client.execute(request);
        String data = EntityUtils.toString(response.getEntity());
        JSONObject json = readJsonFromUrl(url);
        resultJSON = json.getJSONArray("users");
        
        for(int i = 0; i < resultJSON.length(); i++)
        {
        	JSONObject currResult = resultJSON.getJSONObject(i);
        	
        	UserStats user = new UserStats(
        			currResult.getString("user_id"),
        			currResult.getString("username"),
        			currResult.getInt("curr_wins"),
        			currResult.getInt("curr_losses"),
        			currResult.getInt("curr_played"),
        			currResult.getInt("curr_streak"),
        			currResult.getInt("curr_longest"),
        			currResult.getInt("curr_percent"),
        			currResult.getInt("total_wins"),
        			currResult.getInt("total_losses"),
        			currResult.getInt("total_played"),
        			currResult.getInt("total_longest"),
        			currResult.getInt("total_percent"));
        	
        	
        	highest_streak_list.add(user);
        }                
		return highest_streak_list;
	}
	
	public ArrayList<UserStats> get_most_wins() throws JSONException, Exception
	{
		ArrayList<UserStats> most_wins = new ArrayList<UserStats>();
		JSONArray resultJSON;
		String url = "http://pwnstreak.com/api/user/get_most_wins";
		HttpClient client = new DefaultHttpClient();
        URI website = new URI(url);
        HttpGet request = new HttpGet();
        request.setURI(website);                                                                                                                               
        HttpResponse response = client.execute(request);
        String data = EntityUtils.toString(response.getEntity());
        JSONObject json = readJsonFromUrl(url);
        resultJSON = json.getJSONArray("users");
        
        for(int i = 0; i < resultJSON.length(); i++)
        {
        	JSONObject currResult = resultJSON.getJSONObject(i);
        	
        	UserStats user = new UserStats(
        			currResult.getString("user_id"),
        			currResult.getString("username"),
        			currResult.getInt("curr_wins"),
        			currResult.getInt("curr_losses"),
        			currResult.getInt("curr_played"),
        			currResult.getInt("curr_streak"),
        			currResult.getInt("curr_longest"),
        			currResult.getInt("curr_percent"),
        			currResult.getInt("total_wins"),
        			currResult.getInt("total_losses"),
        			currResult.getInt("total_played"),
        			currResult.getInt("total_longest"),
        			currResult.getInt("total_percent"));
        	
        	
        	most_wins.add(user);
        }                
		return most_wins;
	}
	
	public ArrayList<UserStats> get_lifetime_longest() throws JSONException, Exception
	{
		ArrayList<UserStats> lifetime_longest = new ArrayList<UserStats>();
		JSONArray resultJSON;
		String url = "http://pwnstreak.com/api/user/get_lifetime_longest";
		HttpClient client = new DefaultHttpClient();
        URI website = new URI(url);
        HttpGet request = new HttpGet();
        request.setURI(website);                                                                                                                               
        HttpResponse response = client.execute(request);
        String data = EntityUtils.toString(response.getEntity());
        JSONObject json = readJsonFromUrl(url);
        resultJSON = json.getJSONArray("users");
        
        for(int i = 0; i < resultJSON.length(); i++)
        {
        	JSONObject currResult = resultJSON.getJSONObject(i);
        	
        	UserStats user = new UserStats(
        			currResult.getString("user_id"),
        			currResult.getString("username"),
        			currResult.getInt("curr_wins"),
        			currResult.getInt("curr_losses"),
        			currResult.getInt("curr_played"),
        			currResult.getInt("curr_streak"),
        			currResult.getInt("curr_longest"),
        			currResult.getInt("curr_percent"),
        			currResult.getInt("total_wins"),
        			currResult.getInt("total_losses"),
        			currResult.getInt("total_played"),
        			currResult.getInt("total_longest"),
        			currResult.getInt("total_percent"));
        	
        	
        	lifetime_longest.add(user);
        }                
		return lifetime_longest;
	}
	
	
	public ArrayList<UserStats> get_lifetime_wins() throws JSONException, Exception
	{
		ArrayList<UserStats> lifetime_wins = new ArrayList<UserStats>();
		JSONArray resultJSON;
		String url = "http://pwnstreak.com/api/user/get_lifetime_wins";
		HttpClient client = new DefaultHttpClient();
        URI website = new URI(url);
        HttpGet request = new HttpGet();
        request.setURI(website);                                                                                                                               
        HttpResponse response = client.execute(request);
        String data = EntityUtils.toString(response.getEntity());
        JSONObject json = readJsonFromUrl(url);
        resultJSON = json.getJSONArray("users");
        
        for(int i = 0; i < resultJSON.length(); i++)
        {
        	JSONObject currResult = resultJSON.getJSONObject(i);
        	
        	UserStats user = new UserStats(
        			currResult.getString("user_id"),
        			currResult.getString("username"),
        			currResult.getInt("curr_wins"),
        			currResult.getInt("curr_losses"),
        			currResult.getInt("curr_played"),
        			currResult.getInt("curr_streak"),
        			currResult.getInt("curr_longest"),
        			currResult.getInt("curr_percent"),
        			currResult.getInt("total_wins"),
        			currResult.getInt("total_losses"),
        			currResult.getInt("total_played"),
        			currResult.getInt("total_longest"),
        			currResult.getInt("total_percent"));
        	
        	
        	lifetime_wins.add(user);
        }                
		return lifetime_wins;
	}
	
	
	public ArrayList<UserStats> get_lifetime_losses() throws JSONException, Exception
	{
		ArrayList<UserStats> lifetime_losses = new ArrayList<UserStats>();
		JSONArray resultJSON;
		String url = "http://pwnstreak.com/api/user/get_lifetime_losses";
		HttpClient client = new DefaultHttpClient();
        URI website = new URI(url);
        HttpGet request = new HttpGet();
        request.setURI(website);                                                                                                                               
        HttpResponse response = client.execute(request);
        String data = EntityUtils.toString(response.getEntity());
        JSONObject json = readJsonFromUrl(url);
        resultJSON = json.getJSONArray("users");
        
        for(int i = 0; i < resultJSON.length(); i++)
        {
        	JSONObject currResult = resultJSON.getJSONObject(i);
        	
        	UserStats user = new UserStats(
        			currResult.getString("user_id"),
        			currResult.getString("username"),
        			currResult.getInt("curr_wins"),
        			currResult.getInt("curr_losses"),
        			currResult.getInt("curr_played"),
        			currResult.getInt("curr_streak"),
        			currResult.getInt("curr_longest"),
        			currResult.getInt("curr_percent"),
        			currResult.getInt("total_wins"),
        			currResult.getInt("total_losses"),
        			currResult.getInt("total_played"),
        			currResult.getInt("total_longest"),
        			currResult.getInt("total_percent"));
        	
        	
        	lifetime_losses.add(user);
        }                
		return lifetime_losses;
	}
	
	
	
	public ArrayList<UserStats> get_longest_current_streak() throws JSONException, Exception
	{
		ArrayList<UserStats> longest_streak_list = new ArrayList<UserStats>();
		JSONArray resultJSON;
		String url = "http://pwnstreak.com/api/user/get_longest_current_streak";
		HttpClient client = new DefaultHttpClient();
        URI website = new URI(url);
        HttpGet request = new HttpGet();
        request.setURI(website);                                                                                                                               
        HttpResponse response = client.execute(request);
        String data = EntityUtils.toString(response.getEntity());
        JSONObject json = readJsonFromUrl(url);
        resultJSON = json.getJSONArray("users");
        
        for(int i = 0; i < resultJSON.length(); i++)
        {
        	JSONObject currResult = resultJSON.getJSONObject(i);
        	
        	UserStats user = new UserStats(
        			currResult.getString("user_id"),
        			currResult.getString("username"),
        			currResult.getInt("curr_wins"),
        			currResult.getInt("curr_losses"),
        			currResult.getInt("curr_played"),
        			currResult.getInt("curr_streak"),
        			currResult.getInt("curr_longest"),
        			currResult.getInt("curr_percent"),
        			currResult.getInt("total_wins"),
        			currResult.getInt("total_losses"),
        			currResult.getInt("total_played"),
        			currResult.getInt("total_longest"),
        			currResult.getInt("total_percent"));
        	
        	
        	longest_streak_list.add(user);
        }                
		return longest_streak_list;
	}
	
	
	public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			Log.e("DEBUG_DAO",line);
			sb.append(line + "\n");
		}
		reader.close();
		String result = sb.toString();
		return result;
	}
	
	
	public boolean isUserLoggedIn(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount();
        if(count > 0){
            // user logged in
            return true;
        }
        return false;
    }
     
    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        return true;
    }
    
    public String getUserIDFromDB(Context context)
    {
    	DatabaseHandler db = new DatabaseHandler(context);
    	return db.getUserIDFromDB();
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
    
		

}
