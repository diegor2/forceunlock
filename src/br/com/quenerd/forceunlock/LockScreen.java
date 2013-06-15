package br.com.quenerd.forceunlock;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;

public class LockScreen {
	 	private KeyguardManager mKeyGuardManager;
		@SuppressWarnings("deprecation")
		private KeyguardLock mLock;
		
		public LockScreen(Context context, KeyguardManager mKeyGuardManager, KeyguardLock mLock) {
			super();
			this.mKeyGuardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
			this.mLock = mKeyGuardManager.newKeyguardLock("activity_screen");
		}
		public KeyguardLock getmLock() {
			return mLock;
		}
		public void setmLock(KeyguardLock mLock) {
			this.mLock = mLock;
		}
}
