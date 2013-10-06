package com.example.thenewbostonapplicationtest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//class creating our menu with new activities
public class Menu extends ListActivity {

	String classes[] = { "MainActivity", "TextPlay", "Email", "Camera", "Data",
			"GFX", "GFXSurface", "SoundStuff", "Slider", "Tabs",
			"SimpleBrowser", "Flipper", "SharedPrefs", "InternalData",
			"ExternalData", "SQLiteExample", "Accelerate", "HttpExample",
			"XmlParser" };

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String cheese = classes[position];
		Class ourClass;
		try {
			ourClass = Class.forName("com.example.thenewbostonapplicationtest."
					+ cheese);
			Intent ourIntent = new Intent(Menu.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// create full screen activity
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// setting list adapter
		setListAdapter(new ArrayAdapter<String>(Menu.this,
				android.R.layout.simple_list_item_1, classes));
	}

	// use menu inflater to pop some menu option when we press the menu button
	// on our device
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.aboutUs:
			// example of nice dialog theme appearing when we choose about us.
			// Look into the manifest for additional info!!
			Intent i = new Intent(
					"com.example.thenewbostonapplicationtest.ABOUT");
			startActivity(i);
			break;
		case R.id.preferences:
			Intent p = new Intent(
					"com.example.thenewbostonapplicationtest.PREFS");
			startActivity(p);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}

}
