package motive.e_sportstreak;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import motive.e_sportstreak.controllers.Signup_login_Controller;
import motive.e_sportstreak.views.Signup_login_View;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;

public class Signup_login_Activity extends Activity {

	public static Signup_login_Activity sign_log_activity;
	public Signup_login_Controller sign_log_controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup_login);
		sign_log_activity = this;
		
		final Signup_login_View sign_log_view = new Signup_login_View(this);
		try {
			sign_log_controller = new Signup_login_Controller(this, sign_log_view);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}