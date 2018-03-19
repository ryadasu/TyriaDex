package com.example.luke.tyriadex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.SubMenu;
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
    private static Fragment fragment = null;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        setApiKey(sharedPref.getString("newApiKey", null));
        Log.d("LOG", "Main load api key: " + getApiKey());

        if (savedInstanceState != null) {
            //Restore the fragment's instance
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
        }

        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment, "currentFragment").commit();
        }
        else {
            if (getApiKey() != null) {
                int id = R.id.nav_characters;
                selectItem(id);
            }
            else {
                int id = R.id.nav_api;
                selectItem(id);
            }
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

    public void selectItem(final int itemId) {
        MenuItem item = new MenuItem() {
            @Override
            public int getItemId() {
                return itemId;
            }

            @Override
            public int getGroupId() {
                return 0;
            }

            @Override
            public int getOrder() {
                return 0;
            }

            @Override
            public MenuItem setTitle(CharSequence title) {
                return null;
            }

            @Override
            public MenuItem setTitle(int title) {
                return null;
            }

            @Override
            public CharSequence getTitle() {
                return null;
            }

            @Override
            public MenuItem setTitleCondensed(CharSequence title) {
                return null;
            }

            @Override
            public CharSequence getTitleCondensed() {
                return null;
            }

            @Override
            public MenuItem setIcon(Drawable icon) {
                return null;
            }

            @Override
            public MenuItem setIcon(int iconRes) {
                return null;
            }

            @Override
            public Drawable getIcon() {
                return null;
            }

            @Override
            public MenuItem setIntent(Intent intent) {
                return null;
            }

            @Override
            public Intent getIntent() {
                return null;
            }

            @Override
            public MenuItem setShortcut(char numericChar, char alphaChar) {
                return null;
            }

            @Override
            public MenuItem setNumericShortcut(char numericChar) {
                return null;
            }

            @Override
            public char getNumericShortcut() {
                return 0;
            }

            @Override
            public MenuItem setAlphabeticShortcut(char alphaChar) {
                return null;
            }

            @Override
            public char getAlphabeticShortcut() {
                return 0;
            }

            @Override
            public MenuItem setCheckable(boolean checkable) {
                return null;
            }

            @Override
            public boolean isCheckable() {
                return false;
            }

            @Override
            public MenuItem setChecked(boolean checked) {
                return null;
            }

            @Override
            public boolean isChecked() {
                return false;
            }

            @Override
            public MenuItem setVisible(boolean visible) {
                return null;
            }

            @Override
            public boolean isVisible() {
                return false;
            }

            @Override
            public MenuItem setEnabled(boolean enabled) {
                return null;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public boolean hasSubMenu() {
                return false;
            }

            @Override
            public SubMenu getSubMenu() {
                return null;
            }

            @Override
            public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
                return null;
            }

            @Override
            public ContextMenu.ContextMenuInfo getMenuInfo() {
                return null;
            }

            @Override
            public void setShowAsAction(int actionEnum) {

            }

            @Override
            public MenuItem setShowAsActionFlags(int actionEnum) {
                return null;
            }

            @Override
            public MenuItem setActionView(View view) {
                return null;
            }

            @Override
            public MenuItem setActionView(int resId) {
                return null;
            }

            @Override
            public View getActionView() {
                return null;
            }

            @Override
            public MenuItem setActionProvider(ActionProvider actionProvider) {
                return null;
            }

            @Override
            public ActionProvider getActionProvider() {
                return null;
            }

            @Override
            public boolean expandActionView() {
                return false;
            }

            @Override
            public boolean collapseActionView() {
                return false;
            }

            @Override
            public boolean isActionViewExpanded() {
                return false;
            }

            @Override
            public MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
                return null;
            }
        };

        onNavigationItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment newFragment = null;
        Bundle args = new Bundle();

        switch (id) {
            case R.id.nav_characters:
                newFragment = new CharacterFragment();
                args.putString("key", getApiKey());
                newFragment.setArguments(args);
                break;
            case R.id.nav_bank:
                newFragment = new BankFragment();
                args.putString("key", getApiKey());
//                args.putParcelable();
                newFragment.setArguments(args);
                break;
            case R.id.nav_tp:
                newFragment = new TradingFragment();
                args.putString("key", getApiKey());
                newFragment.setArguments(args);
                break;
            case R.id.nav_wallet:
                newFragment = new WalletFragment();
                args.putString("key", getApiKey());
                newFragment.setArguments(args);
                break;
            case R.id.nav_api:
                newFragment = new ApiFragment();
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
            fragment = newFragment;
        }
        else {
            Log.e("LOG", "No fragment was created by nav drawer");
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onDataPass(String data) {
        Log.d("LOG","Main received new api key: " + data);
        setApiKey(data);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("newApiKey", getApiKey());
        editor.apply();

        Log.d("LOG", "Main saving api key: " + getApiKey());
    }

    public void setApiKey(String newApiKey) {
        API_KEY = newApiKey;
    }

    public String getApiKey() {
        return API_KEY;
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (fragment != null) {
            getSupportFragmentManager().putFragment(outState, "currentFragment", fragment);
        }
        else {
            Log.e("LOG", "No fragment in main activity");
        }


    }
}