/*******************************************************************************
 * Copyright (c) 2012 rob@theUltimateLabs.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     rob@theUltimateLabs.com - initial API and implementation
 ******************************************************************************/
package com.theultimatelabs.frame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

public class FrameReceiver extends BroadcastReceiver {

	public static final String TAG = "FrameReceiver";
	private static long sLastOnTime = 0;
	private final long FORCED_OFF_THRESHOLD = 3000;

	// TODO: turn off if temp gets to high;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v(TAG, intent.getAction());

		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			int batteryStatus = context.registerReceiver(null,
					new IntentFilter(Intent.ACTION_BATTERY_CHANGED))
					.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
			boolean isCharging = batteryStatus == BatteryManager.BATTERY_STATUS_CHARGING
					|| batteryStatus == BatteryManager.BATTERY_STATUS_FULL;

			// Don't start screen saver if on for less than 3 seconds
			boolean forcedOff = (System.currentTimeMillis() - sLastOnTime < FORCED_OFF_THRESHOLD);

			if (isCharging && !forcedOff) {
				Log.v(TAG, "starting activity");
				Intent startIntent = new Intent(context, FrameActivity.class);
				startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
				startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startIntent.addFlags(Intent.FLAG_FROM_BACKGROUND);
				//startIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				
				// startIntent.putExtra("charging", true);
				startIntent.setAction("start");
				context.startActivity(startIntent);
				Log.v(TAG, "intent sent");
			}
		} else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
			sLastOnTime = System.currentTimeMillis();
		} else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {

			Intent closeItent = new Intent(context, FrameActivity.class);
			closeItent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			closeItent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			closeItent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
			// closeItent.putExtra("charging", false);
			closeItent.setAction("close");
			context.startActivity(closeItent);

		}
		else if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			context.startService(new Intent(context,FrameService.class));
		}
	}
}
