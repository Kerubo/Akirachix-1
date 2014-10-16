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

package com.thomaskioko.akirachix.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.thomaskioko.akirachix.LoginScreen;

import java.util.HashMap;

/**
 * This class uses the shared preferences to handle user sessions.
 *
 * @author kioko
 */


public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context _context;

    //Constructor
    public SessionManager(Context context) {
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(Constants.PREFS_NAME, 0);
        editor = sharedPreferences.edit();
        editor.commit();
    }

    /**
     * This method stores data in the shared preferences
     *
     * @param fullName Users names
     * @param userName User Name
     * @param email    User Email
     * @param password User Password
     */
    public void createUser(String fullName, String userName, String email, String password) {

        //Store Data in the shared preferences
        editor.putString(Constants.KEY_FULL_NAME, fullName);
        editor.putString(Constants.KEY_USER_NAME, userName);
        editor.putString(Constants.KEY_EMAIL, email);
        editor.putString(Constants.KEY_PASSWORD, password);
        editor.putBoolean(Constants.KEY_STATE, true);

        editor.commit();
    }

    /**
     * This method gets user details. This will be used to login in.
     *
     * @return Data for the shared preferences
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(Constants.KEY_HM_NAME, sharedPreferences.getString(Constants.KEY_USER_NAME, null));
        user.put(Constants.KEY_HM_PASSWORD, sharedPreferences.getString(Constants.KEY_PASSWORD, null));

        return user;
    }

    /**
     * This method clears data from the shared preferences and starts the Login Activity
     */
    public void logoutUser() {
        editor.clear();
        editor.commit();

        //Start Login in Activity
        _context.startActivity(new Intent(_context, LoginScreen.class));
    }

    /**
     * This method checks if the user has logged in.
     *
     * @return state
     */
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(Constants.KEY_STATE, false);
    }

}
