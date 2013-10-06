package com.example.thenewbostonapplicationtest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class Slider extends Activity implements OnCheckedChangeListener, OnClickListener, OnDrawerOpenListener{

	Button hb1, hb2, hb3, hb4;
	CheckBox checkbox;
	SlidingDrawer sD;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		hb1 = (Button) findViewById(R.id.handle1);
		hb2 = (Button) findViewById(R.id.handle2);
		hb3 = (Button) findViewById(R.id.handle3);
		hb4 = (Button) findViewById(R.id.handle4);
		checkbox = (CheckBox) findViewById(R.id.cbSliding);
		checkbox.setOnCheckedChangeListener(this);
		sD = (SlidingDrawer) findViewById(R.id.slidingD);
		sD.setOnDrawerOpenListener(this);
		hb1.setOnClickListener(this);
		hb2.setOnClickListener(this);
		hb3.setOnClickListener(this);
		hb4.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.handle1:
			sD.open();
			break;
		case R.id.handle2:
			
			break;
		case R.id.handle3:
			sD.toggle();
			break;
		case R.id.handle4:
			sD.close();
			break;
		
		}
		
	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		if(arg0.isChecked()){
			sD.lock();
		}else {
			sD.unlock();
		}
	}

	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		MediaPlayer mP = MediaPlayer.create(this, R.raw.punch);
		mP.start();
	}
	

}
