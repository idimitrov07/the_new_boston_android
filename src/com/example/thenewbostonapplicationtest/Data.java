package com.example.thenewbostonapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements OnClickListener{
	EditText sendInfo;
	Button startAct;
	Button startActForRes;
	TextView gotText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initialize();	
	}

	private void initialize() {
		// TODO Auto-generated method stub
		sendInfo = (EditText) findViewById(R.id.etSend);
		startAct = (Button) findViewById(R.id.bSA);
		startActForRes = (Button) findViewById(R.id.bSAFR);
		gotText = (TextView) findViewById(R.id.tvGot);
		startAct.setOnClickListener(this);
		startActForRes.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSA:
			//get the text from the input
			String bread = sendInfo.getText().toString();
			//put it in the bundle(bundle is like a basket), so we can pass the data between classes
			Bundle basket = new Bundle();
			//put the text we entered in the basket with a special 'key'
			basket.putString("key", bread);
			//hold the data from this class, and open the new class
			Intent a = new Intent(Data.this, OpenedClass.class);
			//add extras to the intent
			a.putExtras(basket);
			startActivity(a);
			break;
		case R.id.bSAFR:
			Intent i = new Intent(Data.this, OpenedClass.class);
			startActivityForResult(i, 0);
			break;
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle basket = data.getExtras();
			String s = basket.getString("answer");
			gotText.setText(s);
		}
	}
	
}
