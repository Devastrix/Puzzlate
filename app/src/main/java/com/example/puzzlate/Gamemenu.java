package com.example.puzzlate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Gamemenu extends Activity implements OnClickListener {
	EditText name;
	Button startGame, leaderboard,exit;
	String user;
	TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gamemenu);
		title = (TextView) findViewById(R.id.textView1);
		name = (EditText) findViewById(R.id.editText1);
		startGame = (Button) findViewById(R.id.button1);
		leaderboard = (Button) findViewById(R.id.button2);
		exit = (Button) findViewById(R.id.button3);
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "Transformers Movie.ttf");
        title.setTypeface(custom_font);
		
		startGame.setOnClickListener(this);
		leaderboard.setOnClickListener(this);
		exit.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gamemenu, menu);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.button1:
			user = name.getText().toString();
			if(user.matches("")) { 
				Vibrator vib = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
       		 // Vibrate for 500 milliseconds
       		 vib.vibrate(200);
				Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
				name.startAnimation(shake);
				break;
				} 
			Intent intent = new Intent(getBaseContext(), MainActivity.class);
			intent.putExtra("username", user);
			startActivity(intent);
			name.setText("");
			break;
		case R.id.button2:
			startActivity(new Intent("com.example.puzzlate.LEA"));
			break;
		case R.id.button3:
			finish();
			break;
		}

	}
}
