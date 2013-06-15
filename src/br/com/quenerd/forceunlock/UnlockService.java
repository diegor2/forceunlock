package br.com.quenerd.forceunlock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UnlockService extends Service {

	private static final String TAG = UnlockService.class.getSimpleName();
	private ProximitySensor mSensor;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO do something useful
		Log.d("Start", "Hello Service!");
		mSensor = new ProximitySensor(this);
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		mSensor.start();
		return null;
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		mSensor.start();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		mSensor.stop();
		return super.onUnbind(intent);
	}
}
