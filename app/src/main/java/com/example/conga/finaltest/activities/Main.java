package com.example.conga.finaltest.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.conga.finaltest.R;
import com.example.conga.finaltest.adapters.NavDrawerListAdapter;
import com.example.conga.finaltest.fragments.ListViewTaskFragment;
import com.example.conga.finaltest.fragments.ReadNewsFragment;
import com.example.conga.finaltest.models.NavDrawerItem;

import java.util.ArrayList;


public class Main extends AppCompatActivity {
    private static String TAG = Main.class.getSimpleName();
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private NavDrawerListAdapter mNavDrawerListAdapter;
    private Toolbar mToolbar;
    private ArrayList<NavDrawerItem> mDataList;
    //public List<NavItemDrawer> dataList;
    // CustomDrawerAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initializing

        mDataList = new ArrayList<NavDrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.lv_sliding_menu);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // Add Drawer Item to dataList
//        mDataList.add(new NavDrawerItem(R.string.title_nav_inCountry_news, R.drawable.express1));
//        mDataList.add(new NavDrawerItem(String.valueOf(R.string.title_nav_national_news), R.drawable.cnn1));
//        mDataList.add(new NavDrawerItem(String.valueOf(R.string.title_nav_register_receive_news), R.drawable.regis));
//        mDataList.add(new NavDrawerItem(String.valueOf(R.string.title_nav_settings), R.drawable.ic_settings_black_24dp));
//        mDataList.add(new NavDrawerItem(String.valueOf(R.string.title_nav_add_on), R.drawable.tienich1));
//        mDataList.add(new NavDrawerItem(String.valueOf(R.string.title_nav_about_app), R.drawable.about1));
        mDataList.add(new NavDrawerItem("Vn express", R.drawable.express1));
        mDataList.add(new NavDrawerItem("CNN news", R.drawable.cnn1));
        mDataList.add(new NavDrawerItem("Register", R.drawable.regis));
        mDataList.add(new NavDrawerItem("Setting", R.drawable.ic_settings_black_24dp));
        mDataList.add(new NavDrawerItem("Add-on", R.drawable.tienich1));
        mDataList.add(new NavDrawerItem("About", R.drawable.about1));
        mNavDrawerListAdapter = new NavDrawerListAdapter(this,
                mDataList);

        mDrawerList.setAdapter(mNavDrawerListAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
                syncState();// creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
                syncState();// creates call to
                // onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            SelectItem(0);
        }

    }


    public void SelectItem(int possition) {

        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
            case 0:
                fragment = new ReadNewsFragment();
                args.putString(ReadNewsFragment.ITEM_NAME, mDataList.get(possition)
                        .getTitle());
//                args.putInt(ReadNewsFragment.IMAGE_RESOURCE_ID, dataList.get(possition)
//                        .getImgResID());
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "loading...", Toast.LENGTH_SHORT).show();
                fragment = new ListViewTaskFragment();
                args.putString(ListViewTaskFragment.ITEM_NAME, mDataList.get(possition)
                        .getTitle());
//                args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
//                        .getImgResID());
                break;

            default:
                break;
        }

        fragment.setArguments(args);
        FragmentManager frgManager = getSupportFragmentManager();
        frgManager.beginTransaction().replace(R.id.main_content, fragment)
                .commit();

        mDrawerList.setItemChecked(possition, true);
        setTitle(mDataList.get(possition).getTitle());
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(menuItem)) {
            return true;
        }
        if (menuItem.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(mDrawerList);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
        //return false;
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            SelectItem(position);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
