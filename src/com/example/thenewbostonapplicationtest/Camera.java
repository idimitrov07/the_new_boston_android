package com.example.thenewbostonapplicationtest;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

//doesn't work well in the emulator, test it in a device!!
//the default setting of wall paper with default icon works OK
public class Camera extends Activity implements View.OnClickListener {

	ImageButton ib;
	Button b;
	ImageView iv;
	Intent i;
	final static int cameraData = 0;
	Bitmap bmp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialize();
		//set our bitmap to the default icon image here, so we have a default wall paper
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		//decode our input(the icon) and set it = to our bmp
		bmp = BitmapFactory.decodeStream(is);
	}

	private void initialize() {
		// TODO Auto-generated method stub
		ib = (ImageButton) findViewById(R.id.ibTakePic);
		iv = (ImageView) findViewById(R.id.ivReturnedPic);
		b = (Button) findViewById(R.id.bSetWall);
		b.setOnClickListener(this);
		ib.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSetWall:
			try {
				getApplicationContext().setWallpaper(bmp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.ibTakePic:
			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i, cameraData);
			break;
			
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			iv.setImageBitmap(bmp);
			
		}
	}	
}
