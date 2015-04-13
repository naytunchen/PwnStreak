package motive.e_sportstreak.views;

import motive.e_sportstreak.R;
import motive.e_sportstreak.Signup_login_Activity;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Signup_login_View {
	private Button loginButton, signupButton;
	private EditText id;
	private EditText password;
	private ImageView logo;
	private View view;
	public Signup_login_View(Activity activity)
	{
		view = Signup_login_Activity.sign_log_activity.getWindow().getDecorView().getRootView();
//		Typeface robottoBold = Typeface.createFromAsset(Signup_login_Activity.sign_log_activity.getAssets(),
//				"fonts/Roboto-BoldCondensed.ttf");
//		Typeface robottoThin = Typeface.createFromAsset(Signup_login_Activity.sign_log_activity.getAssets(),
//				"fonts/Roboto-Thin.ttf");
		this.id = (EditText)view.findViewById(R.id.login_email);
		this.password = (EditText)view.findViewById(R.id.login_password);
		loginButton = (Button) view.findViewById(R.id.login_button);
		signupButton = (Button) view.findViewById(R.id.signup_button);
	    
//		TextView header = (TextView) view.findViewById(R.id.appName);
//		header.setTypeface(robottoBold);
		TextView disclaimer = (TextView) view.findViewById(R.id.disclaimer);
//		header.setTypeface(robottoThin);
	}
	
	public void clearText(){
		
		((EditText)view.findViewById(R.id.login_email)).setText("");
		((EditText)view.findViewById(R.id.login_password)).setText("");
		
	}
	public Button getLoginButton() {
		return loginButton;
	}
	public void setLoginButton(Button loginButton) {
		this.loginButton = loginButton;
	}

	public Button getSignUpButton() {
		return signupButton;
	}
	public void setSignUpButton(Button signupButton) {
		this.signupButton = signupButton;
	}

	public EditText getId() {
		return id;
	}

	public void setId(EditText id) {
		this.id = id;
	}

	public EditText getPassword() {
		return password;
	}

	public void setPassword(EditText password) {
		this.password = password;
	}

}
