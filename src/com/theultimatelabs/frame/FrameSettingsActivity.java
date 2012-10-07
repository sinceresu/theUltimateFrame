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

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

public class FrameSettingsActivity extends Activity {

	public static final String TAG = "FrameSettingsActivity";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		startService(new Intent(getApplicationContext(),FrameService.class));
	}
}
