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

package com.thomaskioko.akirachix.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.thomaskioko.akirachix.model.User;
import com.thomaskioko.akirachix.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * This class extends {@link android.database.sqlite.SQLiteOpenHelper}.
 * It handles all CRUD Operations. (CREATE, READ, UPDATE, DELETE)
 *
 * @author kioko
 */

public class DataBaseHandler extends SQLiteOpenHelper {


    //Get the name of the class.
    private static final String TAG = DataBaseHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDataBase";
    private static final String TABLE_USER_DETAILS = "userDetails";

    //Database Field Values
    private static final String TABLE_COLUMN_ID = "id";
    private static final String TABLE_COLUMN_FULL_NAME = "fullNames";
    private static final String TABLE_COLUMN_USER_NAME = "userNames";
    private static final String TABLE_COLUMN_USER_PASSWORD = "password";
    private static final String TABLE_COLUMN_EMAIL = "email";
    private static final String TABLE_COLUMN_PHONE_NUMBER = "phoneNumber";


    //Constructor
    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * This method is called when creating the database
     *
     * @param sqLiteDatabase Database.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCommand = "CREATE TABLE " + TABLE_USER_DETAILS + "("
                + TABLE_COLUMN_ID + "INTEGER AUTO INCREMENT PRIMARY KEY, "
                + TABLE_COLUMN_FULL_NAME + "TEXT, "
                + TABLE_COLUMN_USER_NAME + "TEXT, "
                + TABLE_COLUMN_EMAIL + "TEXT, "
                + TABLE_COLUMN_USER_PASSWORD + "TEXT, "
                + TABLE_COLUMN_PHONE_NUMBER + "TEXT"
                + ");";

        sqLiteDatabase.execSQL(createCommand);

    }

    /**
     * This method is called when upgrading the database.
     *
     * @param sqLiteDatabase Database
     * @param oldVersion     Old database version
     * @param newVersion     New database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(TAG, "Warning!! You are upgrading your Database Version: " + newVersion);

    }

    /**
     * This method INSERTS data to the database
     *
     * @param user User Model
     */
    public void addUserDetails(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.KEY_FULL_NAME, user.getFullName());
        contentValues.put(Constants.KEY_USER_NAME, user.getUserName());
        contentValues.put(Constants.KEY_PASSWORD, user.getPassword());
        contentValues.put(Constants.KEY_EMAIL, user.getEmail());

        sqLiteDatabase.insert(TABLE_USER_DETAILS, null, contentValues);

    }

    /**
     *
     * @return User details (List)
     */
    public List<User> getUserDetails() {
        List<User> userList = new ArrayList<User>();
        String selectCommand = "SELECT * FROM " + TABLE_USER_DETAILS;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectCommand, null);

        //Loop through the Result
        if (cursor.moveToFirst()) {
            do {
                String fullNames = cursor.getString(1);
                String userName = cursor.getString(2);
                String email = cursor.getString(3);
                String password = cursor.getString(4);
                String phoneNumber = cursor.getString(5);

                User userModel = new User();
                userModel.setFullName(fullNames);
                userModel.setUserName(userName);
                userModel.setPassword(password);
                userModel.setEmail(email);
                userModel.setPhoneNumber(phoneNumber);

                userList.add(userModel);

            } while (cursor.moveToNext());
        }

        return userList;
    }
}
