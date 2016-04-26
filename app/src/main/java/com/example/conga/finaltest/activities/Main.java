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

import com.example.conga.finaltest.R;
import com.example.conga.finaltest.adapters.NavDrawerListAdapter;
import com.example.conga.finaltest.fragments.DangKiNhanTinFragment;
import com.example.conga.finaltest.fragments.KhoaTinCategoryFragment;

import com.example.conga.finaltest.fragments.OfflineFragment;
import com.example.conga.finaltest.fragments.TruongCategoryFragment;
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
        mDataList.add(new NavDrawerItem(getString(R.string.title_nav_inCountry_news), R.drawable.logo));
        mDataList.add(new NavDrawerItem(getString(R.string.khoatin), R.drawable.khoatin));
        mDataList.add(new NavDrawerItem(getString(R.string.title_nav_register_receive_new), R.drawable.regis));
        mDataList.add(new NavDrawerItem(getString(R.string.title_read_offline), R.drawable.ic_file_download_black_24dp));
        mDataList.add(new NavDrawerItem(getString(R.string.title_nav_mo_rong), R.drawable.tienich1));
        mDataList.add(new NavDrawerItem(getString(R.string.title_nav_about_appp), R.drawable.about1));

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
                fragment = new TruongCategoryFragment();
                args.putString(TruongCategoryFragment.ITEM_NAME, mDataList.get(possition)
                        .getTitle());

                break;
            case 1:
                fragment = new KhoaTinCategoryFragment();
                args.putString(KhoaTinCategoryFragment.ITEM_NAME, mDataList.get(possition)
                        .getTitle());

                break;
            case 2:
                fragment = new DangKiNhanTinFragment();
                args.putString(DangKiNhanTinFragment.ITEM_NAME, mDataList.get(possition)
                        .getTitle());
                break;
            case 3:
                fragment = new OfflineFragment();
                args.putString(OfflineFragment.ITEM_NAME, mDataList.get(possition)
                        .getTitle());
                break;
            case 4:
//                fragment = new ListViewTaskFragment();
//                args.putString(ListViewTaskFragment.ITEM_NAME, mDataList.get(possition)
//                        .getTitle());
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
