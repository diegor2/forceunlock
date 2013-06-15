package br.com.quenerd.forceunlock;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver {
    
    // THANKS JASON
    public static boolean wasScreenOn = true;
	@Override
    public void onReceive(Context context, Intent intent) {
		  
		 if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // DO WHATEVER YOU NEED TO DO HERE
	        	Log.d("la", "Lockkkkkkk");
            wasScreenOn = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // AND DO WHATEVER YOU NEED TO DO HERE
            wasScreenOn = true;
        }
    }
 
}