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
	private List<Long> mGestureTimes;
	
	private boolean mIsRecording = false;

	public PasswordManager(Context context) {
		mContext = context;
		mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		mGestureTimes = new ArrayList<Long>();
	}

	public void startRecording() {
		mIsRecording = true;
		clearGestures();
	}

	public void addGesture(long time) {
		mGestureTimes.add(time);
	}

	public List<ProximityGesture> stopRecording() {
		mIsRecording = false;
		Log.d(TAG, mGestureTimes.toString());
		List<ProximityGesture> passwordSequence = generatePasswordSequence(mGestureTimes);
		Log.d(TAG, passwordSequence.toString());
		return passwordSequence;
	}

	public boolean isRecording() {
		return mIsRecording;
	}

	private void clearGestures() {
		if (null != mGestureTimes) {
			mGestureTimes.clear();
		} else {
			mGestureTimes = new ArrayList<Long>();
		}
	}
	
	private List<ProximityGesture> generatePasswordSequence(List<Long> mGestureTimes){
		long minTime = 999999999;
		long maxTime = 0;
		long lastTime = 0;
		List<Long> durations = new ArrayList<Long>();
		for (Long time : mGestureTimes) {
			if(lastTime == 0){
				lastTime = time;
			}else{
				Long duration =  time - lastTime;
				durations.add(duration);
				if(duration < minTime){
					minTime = duration;
				}else if(duration > maxTime){
					maxTime = duration;
				}
			}
		}
		long midTime = (maxTime+minTime)/2;
		List<ProximityGesture> passwordSequence = new ArrayList<ProximityGesture>();
		for (Long duration : durations) {
			if(duration > midTime){
				passwordSequence.add(ProximityGesture.LONG);
			}
			else{
				passwordSequence.add(ProximityGesture.SHORT);
			}
		}
		return passwordSequence;
	}

}
