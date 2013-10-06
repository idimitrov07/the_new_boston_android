package com.example.thenewbostonapplicationtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;


public class Tabs extends Activity implements OnClickListener{

	TabHost th;
	TextView showResults;
	long start;
	long stop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		th = (TabHost) findViewById(R.id.tabhost);
		//set up tabs in java
		Button newTab = (Button) findViewById(R.id.bAddtab);
		Button bStart = (Button) findViewById(R.id.bStart);
		Button bStop = (Button) findViewById(R.id.bStop);
		showResults = (TextView) findViewById(R.id.tvShowResult);
		
		newTab.setOnClickListener(this);
		//stop watch application
		start = 0;
		bStart.setOnClickListener(this);
		bStop.setOnClickListener(this);
		//set up the tab host first
		th.setup();
		//first tab
		TabSpec specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("Stop Watch");
		th.addTab(specs);
		//second tab
		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab 2");
		th.addTab(specs);
		//third tab
		specs = th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a Tab");
		th.addTab(specs);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bAddtab:
			//create new tabs
			TabSpec ourSpec = th.newTabSpec("tag1");
			ourSpec.setContent(new TabHost.TabContentFactory() {
				
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView text = new TextView(Tabs.this);
					text.setText("You have created a new tab!");
					return (text);
				}
			});
			ourSpec.setIndicator("New");
			th.addTab(ourSpec);
			break;
		case R.id.bStart:
			start = System.currentTimeMillis();
			showResults.setText("Running...");
			break;
		case R.id.bStop:
			stop = System.currentTimeMillis();
			if(start != 0){
				long result = stop - start;
				int millis = (int)result;
				int seconds = (int)result/1000;
				int minutes = seconds/60;
				millis = millis % 100;
				seconds = seconds % 60;
				showResults.setText(String.format("%d:%02d:%02d", minutes,seconds,millis));
			}else {
				showResults.setText("Press start!");
			}
			
			start = 0;
			break;
			
		}
	}
	

}
