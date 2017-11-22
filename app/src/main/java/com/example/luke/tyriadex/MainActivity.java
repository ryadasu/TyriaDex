package com.example.luke.tyriadex;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String apiKey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        Context context = getApplication();
//        SharedPreferences sharedPref = context.getSharedPreferences("com.example.luke.tyriadex.pref", Context.MODE_PRIVATE);
        apiKey = sharedPref.getString("newApiKey", null);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = new CharacterFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        final EditText editTextApiKey = findViewById(R.id.et_api_key);
//        Button buttonSaveApiKey = findViewById(R.id.bt_submit_api);
//        buttonSaveApiKey.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (editTextApiKey.getText().toString().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Please enter an API key", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    apiKey = editTextApiKey.getText().toString();
//                }
//                //Toast.makeText(getApplicationContext(), apiKey, Toast.LENGTH_LONG).show(); //debug
//            }
//        });
//        Button buttonClearApiKey = findViewById(R.id.bt_clear_api);
//        buttonClearApiKey.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                apiKey = null;
//                editTextApiKey.setText("");
//                //Toast.makeText(getApplicationContext(), apiKey, Toast.LENGTH_LONG).show(); //debug
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        Context context = getApplication();
//        SharedPreferences sharedPref = context.getSharedPreferences("com.example.luke.tyriadex.pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("newApiKey", apiKey);
        editor.apply();

        super.onDestroy();
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
        Fragment fragment = null;

        if (id == R.id.nav_characters) {
            fragment = new CharacterFragment();
        } else if (id == R.id.nav_bank) {
            fragment = new BankFragment();
        } else if (id == R.id.nav_tp) {

        } else if (id == R.id.nav_dailies) {

        } else if (id == R.id.nav_api) {

        } else if (id == R.id.nav_settings) {

        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            //error
        }

        return true;
    }
}
