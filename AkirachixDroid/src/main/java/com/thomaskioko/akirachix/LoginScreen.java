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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.thomaskioko.akirachix.utils.Constants;
import com.thomaskioko.akirachix.utils.SessionManager;

import java.util.HashMap;

/**
 *
 * This is the Login Screen used to validate a user before they the access the main Application.
 *
 * @author kioko
 */

public class LoginScreen extends ActionBarActivity implements View.OnClickListener {

    boolean checkBoxState;
    EditText etUserName, etPassword;
    private static String TAG = LoginScreen.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        etUserName = (EditText) findViewById(R.id.etLoginUserName);
        etPassword = (EditText) findViewById(R.id.etLoginPassword);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        CheckBox rememberMe = (CheckBox) findViewById(R.id.cbRememberMe);
        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxState = isChecked;
                displayToastMessage(String.valueOf(checkBoxState));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_screen, menu);
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
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> userDetails = sessionManager.getUserDetails();

        String userName = userDetails.get(Constants.KEY_USER_NAME);
        String password = userDetails.get(Constants.KEY_HM_PASSWORD);

        switch (view.getId()){
            case R.id.btnLogin:
                if(etUserName.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
                    displayToastMessage("You cannot have empty Fields!!!");
                }else if(!etUserName.getText().toString().equals(userName) ||
                        !etPassword.getText().toString().equals(password)){
                    displayToastMessage("Please the credentials you registered with!!!");
                }else{
                    sessionManager.setLoginState(checkBoxState);
                    startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                }
                break;
            case R.id.btnLoginCancel:
                startActivity(new Intent(getApplicationContext(), WelcomeScreen.class));
                break;
            default:
                Log.e(TAG, "We have a problem. Sermon @code_wizard!!!");
                break;
        }
    }

    /**
     * @param message Message to be displayed
     */
    public void displayToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
