package com.example.thenewbostonapplicationtest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener, OnClickListener{

	private TextView canWrite, canRead, saveAsText;
	private String state;
	boolean canW, canR;
	Spinner spinner;
	String[] paths = {"Music", "Pics", "Downloads"};
	File path = null;
	File file = null;
	EditText saveFile;
	Button confirm, save;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		
		canWrite = (TextView) findViewById(R.id.tvCanWrite);
		canRead = (TextView) findViewById(R.id.tvCanRead);
		saveAsText = (TextView) findViewById(R.id.tvSaveAs);
		
		saveFile = (EditText) findViewById(R.id.etSaveAs);
		
		confirm = (Button) findViewById(R.id.bConfirmSaveAs);
	    save = (Button) findViewById(R.id.bSaveFile);
	    
		confirm.setOnClickListener(this);
		save.setOnClickListener(this);
		
		checkState();
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_item, paths);
				
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}

	private void checkState() {
		// TODO Auto-generated method stub
		state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			//read and write
			canWrite.setText("true");
			canRead.setText("true");
			canW = true;
			canR = true;
		}else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
			//read, but can't write
			canWrite.setText("false");
			canRead.setText("true");
			canW = false;
			canR = true;
		}else {
			canWrite.setText("false");
			canRead.setText("false");
			canW = false;
			canR = false;
		}
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int position = spinner.getSelectedItemPosition();
		switch(position){
		case 0:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
			
		case 2:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
		}
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bConfirmSaveAs:
			save.setVisibility(View.VISIBLE);
			break;
		case R.id.bSaveFile:
			String f = saveFile.getText().toString() + ".png";
			file = new File(path, f);
			
			checkState();
			if(canW == true && canR == true){
				//create a folder if it doesn't exist
				path.mkdirs();
				
				try {
					InputStream is = getResources().openRawResource(R.drawable.greenball);
					OutputStream os = new FileOutputStream(file);
					byte[] data = new byte[is.available()];
					is.read(data);
					os.write(data);
					is.close();
					os.close();
					
					Toast t = Toast.makeText(ExternalData.this, "File has been saved", Toast.LENGTH_LONG);
					t.show();
					
					//update files for the user to use
					MediaScannerConnection.scanFile(ExternalData.this, 
							new String[] {file.toString()}, 
							null, 
							new MediaScannerConnection.OnScanCompletedListener() {
								
								public void onScanCompleted(String path, Uri uri) {
									// TODO Auto-generated method stub
									Toast tt = Toast.makeText(ExternalData.this, "scan complete", Toast.LENGTH_LONG);
									tt.show();
								}
							});
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			break;
		}
	}

}
