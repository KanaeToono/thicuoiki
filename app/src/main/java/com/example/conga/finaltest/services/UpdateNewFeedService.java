package com.example.conga.finaltest.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.conga.finaltest.controllers.Tools;
import com.example.conga.finaltest.database.databaseadapters.RssItemDatabaseAdapter;
import com.example.conga.finaltest.models.RssItem;

import org.apache.http.conn.ConnectTimeoutException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by ConGa on 30/03/2016.
 */
public class UpdateNewFeedService extends Service {
    private static String TAG = UpdateNewFeedService.class.getSimpleName();
    private Timer mTimer;
    private TimerTask mTimerTask;
    private Context context;
    private Handler handler = new Handler();


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        startTimer();
        Log.d(TAG, "start service");
    }

    public void startTimer() {
        mTimer = new Timer();
        initTimer();
        mTimer.schedule(mTimerTask, 0, 60000);

    }

    public void initTimer() {
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        new ReadXmlPullParse().execute("http://vnexpress.net/rss/tin-moi-nhat.rss");

                    }
                });
            }
        };
    }

    private class ReadXmlPullParse extends AsyncTask<String, Integer, List<RssItem>> {
        static final String KEY_ITEM = "item";
        static final String KEY_TITLE = "title";
        static final String KEY_LINK = "link";
        static final String KEY_PUB_DATE = "pubDate";

        RssItemDatabaseAdapter mRssItemDatabase;

        @Override
        protected List<RssItem> doInBackground(String... strings) {
            HttpURLConnection httpURLConnection = null;
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            Tools tools = new Tools();
            mRssItemDatabase = new RssItemDatabaseAdapter(getApplicationContext());
            mRssItemDatabase.openDB();
            RssItem curItem = new RssItem();
            String curText = "";
            List<RssItem> rssItems;
            rssItems = new ArrayList<RssItem>();
            BufferedReader buffered = null;

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                buffered = new BufferedReader(new InputStreamReader(inputStream));
                xpp.setInput(buffered);
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String tagname = xpp.getName();
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                                curItem = new RssItem();
                            }
                            break;
                        case XmlPullParser.TEXT:
                            curText = xpp.getText();

                            break;
                        case XmlPullParser.END_TAG:
                            if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                                rssItems.add(curItem);
                            } else if (tagname.equalsIgnoreCase(KEY_TITLE)) {
                                curItem.setTitle(curText);
                                Log.i("", curText);
                            } else if (tagname.equalsIgnoreCase(KEY_LINK)) {
                                curItem.setLink(curText);
                            } else if (tagname.equals(KEY_PUB_DATE)) {
                                curItem.setPubDate(tools.parseXmlPubDate(curText));
                            }
                            break;
                        default:
                            break;
                    }

                    eventType = xpp.next();
                }

            } catch (MalformedURLException | SocketTimeoutException | ConnectTimeoutException e) {
                e.printStackTrace();
                Log.d(TAG, "time out read rss ");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


            return rssItems;
        }

        @Override
        protected void onPostExecute(List<RssItem> rssItems) {
            super.onPostExecute(rssItems);

            if (mRssItemDatabase.getRssItemCount() == 0) {
                for (int i = 0; i < rssItems.size(); i++) {
                    mRssItemDatabase.addRssItem(rssItems.get(i));
                    Log.d(TAG, rssItems.get(i) + "");
                }

                Log.d(TAG, mRssItemDatabase.getAllItem() + "");

            } else {
                for (int i = 0; i < rssItems.size(); i++) {
                    Log.d(TAG, mRssItemDatabase.getRssItemLastPubDate() + "");
                    Log.d(TAG, rssItems.get(i).getPubDate() + "");
                    if (rssItems.get(i).getPubDate().getTime() > mRssItemDatabase.getRssItemLastPubDate().getTime()) {
                        mRssItemDatabase.addRssItem(rssItems.get(i));
                        Log.d("NEW FEEDS", rssItems.get(i) + "");
                    } else {
                        Log.d(TAG, " DONT HAVE NEWS");
                    }
                }
            }

            mRssItemDatabase.getAllItem();
            Log.d("Database", mRssItemDatabase.getAllItem() + "");
//
//            for (int i = 0; i < rssItems.size(); i++) {
//                try {
//                    Toast.makeText(getApplicationContext(), "hahaha", Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
