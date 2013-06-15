package br.com.quenerd.forceunlock;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PasswordManager {

	private Context mContext;

	private SharedPreferences mPrefs;

	private List<ProximityGesture> mGestures;

	private long mStartTime;

	private long mStopTime;

	private long mLastTime;

	public PasswordManager(Context context) {
		mContext = context;
		mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		mGestures = new ArrayList<ProximityGesture>();
	}

	public void startRecording() {
		clearGestures();
		mStopTime = 0;
	}

	public void addGesture(float value, long time) {
		if (0 == mStopTime) {
			mStopTime = time;
		}
		mLastTime = time;
		mGestures.add(ProximityGesture.LONG);
	}

	public void stopRecording() {
		mStopTime = mLastTime;
	}

	private void clearGestures() {
		if (null != mGestures) {
			mGestures.clear();
		} else {
			mGestures = new ArrayList<ProximityGesture>();
		}
	}

}
