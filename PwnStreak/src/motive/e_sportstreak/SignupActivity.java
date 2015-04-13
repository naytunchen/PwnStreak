package motive.e_sportstreak;

import motive.e_sportstreak.controllers.SignupController;
import motive.e_sportstreak.util.SystemUiHider;
import motive.e_sportstreak.views.SignupView;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class SignupActivity extends Activity {

	public static SignupActivity signupAct;
	Button butt;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
			        new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		signupAct = this;
		
		final SignupView signupView = new SignupView(this);
		SignupController signupController = new SignupController(this, signupView);
	}
}
