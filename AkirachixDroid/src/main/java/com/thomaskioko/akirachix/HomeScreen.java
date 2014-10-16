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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the main screen of the Application. It
 *
 * @author kioko
 */

public class HomeScreen extends ActionBarActivity implements AdapterView.OnItemClickListener {

    String[] listContent = {"Tabs Activity", "WebView"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        // Initialization
        ListView listview = (ListView) findViewById(R.id.mainList);

        //Convert the Array to ArrayList
        ArrayList<String> contentList = new ArrayList<String>();
        contentList.addAll(Arrays.asList(listContent));

        // Design the ListView
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.simple_layout, contentList);
        listview.setAdapter(stringArrayAdapter);
        listview.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {

            case 0:
                startActivity(new Intent(getApplicationContext(), TabsActivity.class));
                break;
            case 1:
                //This opens the WebViewActivity
                startActivity(new Intent(getApplicationContext(), WebViewActivity.class));
                break;

            default:
                break;

        }
    }
}
