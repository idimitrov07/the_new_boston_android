package com.example.thenewbostonapplicationtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutUs extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		TextView aboutUsText = (TextView) findViewById(R.id.tvAbout);
	}

}
