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

import java.io.File;
import java.io.IOException;

import android.R.xml;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.media.MediaRecorder.AudioSource;
import android.os.IBinder;
import android.util.Log;

public class FrameService extends Service {

	static final String TAG = "FrameService";
	private AudioRecord mAudioRecord;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.v(TAG, "Service Started\n");
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

		registerReceiver(new FrameReceiver(), filter);
		
	}

	

}
