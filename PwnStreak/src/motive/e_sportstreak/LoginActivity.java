package motive.e_sportstreak;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import com.facebook.*;
import motive.e_sportstreak.utilities.CheckNetwork;

public class LoginActivity extends FragmentActivity {

  private LoginFragment loginFrag;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
	CheckNetwork.isInternetAvailable(getApplicationContext());

    if (savedInstanceState == null) {
        // Add the fragment on initial activity setup
        loginFrag = new LoginFragment();
        getSupportFragmentManager()
        .beginTransaction()
        .add(android.R.id.content, loginFrag)
        .commit();
    } else {
        // Or set the fragment from restored state info
        loginFrag = (LoginFragment) getSupportFragmentManager()
        .findFragmentById(android.R.id.content);
    }
    
    
    //setContentView(R.layout.activity_login);


    /*// start Facebook Login
    Session.openActiveSession(this, true, new Session.StatusCallback() {

      // callback when session changes state
      @Override
      public void call(Session session, SessionState state, Exception exception) {
        if (session.isOpened()) {

          // make request to the /me API
        	Request.newMeRequest(session, new Request.GraphUserCallback() {

        		  // callback after Graph API response with user object
        		  @Override
        		  public void onCompleted(GraphUser user, Response response) {
        		    if (user != null) {
        		      //TextView welcome = (TextView) findViewById(R.id.welcome);
        		     // welcome.setText("Hello " + user.getName() + "!");
        		    }
        		  }
        		}).executeAsync();
        }
      }
    });*/
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
  }

}
