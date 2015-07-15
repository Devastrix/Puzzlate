package com.example.puzzlate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataB {
	public static final String KEY_ID = "_id";
	public static final String KEY_name = "name";
	public static final String KEY_score = "score";
	
	public static final String DB_NAME= "puzzlate";
	public static final String DB_TABLE = "highscore";
	public static final int DB_VERSION = 1;
	private DBHelper helper;
	private  final Context  cont;
	private SQLiteDatabase mydb;
	
	public static class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context) {
			super(context,DB_NAME, null, DB_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE "+DB_TABLE+" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
			KEY_name+" TEXT NOT NULL, "+
					KEY_score+" TEXT NOT NULL);");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);
			onCreate(db);
		}
		
	}
	public DataB(Context c) {
		cont = c;
	}
	public DataB open() {
		helper = new DBHelper(cont);
		mydb = helper.getWritableDatabase();
		return this;
		
	}
	public long create(String user,String myscore) {
		// TODO Auto-generated method stub
		ContentValues cv  = new ContentValues();
		cv.put(KEY_name, user);
		cv.put(KEY_score,myscore);
		return mydb.insert(DB_TABLE, null, cv);
		
	}
	public void close() {
		// TODO Auto-generated method stub
		helper.close();
		
	}
	public String getdata() {
	
		// TODO Auto-generated method stub
		
		String[] col = new String[]{KEY_ID,KEY_name,KEY_score};
		
		Cursor c = mydb.query(DB_TABLE, col, null, null,null, null, null);
		String result = "";
		int irow = c.getColumnIndex(KEY_ID);
		int iname = c.getColumnIndex(KEY_name);
		int iscore = c.getColumnIndex(KEY_score);
		int uscore[] = new int[100];
		String naams[] = new String[100];
		int i;
		for(c.moveToFirst(), i = 0; !c.isAfterLast(); c.moveToNext(),i++) {
			uscore[i] = Integer.parseInt(c.getString(iscore));
			naams[i] = c.getString(iname);
			
		}
		  // bubble sort
		
		for(int k = 0; k < i-1; k++) {
			for(int j = 0; j < i; j++) {
				if(uscore[j] < uscore[j+1]){
					int t = uscore[j];
					uscore[j] = uscore[j+1];
					uscore[j+1] = t;
					
					String s = naams[j];
					naams[j] = naams[j+1];
					naams[j+1] = s;
				}
			}
		}
		int count = 0;
		for(int z = 0; z < i ; z++) {
			result = result + naams[z]+ " : " + uscore[z] + "\n";
			count++;
			if(count == 5)
				break;
		}
		
		return result;
	}

}
