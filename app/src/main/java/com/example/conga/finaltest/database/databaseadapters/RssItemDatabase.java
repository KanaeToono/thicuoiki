package com.example.conga.finaltest.database.databaseadapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.conga.finaltest.interfaces.IRssItemListener;
import com.example.conga.finaltest.models.RssItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import constants.ConstantRssItem;

/**
 * Created by ConGa on 29/03/2016.
 */
public class RssItemDatabase extends SQLiteOpenHelper implements IRssItemListener {
    private static String TAG = RssItemDatabase.class.getSimpleName();
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "RssItem.db";
    private static final String TABLE_NAME = "RssItem_table";
    private static final String KEY_ID = "_id";
    private static final String KEY_TITLE = "_title";
    private static final String KEY_LINK = "_link";
    private static final String KEY_PUBDATE = "_pubDate";
    private static final String KEY_CHECKED_STATUS= "_status";
    private static final String KEY_PIORITY_STATUS ="_piority";
    private static final String KEY_DELETE_DATE ="_delete_date";
    private SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT(100)," +
            KEY_LINK + " TEXT(100)," + KEY_PUBDATE + " DATE,"+ KEY_CHECKED_STATUS +" INTEGER,"
            + KEY_PIORITY_STATUS +" INTEGER," + KEY_DELETE_DATE + " INTEGER );";
    String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public RssItemDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(TAG, "CREATE DATABASE");
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        try {
            database.execSQL(CREATE_TABLE);
            Log.d(TAG, TABLE_NAME);
            Log.d("CREATE TABLE", "CREATE TABLE SUCESSFULLY");
        } catch (Exception e){
            e.printStackTrace();
            Log.d(TAG," can not create table");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(DROP_TABLE);
        onCreate(database);
    }

    @Override
    public void addRssItem(RssItem rssItemPDT) {
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, rssItemPDT.getTitle());
            contentValues.put(KEY_LINK, rssItemPDT.getLink());
            contentValues.put(KEY_PUBDATE,format.format(rssItemPDT.getPubDate()));
            contentValues.put(KEY_CHECKED_STATUS, rssItemPDT.getStatus_check());
            contentValues.put(KEY_PIORITY_STATUS, rssItemPDT.getStatus_piority());
            contentValues.put(KEY_DELETE_DATE, rssItemPDT.getStatus_delete_date());
            database.insert(TABLE_NAME, null, contentValues);
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("problem", e + "");
        }

    }

    @Override
    public ArrayList<RssItem> getAllItem() {
        SQLiteDatabase database = this.getWritableDatabase();
        ArrayList<RssItem> rssLists = null;
        try {
            rssLists = new ArrayList<RssItem>();
            String QUERY = " select * from " + TABLE_NAME +" order by " + KEY_PUBDATE + " desc ";
            Cursor cursor = database.rawQuery(QUERY, null);
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));
                    item.setLink(cursor.getString(2));
                    try {
                        item.setPubDate(format.parse(cursor.getString(3)));
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                    item.setStatus_check(cursor.getInt(4));
                    item.setStatus_piority(cursor.getInt(5));
                    item.setStatus_delete_date(cursor.getInt(6));
                    rssLists.add(item);
                }
            }
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("problem", e + "");
        }
        return rssLists;
    }

    @Override
    public int getRssItemCount() {
        int num = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String QUERY = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            num = cursor.getCount();
            db.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }

    @Override
    public Date getRssItemLastPubDate() {
        SQLiteDatabase db = this.getWritableDatabase();
        Date date = null;
        try {
            String QUERY = " SELECT * FROM " + TABLE_NAME +
             " ORDER BY " + KEY_PUBDATE + " DESC LIMIT 1";

            Cursor cursor = db.rawQuery(QUERY, null);
            if (cursor.moveToFirst()) {
                    date = format.parse(cursor.getString(cursor.getColumnIndex(KEY_PUBDATE)));
                Log.d(TAG, date +"");
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    @Override
    public void upDateStatusChecked(int status, int id_rss_item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ConstantRssItem.KEY_CHECKED_STATUS, status);
        try {
            if (db.update(ConstantRssItem.TABLE_NAME, values, ConstantRssItem.KEY_ID + "=" + id_rss_item, null) > 0)
                Log.d(TAG, "UPDATE stt SUCCESS");
            else
                Log.d(TAG, "FAIL");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upDateStatusPiority(int status, int id_rss_item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ConstantRssItem.KEY_PIORITY_STATUS, status);
        try {
            if (db.update(ConstantRssItem.TABLE_NAME, values, ConstantRssItem.KEY_ID + "=" + id_rss_item, null) > 0)
                Log.d(TAG, "UPDATE stt SUCCESS");
            else
                Log.d(TAG, "FAIL");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int id_rss_item) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(ConstantRssItem.TABLE_NAME, ConstantRssItem.KEY_ID
                    + " =" + id_rss_item, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
