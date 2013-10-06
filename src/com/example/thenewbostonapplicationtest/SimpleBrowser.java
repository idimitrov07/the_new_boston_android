package com.example.thenewbostonapplicationtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener{

	WebView ourBrow;
	EditText etUrl;
	Button buttonGo, buttonBack, buttonForward, buttonClearHistory, buttonRefresh; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		
		ourBrow = (WebView) findViewById(R.id.wvBrowser);
		//enable java script in browser and other settings
		ourBrow.getSettings().setJavaScriptEnabled(true);
		ourBrow.getSettings().setLoadWithOverviewMode(true);
		ourBrow.getSettings().setUseWideViewPort(true);
		
		//set our own web view client, so the pages load in our browser
		ourBrow.setWebViewClient(new ourViewClient());
		
		try {
			ourBrow.loadUrl("http://www.mybringback.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		etUrl = (EditText) findViewById(R.id.etURL);
		buttonGo = (Button) findViewById(R.id.bGo);
		buttonBack = (Button) findViewById(R.id.bBack);
		buttonForward = (Button) findViewById(R.id.bForward);
		buttonRefresh = (Button) findViewById(R.id.bRefresh);
		buttonClearHistory = (Button) findViewById(R.id.bHistory);
		
		buttonGo.setOnClickListener(this);
		buttonBack.setOnClickListener(this);
		buttonForward.setOnClickListener(this);
		buttonRefresh.setOnClickListener(this);
		buttonClearHistory.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bGo:
			String theWebsite = "http://" + etUrl.getText().toString();
			ourBrow.loadUrl(theWebsite);
			//hiding keyboard, after page loads
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(etUrl.getWindowToken(), 0);
			break;
		case R.id.bBack:
			if(ourBrow.canGoBack()){
		    ourBrow.goBack();
			}
			break;
		case R.id.bForward:
			if(ourBrow.canGoForward()){
				ourBrow.goForward();
			}
			break;
		case R.id.bRefresh:
			ourBrow.reload();
		case R.id.bHistory:
			ourBrow.clearHistory();
			break;
		}
		
	}
	

}
