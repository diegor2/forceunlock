package br.com.quenerd.forceunlock;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class UnlockService extends Service implements SensorEventListener {

	private static final String TAG = UnlockService.class.getSimpleName();
	private SensorManager mSensorManager;
	private Sensor mProximity;
	private NumberFormat numb = new DecimalFormat();

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO do something useful
		Log.d("Start", "Hello Service!");
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		mSensorManager.registerListener(this, mProximity,
				SensorManager.SENSOR_DELAY_NORMAL);
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO for communication return IBinder implementation
		return null;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRebind(Intent intent) {
		mSensorManager.registerListener(this, mProximity,
				+SensorManager.SENSOR_DELAY_NORMAL);
		super.onRebind(intent);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		mSensorManager.unregisterListener(this);
		return super.onUnbind(intent);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float sensor = event.values[0];
		String cm = numb.format(sensor);
		int dis = Integer.decode(cm);

		Log.d(TAG, Float.toString(sensor));

		if (dis == 0) {
			Log.d("Near", "It's near!");
		} else {
			Log.d("Away", "Don't go :(");
		}
	}
}
