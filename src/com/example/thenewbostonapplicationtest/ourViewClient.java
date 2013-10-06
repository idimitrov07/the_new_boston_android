package com.example.thenewbostonapplicationtest;

import android.webkit.WebView;
import android.webkit.WebViewClient;


public class ourViewClient extends WebViewClient {
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView wv, String url){
		wv.loadUrl(url);
		return true;
	}

}
