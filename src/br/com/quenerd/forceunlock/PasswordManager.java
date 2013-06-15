package br.com.quenerd.forceunlock;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class PasswordManager {

	private static final String TAG = PasswordManager.class.getSimpleName();
	private Context mContext;
	private SharedPreferences mPrefs;
	private List<ProximityGesture> mGestures;
	private long mStartTime;
	private long mStopTime;
	private long mLastTime;
	private boolean mIsRecording = false;

	public PasswordManager(Context context) {
		mContext = context;
		mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		mGestures = new ArrayList<ProximityGesture>();
	}

	public void startRecording() {
		mIsRecording = true;
		clearGestures();
		mStopTime = 0;
	}

	public void addGesture(long time) {
		if (0 == mStopTime) {
			mStopTime = time;
		}
		mLastTime = time;
		mGestures.add(ProximityGesture.LONG);
	}

	public void stopRecording() {
		mIsRecording = false;
		mStopTime = mLastTime;
		Log.d(TAG, mGestures.toString());
	}

	public boolean isRecording() {
		return mIsRecording;
	}

	private void clearGestures() {
		if (null != mGestures) {
			mGestures.clear();
		} else {
			mGestures = new ArrayList<ProximityGesture>();
		}
	}

}
