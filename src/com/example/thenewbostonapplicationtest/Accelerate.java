package com.example.thenewbostonapplicationtest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Accelerate extends Activity implements SensorEventListener {

	private SensorManager sensorManager;
	private boolean color = false;
	private View view;
	private long lastUpdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// create full screen view
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accelerometer);
		view = findViewById(R.id.tvAccelerate);
		view.setBackgroundColor(Color.GREEN);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		lastUpdate = System.currentTimeMillis();

	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			getAccelerometer(event);
		}

	}

	private void getAccelerometer(SensorEvent event) {
		// TODO Auto-generated method stub
		float[] values = event.values;
		float x = values[0];
		float y = values[1];
		float z = values[2];

		float accelerationSqrt = (x * x + y * y + z * z)
				/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
		long actualTime = System.currentTimeMillis();
		if (accelerationSqrt >= 2) {
			if (actualTime - lastUpdate < 200) {
				return;
			}
			lastUpdate = actualTime;
			
			if (color) {
				view.setBackgroundColor(Color.GREEN);
				Toast.makeText(this, "Device was shuffed", Toast.LENGTH_SHORT)
				.show();
			} else {
				view.setBackgroundColor(Color.RED);
				Toast.makeText(this, "Device was shuffed", Toast.LENGTH_SHORT)
				.show();
			}
			color = !color;
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// register this class as a listener and for orientation
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//unregistered listener
		sensorManager.unregisterListener(this);
	}

}
