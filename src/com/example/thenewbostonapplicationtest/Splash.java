package com.example.thenewbostonapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity{

	MediaPlayer ourSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//add the splash to the content, also applicable for other classes
		setContentView(R.layout.splash);
	    ourSong = MediaPlayer.create(Splash.this, R.raw.background);
	    
	    //connect our preference for music with our splash class
	    SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	    boolean music = getPrefs.getBoolean("checkbox", true);
	    if(music == true){
		ourSong.start();
	    }
		//set threading, in this case - 5 seconds long = sleep(5000) - in milliseconds 
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(5000);					
				}catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					//after our first activity(the song for 5 seconds), open next activity
					Intent openMainActivity = new Intent("com.example.thenewbostonapplicationtest.MENU");
					startActivity(openMainActivity);
				}
			}
		};
		timer.start();
	}

	//method to pause our song, when we finish with our thread
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release();
		finish();
	}
}
