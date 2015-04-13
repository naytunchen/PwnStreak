package motive.e_sportstreak.views;

import motive.e_sportstreak.R;
import motive.e_sportstreak.SignupActivity;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupView {

	private Button createButton;
	private View view;
	private EditText first_name, last_name, username, email, password, password2, company, phone;
	
	public SignupView(Activity activity)
	{
		
		view = SignupActivity.signupAct.getWindow().getDecorView().getRootView();
//		Typeface robottoBlack = Typeface.createFromAsset(SignupActivity.signupAct.getAssets(),
//				"fonts/Roboto-Black.ttf");        
//		Typeface robottoBold = Typeface.createFromAsset(SignupActivity.signupAct.getAssets(),
//				"fonts/Roboto-BoldCondensed.ttf");
//		Typeface robottoThin = Typeface.createFromAsset(SignupActivity.signupAct.getAssets(),
//				"fonts/Roboto-Thin.ttf");
//		Drawable bg = SignupActivity.signupAct.getResources().getDrawable(R.drawable.greenbg);
//		bg.setAlpha(160);
		
//		TextView greeting = (TextView) view.findViewById(R.id.greeting);
//		greeting.setTypeface(robottoBold);
//		TextView greeting2 = (TextView) view.findViewById(R.id.greeting2);
//		greeting2.setTypeface(robottoThin);
		
		createButton = (Button) view.findViewById(R.id.create_account_button);

		
		first_name = (EditText) view.findViewById(R.id.reg_first_name);
		last_name = (EditText) view.findViewById(R.id.reg_last_name);
		username = (EditText) view.findViewById(R.id.reg_username);
		email = (EditText) view.findViewById(R.id.reg_email);
		password = (EditText) view.findViewById(R.id.reg_password);
		password2 = (EditText) view.findViewById(R.id.reg_password1);
		company = (EditText) view.findViewById(R.id.reg_company);
		phone = (EditText) view.findViewById(R.id.reg_phone);

	}
	
	public void clearPasswords()
	{
		((EditText) view.findViewById(R.id.reg_password)).setText("");
		((EditText) view.findViewById(R.id.reg_password1)).setText("");

	}
	
	public Button getButton() {
		return createButton;
	}
	public void setButton(Button button) {
		this.createButton = button;
	}
	public EditText getFirst_name() {
		return first_name;
	}
	public void setFirst_name(EditText first_name) {
		this.first_name = first_name;
	}
	public EditText getLast_name() {
		return last_name;
	}
	public void setLast_name(EditText last_name) {
		this.last_name = last_name;
	}

	public EditText getPass() {
		return password;
	}
	public void setPass(EditText pass) {
		this.password = pass;
	}
	public EditText getConfirmPass() {
		return password2;
	}
	public void setConfirmPass(EditText pass2) {
		this.password2 = pass2;
	}
	public EditText getEmail() {
		return email;
	}
	public void setEmail(EditText email) {
		this.email = email;
	}
	public EditText getUsername() {
		return username;
	}
	public void setUsername(EditText uname) {
		this.username = uname;
	}
	public EditText getCompany() {
		return company;
	}
	public void setCompany(EditText company) {
		this.company = company;
	}public EditText getPhone() {
		return phone;
	}
	public void setPhone(EditText phone) {
		this.phone = phone;
	}

}
