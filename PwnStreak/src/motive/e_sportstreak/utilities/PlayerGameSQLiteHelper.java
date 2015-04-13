package motive.e_sportstreak.utilities;


import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlayerGameSQLiteHelper extends SQLiteOpenHelper{    
    private static final String DATABASE_NAME = "player_games.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PLAYER_GAME = "player_games";
    
    private static final String KEY_ID = "id";
    private static final String KEY_GAME_ID = "game_id";
    private static final String KEY_TOTAL_PICKS = "total_picks";
    private static final String KEY_WINS = "wins";
    private static final String KEY_LOSSES = "losses";
    private static final String KEY_LONGEST_STREAK = "longest_streak";
    private static final String KEY_WIN_PERCENT = "win_percent";
    
    public PlayerGameSQLiteHelper(Context context)
    {
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db)
    {
    	String CREATE_PLAYER_GAME_TABLE = "CREATE TABLE" + TABLE_PLAYER_GAME + "("
    			+ KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
    			+ KEY_GAME_ID + "TEXT NOT NULL, "
    			+ KEY_TOTAL_PICKS + "TEXT NOT NULL, "
    			+ KEY_WINS + "TEXT NOT NULL, "
    			+ KEY_LOSSES + "TEXT NOT NULL, "
    			+ KEY_LONGEST_STREAK + "TEXT NOT NULL, "
    			+ KEY_WIN_PERCENT + "TEXT NOT NULL" + ")";
    	db.execSQL(CREATE_PLAYER_GAME_TABLE);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_GAME);
    }
    
    public void addPlayerGame(String game_id, String total_picks, String wins, String losses, String longest_streak, String win_percent)
    {
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues values = new ContentValues();
    	values.put(KEY_GAME_ID, game_id);
    	values.put(KEY_TOTAL_PICKS, total_picks);
    	values.put(KEY_WINS, wins);
    	values.put(KEY_LOSSES, losses);
    	values.put(KEY_LONGEST_STREAK, longest_streak);
    	values.put(KEY_WIN_PERCENT, win_percent);
    	
    	db.insert(TABLE_PLAYER_GAME, null, values);
    	db.close();
    }
    
    public void addTotal_Picks(String total_picks)
    {
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues values = new ContentValues();
    	values.put(KEY_TOTAL_PICKS, total_picks);
    	db.insert(TABLE_PLAYER_GAME,  null, values);
    	db.close();
    }
    
    public String getTotal_Picks()
    {
    	HashMap<String, String> username = new HashMap<String, String>();
    	String total_row = "";
    	String selectQuery = "SELECT * FROM " + TABLE_PLAYER_GAME;
    	
    	SQLiteDatabase ldb = this.getReadableDatabase();
    	Cursor cursor = ldb.rawQuery(selectQuery, null);
    	
    	cursor.moveToFirst();
    	if( cursor.getCount() > 0 )
    	{
    		total_row = cursor.getString(2);
    		Log.i("FROM PLAYER GAME DATABASE HANDLER TOTAL_ROW: ", total_row);
    	}
    	cursor.close();
    	ldb.close();
    	return total_row;
    }
    
}
