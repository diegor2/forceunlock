package br.com.quenerd.forceunlock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener,
		ProximityListener {

	private PasswordManager mPasswordManager;
	private View mStart;
	private View mStop;
	private ProximitySensor mSensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPasswordManager = new PasswordManager(this);

		Intent unlock = new Intent(MainActivity.this, UnlockService.class);
		startService(unlock);

		bindViews();
		bindEvents();
		startSensor();
	}

	private void startSensor() {
		mSensor = new ProximitySensor(this);
		mSensor.setListener(this);
		mSensor.start();
	}

	private void bindViews() {
		mStart = findViewById(R.id.start);
		mStop = findViewById(R.id.stop);
	}

	private void bindEvents() {
		mStart.setOnClickListener(this);
		mStop.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start:
			mPasswordManager.startRecording();
			break;
		case R.id.stop:
			mPasswordManager.stopRecording();
			break;
		default:
			break;
		}
	}

	@Override
	public void onProximityChanged(long time) {
		if (mPasswordManager.isRecording()) {
			mPasswordManager.addGesture(time);
		}
	}
}
