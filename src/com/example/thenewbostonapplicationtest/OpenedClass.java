package com.example.thenewbostonapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener, OnCheckedChangeListener{
	TextView question;
	RadioGroup selectionList;
	RadioButton crazy;
	RadioButton lazy;
	RadioButton both;
	TextView testText;
	Button returnData;
	String gotBread, setData;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
		
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String et = getData.getString("name", "How are you feeling?");
		String values = getData.getString("list", "4");
		if(values.contentEquals("1")){
			question.setText(et);
		}
		//when we pass data between classes we use the Bundle(our basket)
		//Bundle gotBasket = getIntent().getExtras();
		//reference the new String variable with the 'key' we used in Data class
		//gotBread = gotBasket.getString("key");
		//set it equal to our 'question' 
		//question.setText(gotBread);
	}

	private void initialize() {
		// TODO Auto-generated method stub
		question = (TextView) findViewById(R.id.tvQuestion);
		selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
		crazy = (RadioButton) findViewById(R.id.rCrazy);
		lazy = (RadioButton) findViewById(R.id.rLazy);
		both = (RadioButton) findViewById(R.id.rBoth);
		testText = (TextView) findViewById(R.id.tvTest);
		returnData = (Button) findViewById(R.id.bReturn);
		returnData.setOnClickListener(this);
		selectionList.setOnCheckedChangeListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent person = new Intent();
		Bundle bagpack = new Bundle();
		bagpack.putString("answer", setData);
		person.putExtras(bagpack);
		setResult(RESULT_OK, person);
		finish();
	}

	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch(arg1){
		case R.id.rCrazy:
			setData = "Probably right!";
			break;
		case R.id.rLazy:
			setData = "Definitely!";
			break;
		case R.id.rBoth:
			setData = "Spot on!";
			break;			
		}
		testText.setText(setData);
	}
	
}
