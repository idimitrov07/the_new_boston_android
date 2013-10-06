package com.example.thenewbostonapplicationtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity{

	MyBringBack ourView;
	WakeLock wL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		//create a wake lock
		//keep screen awake the whole time
		PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
	    wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "WakeLock");
		
		super.onCreate(savedInstanceState);
		wL.acquire();
		ourView = new MyBringBack(this);
		setContentView(ourView);
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wL.release();
	}
	
}
