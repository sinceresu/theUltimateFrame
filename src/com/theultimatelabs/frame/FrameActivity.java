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
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class FrameActivity extends Activity {

	static final String TAG = "FrameActivity";
	
	private Handler mHandler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getIntent().getAction().equals("close")) {
			finish();
		}
		setContentView(R.layout.frame);

		Log.v(TAG, String.format("onCreate activity %s", this.toString()));

		startService(new Intent(getApplicationContext(), FrameService.class));

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.frameLayout);
		layout.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				Log.v(TAG, "on touch");
				finish();
				return false;
			}
		});
		
		mHandler.post(new Runnable() {
			
			public void run() {
				/*String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
				SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		        String formattedDate = df.format(c.getTime());
				((TextView) findViewById(R.id.time)).setText(currentDateTimeString);
				mHandler.postDelayed(this, 60000);*/
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();

		Log.v(TAG, String.format("onStart activity %s", this.toString()));

		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER);
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		  if (Build.VERSION.SDK_INT>11) {
		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LOW_PROFILE);
		  }
	}	

}



