/*
 * Copyright (c) 2014. Thomas Kioko.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.thomaskioko.akirachix;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.thomaskioko.akirachix.utils.Constants;

/**
 * This is the initial page displayed when the Application is started for the first time.
 * @author kioko
 */

public class SplashScreen extends ActionBarActivity {

    private static final String TAG = SplashScreen.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SplashHandler handle = new SplashHandler();

        Message message = new Message();
        message.what = 0;
        handle.sendMessageDelayed(message, Constants.DELAY_TIME);
    }

    /**
     * This class gets a message and performs a task depending on the message it receives.
     */
    private class SplashHandler extends Handler {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    super.handleMessage(msg);
                    startActivity(new Intent(getApplicationContext(), WelcomeScreen.class));
                    finish();
                    break;
                default:
                    Log.d(TAG, "@SplashHandler: Error");
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
