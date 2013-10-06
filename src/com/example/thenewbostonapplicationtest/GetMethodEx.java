package com.example.thenewbostonapplicationtest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetMethodEx {

	public String getInternetData() throws Exception{
		BufferedReader bufferedReader = null;
		String data = null;
		try{
			HttpClient client = new DefaultHttpClient();
			URI website = new URI("http://www.mybringback.com");
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response = client.execute(request);
			bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String newLine = System.getProperty("line.separator");
			while ((line = bufferedReader.readLine()) != null){
				sb.append(line + newLine);
			}
			bufferedReader.close();
			data = sb.toString();
			return data;
		} finally {
			if(bufferedReader != null){
				try {
					bufferedReader.close();
					return data;
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
