//package com.example.conga.finaltest.fragments;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.PopupMenu;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.ListView;
//
//import com.example.conga.finaltest.R;
//import com.example.conga.finaltest.adapters.RSSAdapter;
//import com.example.conga.finaltest.adapters.ReadNewsListAdapter;
//import com.example.conga.finaltest.controllers.Tools;
//import com.example.conga.finaltest.models.RssItem;
//import com.example.conga.finaltest.utils.NetworkUtils;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
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
//
//public class TruongRssFragment extends Fragment  implements AdapterView.OnItemClickListener , View.OnClickListener{
//    public static final String ITEM_NAME = "itemName";
//    private static String TAG = TruongRssFragment.class.getSimpleName();
//   // public RecyclerView mRecyclerView;
//    ////private Toolbar mToolbar;
//   // private SwipeMenuListView swipeMenuListView;
//    private NetworkUtils mNetworkUtils;
//    private RSSAdapter mAdapter;
//    final Handler handler = new Handler();
//    private ListRssItemReceiver mListReceiver;
//
//   // private ImageButton mFloatingActionButtonAddNewTask;
//  //  private RssItemDatabaseAdapter mRssItemDatabaseAdapter;
//    private ReadNewsListAdapter mReadNewsListAdapter;
//    private ListView swipeMenuListView;
//    private ImageView mImageViewReadNews;
//
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate Notification Fragment");
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.readnewtaskfrag, container, false);
//        swipeMenuListView = (ListView) view.findViewById(R.id.listView);
//        mImageViewReadNews = (ImageView) view.findViewById(R.id.imageView_read_news);
//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//
//                .build();
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity())
//
//                .defaultDisplayImageOptions(defaultOptions)
//                .build();
//        ImageLoader.getInstance().init(config);
//
//
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ReadXmlPullParse readXML = new ReadXmlPullParse();
//                        readXML.execute("http://ued.udn.vn/rss/tin-dao-tao.rss");
//
//                    }
//                });
//
//        swipeMenuListView.setOnItemClickListener(this);
//        mImageViewReadNews.setOnClickListener(this);
//
//
//
//        return view;
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        ReadNewsListAdapter adapter = (ReadNewsListAdapter) adapterView.getAdapter();
//        RssItem item = (RssItem) adapter.getItem(i);
//        Uri uri = Uri.parse(item.getLink());
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.imageView_read_news:
//                try {
//                    PopupMenu popup = new PopupMenu(getActivity(), mImageViewReadNews);
//                    popup.getMenuInflater().inflate(R.menu.read_news, popup.getMenu());
//                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        public boolean onMenuItemClick(MenuItem item) {
//                            switch (item.getItemId()) {
//                                case R.id.action_tieu_diem:
//                                    new ReadXmlPullParse().execute("http://ued.udn.vn/rss/tin-tieu-diem.rss");
//                                    break;
//                                case R.id.action_thong_tin_dao_tao:
//                                    new ReadXmlPullParse().execute("http://ued.udn.vn/rss/tin-dao-tao.rss");
//                                    break;
//                                case R.id.action_cong_tac_sinh_vien:
//                                    new ReadXmlPullParse().execute("http://ued.udn.vn/rss/cong-tac-sinh-vien.rss");
//                                    break;
//                                case R.id.action_khao_thi_va_dam_bao_chat_luong:
//                                    new ReadXmlPullParse().execute("http://ued.udn.vn/rss/khao-thi-va-dam-bao-chat-luong.rss");
//
//                                    break;
//                                case R.id.action_khoa_hoc_sau_dai_hoc:
//                                    new ReadXmlPullParse().execute("http://ued.udn.vn/rss/khoa-hoc-hop-tac-quoc-te.rss");
//                                    break;
//                                case R.id.action_doan_thanh_nien:
//                                    new ReadXmlPullParse().execute("http://ued.udn.vn/rss/doan-thanh-nien-hoi-sinh-vien.rss");
//                                    break;
//                                case R.id.action_tin_sinh_vien:
//                                    new ReadXmlPullParse().execute("http://ued.udn.vn/rss/tin-sinh-vien.rss");
//                                    break;
//                                default:
//                                    new ReadXmlPullParse().execute("http://ued.udn.vn/rss/tin-dao-tao.rss");
//                                    break;
//
//                            }
//                            return true;
//                        }
//                    });
//                    popup.show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;
//        }
//    }
//
//    public class ReadXmlPullParse extends AsyncTask<String, Integer, List<RssItem>>  {
//        static final String KEY_ITEM = "item";
//        static final String KEY_TITLE = "title";
//        static final String KEY_LINK = "link";
//        static final String KEY_PUB_DATE = "pubDate";
//        static final String KEY_DESCRIPTION ="description";
//        static final String KEY_THUMB="thumb";
//        private SimpleDateFormat format = new SimpleDateFormat("yyyy MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
//
//
//        @Override
//        protected List<RssItem> doInBackground(String... strings) {
//            HttpURLConnection httpURLConnection = null;
//           // SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
//            Tools tools = new Tools();
//            //  mRssItemDatabase = new RssItemDatabase(getContext());
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
//                                //curItem.setImageUrl(getImage(curItem.getDescription()));
//                                rssItems.add(curItem);
//                            } else if (tagname.equalsIgnoreCase(KEY_TITLE)) {
//                                curItem.setTitle(curText);
//                                Log.i("", curText);
//                            } else if (tagname.equalsIgnoreCase(KEY_LINK)) {
//                                curItem.setLink(curText);
//                            } else if (tagname.equals(KEY_PUB_DATE)) {
//                                curItem.setPubDate(tools.parseXmlPubDate(curText));
//                            }
//                            else if(tagname.equals(KEY_DESCRIPTION)){
//                                curItem.setDescription(curText);
//                            }
//                            else if(tagname.equals(KEY_THUMB)){
//                                curItem.setImageUrl(curText);
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
//        public void onPostExecute(List<RssItem> rssItems) {
//            super.onPostExecute(rssItems);
//            mReadNewsListAdapter = new ReadNewsListAdapter(getActivity(), rssItems);
//            swipeMenuListView.setAdapter(mReadNewsListAdapter);
////            for(int i=0 ; i <rssItems.size(); i++){
////                mRssItemDatabase.addRssItem(rssItems.get(i));
////            }
////            mReadNewsListAdapter = new ReadNewsListAdapter(getActivity(), mRssItemDatabase.getAllItem());
////            swipeMenuListView.setAdapter(mReadNewsListAdapter);
//          // mReadNewsListAdapter.notifyDataSetChanged();
//        }
////            if (mRssItemDatabase.getRssItemCount() == 0) {
////                for (int i = 0; i < rssItems.size(); i++) {
////                    mRssItemDatabase.addRssItem(rssItems.get(i));
////                    Log.d(TAG, rssItems.get(i) + "");
////                }
////
////                Log.d(TAG, mRssItemDatabase.getAllItem() + "");
////
////            } else {
////                for (int i = 0; i < rssItems.size(); i++) {
////                    Log.d(TAG, mRssItemDatabase.getRssItemLastPubDate() + "");
////                    Log.d(TAG, rssItems.get(i).getPubDate() + "");
////                    if (rssItems.get(i).getPubDate().getTime() > mRssItemDatabase.getRssItemLastPubDate().getTime()) {
////                        mRssItemDatabase.addRssItem(rssItems.get(i));
////                        Log.d("NEW FEEDS", rssItems.get(i) + "");
////                    } else {
////                        Log.d(TAG, " DONT HAVE NEWS");
////                    }
////                }
////            }
////
////            mRssItemDatabase.getAllItem();
////            Log.d("Database", mRssItemDatabase.getAllItem() + "");
////
////            for (int i = 0; i < rssItems.size(); i++) {
////                try {
////                    Toast.makeText(getApplicationContext(), "hahaha", Toast.LENGTH_SHORT).show();
////                } catch (Exception e) {
////
////                    e.printStackTrace();
////                }
////          }
//        }
/////
//    private String getImage(String description) {
//        int a = description.indexOf("src=");
//        int start = description.indexOf("\"", a);
//        int end = description.indexOf("\"", start + 1);
//        Log.d("a", a + "");
//        Log.d("start", start + "");
//        Log.d("end", end + "");
//        String image = "";
//        Log.d("Image", image);
//        return image;
//    }
//
///////
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        getView().setFocusableInTouchMode(true);
//        getView().requestFocus();
//        getView().setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
//                    getActivity().finish();
//                    return true;
//                }
//                return false;
//            }
//        });
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//    }
//
//    private class ListRssItemReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            RssItem mBook = (RssItem) intent.getParcelableExtra("rssItem");
//            List<RssItem> list = new ArrayList<>();
//            list.add(mBook);
//            Log.d(TAG, mBook + "");
//            // mAdapter = new RSSAdapter(list, listener);
//            //mRecyclerView.setAdapter(mAdapter);
//            mAdapter.notifyDataSetChanged();
//        }
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_main, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
////        if (id == R.id.action_refresh) {
////            try {
//////                mAdapter = new RSSAdapter(mRssItemDatabase.getAllItem(), listener);
//////                mAdapter.notifyDataSetChanged();
//////                mRecyclerView.setAdapter(mAdapter);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////
////            //  mRecyclerView.setAdapter(mAdapter);
////            return true;
////        }
////        if (id == R.id.action_press_row_forward) {
////            Fragment listViewTaskFrag = new ListViewTaskFragment();
////            FragmentManager fragmentManager = getActivity()
////                    .getSupportFragmentManager();
////            ;
////            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////
////            fragmentTransaction.replace(R.id.content_frame, listViewTaskFrag);
////            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
////            fragmentTransaction.addToBackStack(null);
////            fragmentTransaction.commit();
////            return true;
////        }
//        return false;
//    }
//
////    private void hideViews() {
////        //    mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
//////
////        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFloatingActionButtonAddNewTask.getLayoutParams();
////        int fabBottomMargin = lp.bottomMargin;
////        mFloatingActionButtonAddNewTask.animate().translationY(mFloatingActionButtonAddNewTask.getHeight() + fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
////    }
////
////    private void showViews() {
////        //   mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
////        mFloatingActionButtonAddNewTask.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
////    }
//
//
//}
