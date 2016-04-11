package com.example.conga.finaltest.database.databaseadapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.conga.finaltest.database.databasehelper.RssItemHelper;
import com.example.conga.finaltest.interfaces.IRssItemListener;
import com.example.conga.finaltest.models.RssItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import constants.ConstantRssItem;

public class RssItemDatabaseAdapter implements IRssItemListener {
    private static String TAG = RssItemDatabaseAdapter.class.getSimpleName();
    private RssItemHelper mRssItemHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;
    private SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    public RssItemDatabaseAdapter(Context mContext) {
        this.mContext = mContext;
        mRssItemHelper = new RssItemHelper(mContext);
        Log.d(TAG, "");
    }

    // open database
    public RssItemDatabaseAdapter openDB() {
        try {
            mSqLiteDatabase = mRssItemHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "LOI ");
        }
        return this;
    }

    // close database
    public void closeDB() {
        try {

            mRssItemHelper.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRssItem(RssItem rssItemPDT) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ConstantRssItem.KEY_TITLE, rssItemPDT.getTitle());
            contentValues.put(ConstantRssItem.KEY_LINK, rssItemPDT.getLink());
            contentValues.put(ConstantRssItem.KEY_PUBDATE, format.format(rssItemPDT.getPubDate()));
            contentValues.put(ConstantRssItem.KEY_CHECKED_STATUS, rssItemPDT.getStatus_check());
            contentValues.put(ConstantRssItem.KEY_PIORITY_STATUS, rssItemPDT.getStatus_piority());
            contentValues.put(ConstantRssItem.KEY_DELETE_DATE, rssItemPDT.getStatus_delete_date());
            mSqLiteDatabase.insert(ConstantRssItem.TABLE_NAME, null, contentValues);
            mSqLiteDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("problem", e + "");
        }
    }

    @Override
    public ArrayList<RssItem> getAllItem() {
        ArrayList<RssItem> rssLists = null;
        try {
            rssLists = new ArrayList<RssItem>();
            String QUERY = " select * from " + ConstantRssItem.TABLE_NAME + " order by " +
                    ConstantRssItem.KEY_PUBDATE + " desc ";
            Cursor cursor = mSqLiteDatabase.rawQuery(QUERY, null);
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));
                    item.setLink(cursor.getString(2));
                    try {
                        item.setPubDate(format.parse(cursor.getString(3)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    item.setStatus_check(cursor.getInt(4));
                    item.setStatus_piority(cursor.getInt(5));
                    item.setStatus_delete_date(cursor.getInt(6));
                    rssLists.add(item);
                }
            }
            //  mSqLiteDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("problem", e + "");
        }
        return rssLists;
    }

    @Override
    public int getRssItemCount() {
        int num = 0;
        try {
            String QUERY = "SELECT * FROM " + ConstantRssItem.TABLE_NAME;
            Cursor cursor = mSqLiteDatabase.rawQuery(QUERY, null);
            num = cursor.getCount();
            mSqLiteDatabase.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }

    @Override
    public Date getRssItemLastPubDate() {
        Date date = null;
        try {
            String QUERY = " SELECT * FROM " + ConstantRssItem.TABLE_NAME +
                    " ORDER BY " + ConstantRssItem.KEY_PUBDATE + " DESC LIMIT 1";

            Cursor cursor = mSqLiteDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst()) {
                date = format.parse(cursor.getString(cursor.getColumnIndex(ConstantRssItem.KEY_PUBDATE)));
                Log.d(TAG, date + "");
            }
            // mSqLiteDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    @Override
    public void upDateStatusChecked(int status, int id_rss_item) {

    }

    @Override
    public void upDateStatusPiority(int status, int id_rss_item) {

    }

    @Override
    public void deleteTask(int id_rss_item) {

    }
}

