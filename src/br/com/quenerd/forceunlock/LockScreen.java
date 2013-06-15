package br.com.quenerd.forceunlock;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;

@SuppressWarnings("deprecation")
public class LockScreen {
		private static LockScreen __instance;
		
	 	private KeyguardManager mKeyGuardManager;
		private KeyguardLock mLock;
		
		public LockScreen(Context context) {
			this.mKeyGuardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
			this.mLock = mKeyGuardManager.newKeyguardLock("activity_screen");
		}
		
		public static LockScreen getInstance(Context context){
			if(__instance == null){
				__instance = new LockScreen(context); 
			}
			return __instance;
		}
		public KeyguardLock getmLock() {
			return mLock;
		}
		public void setmLock(KeyguardLock mLock) {
			this.mLock = mLock;
		}
}
