/*
   Copyright 2012 motionBEAT Inc.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

package com.ad_stir.sample.android.phonegap;

import android.os.Bundle;
import android.view.ViewGroup;

import org.apache.cordova.*;

import com.ngigroup.adstir.AdstirTerminate;
import com.ngigroup.adstir.AdstirView;

public class AdStirPhoneGapSample extends DroidGap {
	private AdstirView adstirView;
	ViewGroup layout = null;
	static final int spotNo = 枠No; // 枠Noは利用するアプリの枠Noを指定してください。

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.loadUrl("file:///android_asset/www/index.html");
		// onCreate()にここから
		layout = this.root; // 先ほどレイアウトに追加したidを指定してください。
		adstirView = new AdstirView(this, spotNo);
		layout.addView(adstirView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		// ここまでを追加
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		// onDestroy()にここから
		new AdstirTerminate(this);
		// ここまでを追加
	}

	// AdstirViewのstopメソッドを実行することにより、不要な通信を抑えることが出来ます。
	@Override
	protected void onPause() {
		super.onPause();

		// onPause()にここから
		adstirView.stop();
		ViewGroup parent = (ViewGroup) adstirView.getParent();
		if (parent != null) {
			parent.removeView(adstirView);
		}
		// ここまでを追加
	}

	// AdstirViewのstartメソッドを実行することにより、通信を再開することが出来ます。
	@Override
	protected void onResume() {
		super.onResume();

		// onResume()にここから
		int index = 0;
		while (layout.getChildAt(index) != null) {
			if (layout.getChildAt(index) == adstirView) {
				return;
			}
			index++;
		}
		adstirView = null;
		adstirView = new AdstirView(this, spotNo);
		layout.addView(adstirView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		adstirView.start();
		// ここまでを追加
	}
}
