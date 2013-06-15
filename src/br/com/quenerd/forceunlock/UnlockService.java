package br.com.quenerd.forceunlock;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class UnlockService extends Service implements ProximityListener {

	private static final String TAG = UnlockService.class.getSimpleName();
	private ProximitySensor mSensor;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO do something useful
		Log.d("Start", "Hello Service!");

		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		BroadcastReceiver mReceiver = new ScreenReceiver();
		registerReceiver(mReceiver, filter);

		mSensor = new ProximitySensor(this);
		mSensor.setListener(this);
		mSensor.start();
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

	@Override
	public void onProximityChanged(long time) {
		// TODO Auto-generated method stub

	}
}
