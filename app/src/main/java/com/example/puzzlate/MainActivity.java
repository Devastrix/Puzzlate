package com.example.puzzlate;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener {
	
	  private Integer[] mThumbIds = {
              R.drawable.one, R.drawable.two,R.drawable.three,
              R.drawable.four, R.drawable.five,R.drawable.six,
              R.drawable.seven, R.drawable.eight,R.drawable.circle,
          };
	  List<Integer> pictures;
	  Button shuffler;
	  TextView att;
	  Button endg;
	  ImageView sam;
	  TextView score;
	  GridView gridview;
	  int empty = 8;
	  int completed = 0;
	  int attempts = 0;
	  ImageAdapter im ;
	  long timeMillis, end;
	  int scoreC;
	  MediaPlayer mp;
	  String naam;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	         setContentView(R.layout.activity_main);
	          gridview = (GridView) findViewById(R.id.gridView1);   
	          att = (TextView) findViewById(R.id.textView1);
	          endg = (Button) findViewById(R.id.button2);   
	          score = (TextView) findViewById(R.id.textView2);
	          Typeface custom_font = Typeface.createFromAsset(getAssets(), "TitilliumWeb-Black.ttf");
	          att.setTypeface(custom_font);
	          score.setTypeface(custom_font);
	          sam = (ImageView) findViewById(R.id.imageView1);
	          shuffler = (Button) findViewById(R.id.button1);
	         shuffler.setOnClickListener(this);
	         endg.setOnClickListener(this);
	     	gridview.setOnItemClickListener(this);
	          im = new ImageAdapter(this);
	         
	          sam.setVisibility(View.VISIBLE);
	          endg.setVisibility(View.INVISIBLE);
	          att.setVisibility(View.INVISIBLE);
	          
	          Bundle extras = getIntent().getExtras();
	          if (extras != null) {
	               naam = extras.getString("username");
	              
	              
	          }
	          score.setText("Arrange the numbers from 1 to 8 as shown in the figure below ");

	         	      
	         
	    }             
	    public class ImageAdapter extends BaseAdapter{
	         private Context mContext;
	         public int getCount() {
	              return mThumbIds.length;
	         }                               
	         public Object getItem(int position) {
	              return mThumbIds[position];
	         }                               
	         public long getItemId(int position) {
	              return 0;
	         }                               
	         public ImageAdapter(Context c) {
	              mContext = c;
	         }                         
	                               
	         public View getView(int position, View convertView, ViewGroup parent) {
	              ImageView imageView;
	              if (convertView == null){  
	                 imageView = new ImageView(mContext);
	                 imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
	                 imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	                 imageView.setPadding(2 , 1, 1, 2);
	              } 
	              else{
	                  imageView = (ImageView) convertView;
	              }
	              imageView.setImageResource(pictures.get(position));
	              return imageView;
	         }
	                              
	       
	       
		  
	     }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(mp != null)
		mp.release();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.button1:
			mp = MediaPlayer.create(getBaseContext(), R.raw.downhill);
			mp.start();
			sam.setVisibility(View.GONE);
			score.setVisibility(View.GONE);
		//shuffling
		completed = 0;
		shuffler.setVisibility(View.GONE);
		gridview.setVisibility(View.VISIBLE);
		 endg.setVisibility(View.VISIBLE);
		 att.setVisibility(View.VISIBLE);

		timeMillis = System.currentTimeMillis();
	     



		attempts = 0;
		att.setText("Game Started!");
        pictures = new ArrayList<Integer>();
        Random r = new Random();
        int randomNum = r.nextInt((5 - 0) + 1) + 0;
        int a[][] = {{0,1,2,3,5,7,6,4,8},{4,1,7,0,3,2,6,5,8},{0,5,4,2,6,1,3,7,8},{1,3,2,0,7,4,6,5,8},{3,0,1,6,4,5,2,7,8},{7,1,0,3,2,4,6,5,8}};
        for (int index = 0; index < ((mThumbIds.length)); index++)
        {
            pictures.add(mThumbIds[a[randomNum][index]]);
        }

		// Collections.shuffle(pictures);
		 empty = pictures.indexOf(R.drawable.circle);
 		
		 gridview.setAdapter(im);
		 break;
		case R.id.button2:
			mp.release();
			 endg.setVisibility(View.INVISIBLE);
		      
		       gridview.setVisibility(View.INVISIBLE);
		       shuffler.setVisibility(View.VISIBLE);
		       att.setVisibility(View.INVISIBLE);
		       
		      
		       break;
		}
	

	}
	/*

	private void gameTimer(int i) {
		// TODO Auto-generated method stub
		CountDownTimer mCountDown;
		
		mCountDown = new CountDownTimer(200000, 1000) {

		     public void onTick(long millisUntilFinished) {
		         score.setText( (millisUntilFinished / 1000)+" s");
		     }

		     public void onFinish() {
		         score.setText("done!");
		     }
		  }.start();
		if(i == 0) {
			mCountDown.cancel();
		}
		

	}
	*/

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position,
			long id) {
		// TODO Auto-generated method stub
		  
			if((position == (empty-1) && ((position+empty) % 3 != 2)) || (position == (empty+1) && ((position+empty) % 3 != 2)) || position == (empty-3) || position == (empty+3) ) {
				 Integer help=new Integer(pictures.get(position));
	                pictures.set(position, pictures.get(empty));
	                pictures.set(empty, help);
	                empty = position;
	                im.notifyDataSetChanged();
	                gridview.setAdapter(im);
	                gridview.invalidateViews();
	                attempts++;
	                att.setText("Attempts = "+ attempts);
	                int x = 0;
	                //checking for the result
	                for(int i = 0 ; i < 9; i++) {
	                	if(mThumbIds[i] != (int)pictures.get(i)) {
	                		x = 1;
	                		break;
	                	}
	                	else {
	                		x = 0;
	                	}
	                	
	                	
	                }
	                
	                	if(x == 0) {
	                		
	                		//  completed
	                		mp.release();
	                		completed = 1;
	                		 att.setVisibility(View.INVISIBLE);
	                		shuffler.setVisibility(View.VISIBLE);
	                		gridview.setVisibility(View.INVISIBLE);
	                		 endg.setVisibility(View.INVISIBLE);
	                		end = System.currentTimeMillis();
	                		long timeSeconds = TimeUnit.MILLISECONDS.toSeconds(end - timeMillis);
	                		scoreC = 10000/attempts;
	                		String scorewa = "" + scoreC;
	                		//entry in database
	                		DataB entry = new DataB(MainActivity.this);
	            			entry.open();
	            			entry.create(naam,scorewa);
	            			entry.close();
	                		
	                		
	                		Vibrator vib = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
	                		 // Vibrate for 500 milliseconds
	                		 vib.vibrate(500);
	                		Intent intent = new Intent(getBaseContext(), Score.class);
	            			intent.putExtra("points", scoreC);
	            			intent.putExtra("timeT", timeSeconds);
	            			startActivity(intent);
	                		//Toast.makeText(getBaseContext(), "Congrats! You took "+timeSeconds+"s", Toast.LENGTH_SHORT).show();
	                	}
	   
	             
			}
		   
		
	}
}
