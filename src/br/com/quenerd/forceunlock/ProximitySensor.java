package br.com.quenerd.forceunlock;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class ProximitySensor implements SensorEventListener {

	private static final String TAG = ProximitySensor.class.getSimpleName();
	private SensorManager mSensorManager;
	private Sensor mProximity;

	public ProximitySensor(Context context) {
		mSensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
	}

	public void start() {
		mSensorManager.registerListener(this, mProximity,
				SensorManager.SENSOR_DELAY_FASTEST);
	}

	public void stop() {
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float sensor = event.values[0];
		Log.d(TAG, Float.toString(sensor) + " " + System.currentTimeMillis());
	}
}
