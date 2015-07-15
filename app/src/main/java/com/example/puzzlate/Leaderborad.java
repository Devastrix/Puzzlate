package com.example.puzzlate;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Leaderborad extends Activity {

	TextView tv1,header;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leaderborad);
		tv1 = (TextView) findViewById(R.id.textView2);
		header = (TextView) findViewById(R.id.textView1);
	
	
		
		//read from database
		DataB entry = new DataB(Leaderborad.this);
		entry.open();
		String data = entry.getdata();
		entry.close();
		Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "Transformers Movie.ttf");
        header.setTypeface(custom_font1);
		 Typeface custom_font = Typeface.createFromAsset(getAssets(), "TitilliumWeb-Black.ttf");
         tv1.setTypeface(custom_font);
		tv1.setText(data);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.leaderborad, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
