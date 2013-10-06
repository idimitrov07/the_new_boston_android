package com.example.thenewbostonapplicationtest;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class XmlParser extends Activity implements OnClickListener {

	EditText inputCity;
	EditText inputState;
	Button buttonCheckWeather;
	TextView textWeather;

	String dataUrl1 = "http://api.openweathermap.org/data/2.5/weather?q=";
	String dataUrl2 = "&mode=xml&units=metric";

	String fullUrl = null;

	// ParseAsync parseAsync = new ParseAsync();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xmlparser);
		inputCity = (EditText) findViewById(R.id.etCity);
		inputState = (EditText) findViewById(R.id.etState);
		buttonCheckWeather = (Button) findViewById(R.id.bCheckWeather);
		textWeather = (TextView) findViewById(R.id.tvWeather);

		buttonCheckWeather.setOnClickListener(this);

		// not good method to allow network activity in Main thread, but not
		// other way is working properly...
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		String city = inputCity.getText().toString();
		String state = inputState.getText().toString();

		// checking for empty space in the city name
		if (city.contains(" ")) {
			/*
			String[] cCity = city.split(" ");
			StringBuilder sbCity = new StringBuilder();
			sbCity.append(cCity[0] + "%20" + cCity[1]);
			city = sbCity.toString();
			*/
			city = city.replace(" ", "%20");

		}

		StringBuilder URL = new StringBuilder(dataUrl1);
		URL.append(city + "," + state);
		URL.append(dataUrl2);

		fullUrl = URL.toString();

		// textWeather.setText(fullUrl);
		// parseAsync.execute();

		try {
			URL website = new URL(fullUrl);
			// getting xml reader parsed data
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			HandlingXmlStuff doingWork = new HandlingXmlStuff();
			xr.setContentHandler(doingWork);
			xr.parse(new InputSource(website.openStream()));

			String information = doingWork.getInformation();
			textWeather.setText(information);

		} catch (Exception e) {
			// textWeather.setText("Error");
			Log.d("ERROR", e.toString());
		}

	}

	/*
	 * public class ParseAsync extends AsyncTask<String, Void, String> {
	 * 
	 * String information = null;
	 * 
	 * @Override protected String doInBackground(String... params) { // TODO
	 * Auto-generated method stub
	 * 
	 * try { URL website = new URL(fullUrl); // getting xml reader parsed data
	 * SAXParserFactory spf = SAXParserFactory.newInstance(); SAXParser sp =
	 * spf.newSAXParser(); XMLReader xr = sp.getXMLReader();
	 * 
	 * HandlingXmlStuff doingWork = new HandlingXmlStuff();
	 * xr.setContentHandler(doingWork); xr.parse(new
	 * InputSource(website.openStream()));
	 * 
	 * information = doingWork.getInformation(); return information;
	 * 
	 * } catch (Exception e) { // textWeather.setText("Error"); Log.d("ERROR",
	 * e.toString()); } return null;
	 * 
	 * }
	 * 
	 * @Override protected void onPostExecute(String result) { // TODO
	 * Auto-generated method stub textWeather.setText(information);
	 * 
	 * }
	 * 
	 * }
	 */

}
