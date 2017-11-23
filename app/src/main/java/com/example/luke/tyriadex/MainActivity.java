package com.example.luke.tyriadex;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ApiFragment.OnDataPass {

    private static String API_KEY = null;
    private static Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        Context context = getApplication(); //use this style of shared preferences if there's more than one activity
//        SharedPreferences sharedPref = context.getSharedPreferences("com.example.luke.tyriadex.pref", Context.MODE_PRIVATE);
        setApiKey(sharedPref.getString("newApiKey", null));
        Log.d("LOG", "Main load api key: " + getApiKey());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getApiKey() != null) {
            fragment = new CharacterFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment, "character").commit();
        }
        else {
            fragment = new ApiFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment, "api").commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onStop() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        Context context = getApplication(); //use this style of shared preferences if there's more than one activity
//        SharedPreferences sharedPref = context.getSharedPreferences("com.example.luke.tyriadex.pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("newApiKey", getApiKey());
        editor.apply();

        Log.d("LOG", "Main saving api key: " + getApiKey());

        super.onStop();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment newFragment = null;

        switch (id) {
            case R.id.nav_characters:
                newFragment = new CharacterFragment();
                break;
            case R.id.nav_bank:
                newFragment = new BankFragment();
                break;
            case R.id.nav_tp:
                //
                break;
            case R.id.nav_dailies:
                //
                break;
            case R.id.nav_api:
                newFragment = new ApiFragment();
                Bundle args = new Bundle();
                args.putString("key", getApiKey());
                newFragment.setArguments(args);
                break;
            case R.id.nav_settings:
                //
                break;
        }

        if (newFragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, newFragment).commit();
        }
        else {
            //error
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onDataPass(String data) {
        Log.d("LOG","Main api key: " + data);
        setApiKey(data);
    }

    public void setApiKey(String newApiKey) {
        API_KEY = newApiKey;
    }

    public String getApiKey() {
        return API_KEY;
    }
}
