package br.com.quenerd.forceunlock;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class ProximitySensor extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;
    private NumberFormat numb = new DecimalFormat();
    private Sensor mProximity;
    private TextView tv;

	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
	    mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
	}
	
	public void onResume()
	{
	    super.onResume();
	    mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_FASTEST);
	    tv = new TextView(this);
	}
	
	public void onPause()
	{
	    mSensorManager.unregisterListener(this);
	}
	
	protected void onStop() {
	    super.onStop();
	    // The activity is no longer visible (it is now "stopped")
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
	    float sensor = event.values[0];
	    String cm = numb.format(sensor);
	    int dis = Integer.decode(cm);
	
	    if(dis == 0)
	        tv.setText("Object near phone!!");
	    else
	        tv.setText("No Object near phone!!");
	
	        setContentView(tv);
	}
}
