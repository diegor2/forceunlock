package br.com.quenerd.forceunlock;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;

public class UnlockService extends Service implements SensorEventListener{
	  private SensorManager mSensorManager;
	  private Sensor mProximity;
	  private NumberFormat numb = new DecimalFormat();
	  
	  @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
	    //TODO do something useful
		Log.d("Start", "Hello Service!");
		
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);
        
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
	    mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
	    mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_FASTEST);
	    return Service.START_NOT_STICKY;
	  }

	  @Override
	  public IBinder onBind(Intent intent) {
	  //TODO for communication return IBinder implementation
	    return null;
	  }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onSensorChanged(SensorEvent event) {
	    float sensor = event.values[0];
	    String cm = numb.format(sensor);
	    int dis = Integer.decode(cm);
	    
	    PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        
		  KeyguardManager mKeyGuardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
		  @SuppressWarnings("deprecation")
		  KeyguardLock mLock = mKeyGuardManager.newKeyguardLock("activity_service");
	
	    if(dis == 0){
	    	if(ScreenReceiver.wasScreenOn){
		    	Log.d("Near", "It's near!");
		    	 wakeLock.acquire();
		    	 mLock.disableKeyguard();
		    	 mLock = null;
	    	}
	    }else{
	    	Log.d("Away", "Don't go :(");
	    }
	     //if (!ScreenReceiver.wasScreenOn) {
		//	 mLock.reenableKeyguard();
	   	 //}
	}
}
