package com.example.thenewbostonapplicationtest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener{

	EditText sharedData;
	TextView dataResult;
	public static String fileName = "MySharedString";
	SharedPreferences someData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVAriables();
		//shared preferences type variable
		//think of this variable as a folder!!
		someData  = getSharedPreferences(fileName, 0);
	}
	private void setupVAriables() {
		// TODO Auto-generated method stub
		sharedData = (EditText) findViewById(R.id.etSharedPrefs);
		dataResult = (TextView) findViewById(R.id.tvSharedResults);
		Button saveButton = (Button) findViewById(R.id.bSharedSave);
		Button loadButton = (Button) findViewById(R.id.bSharedLoad);
		saveButton.setOnClickListener(this);
		loadButton.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSharedSave:
			String stringData = sharedData.getText().toString();
			//create editor of our "folder"
			SharedPreferences.Editor editor = someData.edit();
			//put our text inside
			editor.putString("sharedString", stringData);
			//finally -> commit!
			editor.commit();
			break;
		case R.id.bSharedLoad:
			someData  = getSharedPreferences(fileName, 0);
			//get the text from our "folder"
			String dataReturned = someData.getString("sharedString", "Couldn't load..");
			dataResult.setText(dataReturned);
			break;
		}
	}

}
