//package com.example.conga.finaltest.services;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Handler;
//import android.os.IBinder;
//import android.support.v7.app.NotificationCompat;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.conga.finaltest.R;
//import com.example.conga.finaltest.activities.Main;
//import com.example.conga.finaltest.controllers.Tools;
//import com.example.conga.finaltest.database.RssItemHelper;
//import com.example.conga.finaltest.models.RssItem;
//
//import org.apache.http.conn.ConnectTimeoutException;
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserException;
//import org.xmlpull.v1.XmlPullParserFactory;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.SocketTimeoutException;
//import java.net.URL;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Created by ConGa on 25/04/2016.
// */
//public class UpdateNewFeedService  extends Service {
//    private static String TAG = UpdateNewFeedService.class.getSimpleName();
//    private Timer mTimer;
//    private TimerTask mTimerTask;
//    private Context context;
//    private Handler handler = new Handler();
//    private NotificationCompat.Builder mBuilder;
//    private NotificationManager mNotification;
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        context = this;
//        startTimer();
//        Log.d(TAG, "start service");
//    }
//
//    public void startTimer() {
//        mTimer = new Timer();
//        initTimer();
//        mTimer.schedule(mTimerTask, 0, 5000);
//
//    }
//
//    public void initTimer() {
//        mTimerTask = new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        new ReadXmlPullParse().execute("http://vnexpress.net/rss/tin-moi-nhat.rss");
//
//                    }
//                });
//            }
//        };
//    }
//
//    private class ReadXmlPullParse extends AsyncTask<String, Integer, List<RssItem>> {
//        static final String KEY_ITEM = "item";
//        static final String KEY_TITLE = "title";
//        static final String KEY_LINK = "link";
//        static final String KEY_PUB_DATE = "pubDate";
//
//        RssItemHelper mRssItemDatabase;
////2015-11-05 04:02:57.000
//        @Override
//        protected List<RssItem> doInBackground(String... strings) {
//            HttpURLConnection httpURLConnection = null;
//            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
//            Tools tools = new Tools();
//            mRssItemDatabase = new RssItemHelper(getApplicationContext());
//            try {
//                mRssItemDatabase.open();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            RssItem curItem = new RssItem();
//            String curText = "";
//            List<RssItem> rssItems;
//            rssItems = new ArrayList<RssItem>();
//            BufferedReader buffered = null;
//
//            try {
//                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//                XmlPullParser xpp = factory.newPullParser();
//                URL url = new URL(strings[0]);
//                httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setReadTimeout(10000);
//                httpURLConnection.setConnectTimeout(10000);
//                httpURLConnection.setRequestMethod("GET");
//                httpURLConnection.setDoInput(true);
//                httpURLConnection.connect();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                buffered = new BufferedReader(new InputStreamReader(inputStream));
//                xpp.setInput(buffered);
//                int eventType = xpp.getEventType();
//                while (eventType != XmlPullParser.END_DOCUMENT) {
//                    String tagname = xpp.getName();
//                    switch (eventType) {
//                        case XmlPullParser.START_TAG:
//                            if (tagname.equalsIgnoreCase(KEY_ITEM)) {
//                                curItem = new RssItem();
//                            }
//                            break;
//                        case XmlPullParser.TEXT:
//                            curText = xpp.getText();
//
//                            break;
//                        case XmlPullParser.END_TAG:
//                            if (tagname.equalsIgnoreCase(KEY_ITEM)) {
//                                rssItems.add(curItem);
//                            } else if (tagname.equalsIgnoreCase(KEY_TITLE)) {
//                                curItem.setTitle(curText);
//                                Log.i("", curText);
//                            } else if (tagname.equalsIgnoreCase(KEY_LINK)) {
//                                curItem.setLink(curText);
//                            } else if (tagname.equals(KEY_PUB_DATE)) {
//                                curItem.setPubDate(tools.parseXmlPubDate(curText));
//                            }
//                            break;
//                        default:
//                            break;
//                    }
//
//                    eventType = xpp.next();
//                }
//
//            } catch (MalformedURLException | SocketTimeoutException | ConnectTimeoutException e) {
//                e.printStackTrace();
//                Log.d(TAG, "time out read rss ");
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (XmlPullParserException e) {
//                e.printStackTrace();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//
//            return rssItems;
//        }
//
//        @Override
//        protected void onPostExecute(List<RssItem> rssItems) {
//            super.onPostExecute(rssItems);
//            for (int i = 0; i < rssItems.size(); i++) {
//                Log.d(TAG, mRssItemDatabase.getRssItemLastPubDateKhoaTin().getTime() + "");
//                Log.d(TAG, rssItems.get(i).getPubDate().getTime() + "");
//                if (rssItems.get(i).getPubDate().getTime() > mRssItemDatabase.getRssItemLastPubDateKhoaTin().getTime()) {
//                    mNotification = (NotificationManager) getApplicationContext().
//                            getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
//                    Intent notyficationIntent = new Intent(getApplicationContext(), Main.class);
//                    //  notyficationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    notyficationIntent.putExtra("show", rssItems.get(i));
//                    notyficationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    mBuilder = (NotificationCompat.Builder) new
//                            NotificationCompat.Builder(getApplicationContext())
//                            .setContentTitle(rssItems.get(i).getTitle())
//                            .setSmallIcon(R.drawable.ic_add_alert_white_24dp);
//                    PendingIntent pendingNotificationIntent = PendingIntent.
//                            getActivity(getApplicationContext(), 0, notyficationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//                    mBuilder.setContentIntent(pendingNotificationIntent);
//                    mBuilder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
//                    mBuilder.build().flags |= Notification.FLAG_AUTO_CANCEL;
//                    mBuilder.setAutoCancel(true);
//                    mNotification.notify(1, mBuilder.build());
//                    Toast.makeText(getApplicationContext(), "Thông báo" + rssItems.get(i).getTitle() + " ", Toast.LENGTH_LONG).show();
//                    mRssItemDatabase.addNewItemRssKhoaTin(rssItems.get(i));
//                    Log.d("NEW FEEDS", rssItems.get(i) + "");
//                } else {
//                    Log.d(TAG, " DONT HAVE NEWS");
//                }
//            }
//        }
//    }
//}
