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
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thomaskioko.akirachix.utils.Constants;
import com.thomaskioko.akirachix.utils.SessionManager;

/**
 * This Activity allows the user to create a new account. It then stores the data in shared preferences.
 * This will be updated, allowing the app to store the data in SQLite and later into an online server.
 *
 * @author kioko
 */

public class RegisterActivity extends ActionBarActivity implements View.OnClickListener {

    EditText etUserName, etEmail, etFullName, etPassword;

    Constants myConstants;
    SessionManager sessionManager;

    private static final String TAG = RegisterActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myConstants = new Constants();
        sessionManager = new SessionManager(getApplicationContext());

        etUserName = (EditText) findViewById(R.id.etUserName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etFullName = (EditText) findViewById(R.id.etFullNames);
        etPassword = (EditText) findViewById(R.id.etPassword);

        Button register = (Button) findViewById(R.id.btnRegister);
        register.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                //Store user data in the session manager.
                sessionManager.createUser(
                        etFullName.getText().toString(),
                        etUserName.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString()
                );

                startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                break;
            default:
                Log.i(TAG, "@onClick: Wizard says NO!!!");
                break;
        }
    }
}
