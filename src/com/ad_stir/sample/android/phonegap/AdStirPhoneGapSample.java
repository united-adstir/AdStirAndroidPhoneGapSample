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

import com.ad_stir.AdstirTerminate;
import com.ad_stir.AdstirView;

public class AdStirPhoneGapSample extends DroidGap
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Set by <content src="index.html" /> in config.xml
        super.loadUrl(Config.getStartUrl());
        //super.loadUrl("file:///android_asset/www/index.html")
        
        // AdstirView onCreate()
        ViewGroup layout = this.root;
        AdstirView adstirView = new AdstirView(this, "MEDIA-ID", SPOT-NO);
        layout.addView(adstirView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //  / AdstirView onCreate()
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // AdstirView onDestroy()
        AdstirTerminate.init(this);
        //  / AdstirView onDestroy()
    }
}

