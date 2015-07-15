package com.example.puzzlate;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Score extends Activity implements OnClickListener {

	TextView title,seconds,scoreC;
	Button close;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		title = (TextView)findViewById(R.id.textView1);
		seconds = (TextView)findViewById(R.id.textView3);
		scoreC = (TextView)findViewById(R.id.textView2);
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "Transformers Movie.ttf");
        title.setTypeface(custom_font);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "TitilliumWeb-Black.ttf");
        seconds.setTypeface(custom_font1);
        scoreC.setTypeface(custom_font1);
        close = (Button) findViewById(R.id.button1);
        close.setOnClickListener(this);
        
        WindowManager.LayoutParams params = getWindow().getAttributes();  
        params.x = -20;  
        params.height = 600;  
        params.width = 550;  
        params.y = -10; 
        

        this.getWindow().setAttributes(params); 
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int poin = extras.getInt("points");
            long tim = extras.getLong("timeT");
            title.setText("Congratulations!");
            seconds.setText("You completed the puzzle in "+tim+"s");
            scoreC.setText("Score: "+poin);
        }
        
       
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
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
		finish();
	}
}
