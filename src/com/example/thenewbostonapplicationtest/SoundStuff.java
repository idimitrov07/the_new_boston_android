package com.example.thenewbostonapplicationtest;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements OnClickListener, OnLongClickListener{

	SoundPool sp;
	MediaPlayer mP;
	int explosion;
	int counter = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View v = new View(this);
		v.setOnClickListener(this);
		v.setOnLongClickListener(this);
		setContentView(v);
		//sound pool is for small clips, played quickly
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		explosion = sp.load(this, R.raw.punch, 1);
		//media player is for longer songs, clips
		mP = MediaPlayer.create(this, R.raw.background);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(explosion != 0){
		sp.play(explosion, 1, 1, 0, 0, 1);
		//mP.stop();
		}
	}

	public boolean onLongClick(View arg0) {
		// TODO Auto-generated method stub
		counter++;
		//stop music on second long click
		if(counter % 2 != 0 ){
		mP.start();
		}else {
			mP.pause();
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		mP.pause();
	}


	/*
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mP.stop();
		}
		*/

}
