/*
 * Copyright 2015 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cs442.amalviy1.freefood_at_iit.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.cs442.amalviy1.freefood_at_iit.R;

public class AttractionListActivity extends AppCompatActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback,ActionBar.TabListener{

    private static final int PERMISSION_REQ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add( R.id.container, new AttractionListFragment())
                    .commit();
        }

//        ActionBar bar = getSupportActionBar();
//        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        ActionBar.Tab TabA= bar.newTab().setIcon(R.drawable.ic_action_refresh).setText("Latest Feed");
//        ActionBar.Tab TabB= bar.newTab().setIcon(R.drawable.ic_border_color_black_48dp).setText("New Post");
//        AttractionListFragment fragmentA= new AttractionListFragment();
//        bar.addTab(TabA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //   UtilityService.requestLocation(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater mif= getMenuInflater();
        mif.inflate(R.menu.main_action_bar,menu);
        mif.inflate(R.menu.main, menu);
        return  super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.account:

                //  startActivity(new Intent(getApplicationContext(),account.class));


//                UtilityService.triggerWearTest(this, false);
//                showDebugDialog(R.string.action_test_notification,
//                        R.string.action_test_notification_dialog);
                return true;
            case R.id.setting:
                //    startActivity(new Intent(getApplicationContext(),settings_ui.class));
//                UtilityService.triggerWearTest(this, true);
//                showDebugDialog(R.string.action_test_microapp,
//                        R.string.action_test_microapp_dialog);
                return true;
            case R.id.logout:
                startActivity(new Intent(getApplicationContext(),Startup.class));
//                boolean geofenceEnabled = Utils.getGeofenceEnabled(this);
//                Utils.storeGeofenceEnabled(this, !geofenceEnabled);
//                Toast.makeText(this, geofenceEnabled ?
//                        "Debug: Geofencing trigger disabled" :
//                        "Debug: Geofencing trigger enabled", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Permissions request result callback
     */
    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQ:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fineLocationPermissionGranted();
                }
        }
    }

    /**
     * Request the fine location permission from the user
     */
    private void requestFineLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQ);
    }

    /**
     * Run when fine location permission has been granted
     */
    private void fineLocationPermissionGranted() {
//        UtilityService.addGeofences(this);
//        UtilityService.requestLocation(this);
    }




    private void showDebugDialog(int titleResId, int bodyResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(titleResId)
                .setMessage(bodyResId)
                .setPositiveButton(android.R.string.ok, null);
        builder.create().show();
    }


    public void postnew(View view)
    {
        Intent intent = new Intent(this, PostNew.class);
        startActivity(intent);
    }
    public void updateFeed(View view)
    {
        Intent intent = new Intent(this, RetriveData.class);
        startActivity(intent);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}