package motive.e_sportstreak.utilities;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
//NOTE TO SELF: Might have to add other activities!!!!!
import motive.e_sportstreak.MainActivity;

/* This class provides a method for checking a valid Internet
 * Connectivity, mainly on MainActivity for now.
 * 
 * Purpose: Check Internet Connection
 */
public class CheckNetwork {
	
	/* Method name: isInternetAvailable
	 * Purpose: Checks valid Internet Connection
	 * Parameter: Context context
	 * Returns: boolean - true (if there's a valid connection)
	 *                    false (if no Internet Connection)
	 */
	public static boolean isInternetAvailable(Context context)
	{
		// Check Network Connection (Internet)
		NetworkInfo info = ((ConnectivityManager)
				context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

		// If Internet is unavailable while the user is on MainActivity, 
		// then toasts a message to check connection, return false (no Internet)
		if (info == null)
		{
			Intent i = new Intent(context,MainActivity.class);
			MainActivity.mainActivity.startActivity(i);    
			Toast.makeText(context, "Valid Internet Connection Required", Toast.LENGTH_LONG).show();
			return false;
		}
		// Otherwise, return true (There's a valid Internet Connection)
		else
		{
			return true;
		}
	}

}
