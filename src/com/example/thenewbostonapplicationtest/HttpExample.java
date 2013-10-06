package com.example.thenewbostonapplicationtest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HttpExample extends Activity{

	TextView httpStuff;
	HttpClient client ;
	JSONObject jsonTweet;
	
	final static String URL = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpexample);
		httpStuff = (TextView) findViewById(R.id.tvHttp);
		client = new DefaultHttpClient();
		new Read().execute("text");
		
		
		/* uncomment this block to get page source
		GetMethodEx test = new GetMethodEx();
		String returned;
		try {
			returned = test.getInternetData();
			httpStuff.setText(returned);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
	}
	
	public JSONObject lastTweet(String username) throws ClientProtocolException, IOException, JSONException{
		StringBuilder sbUrl = new StringBuilder(URL);
		sbUrl.append(username);
		
		HttpGet get = new HttpGet(sbUrl.toString());
		HttpResponse httpResponse = client.execute(get);
		
		//check for successful connection
		int status = httpResponse.getStatusLine().getStatusCode();
		if(status == 200){
			HttpEntity httpEntity = httpResponse.getEntity();
			String data = EntityUtils.toString(httpEntity);
			JSONArray timeline = new JSONArray(data);
			JSONObject lastTweet = timeline.getJSONObject(0);
			return lastTweet;
		}else {
			Toast.makeText(HttpExample.this, "error", Toast.LENGTH_LONG).show();
			return null;
		}
	}
	
	public class Read extends AsyncTask<String, Integer, String>{

		@Override
		protected String doInBackground(String... str) {
			// TODO Auto-generated method stub
			try {
				jsonTweet = lastTweet("ivandimitrov24");
				return jsonTweet.getString(str[0]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			httpStuff.setText(result);
		}
		
		
		
	}

}
