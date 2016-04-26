package com.example.conga.finaltest.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conga.finaltest.R;
import com.example.conga.finaltest.adapters.recycleradapters.TieuDiemAdapter;
import com.example.conga.finaltest.controllers.OnItemClickListener;
import com.example.conga.finaltest.controllers.Tools;
import com.example.conga.finaltest.database.RssItemHelper;
import com.example.conga.finaltest.models.RssItem;
import com.example.conga.finaltest.utils.NetworkUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ConGa on 25/04/2016.
 */
public class ThongTinDaoTaoFragment extends Fragment {
    private static String TAG = ThongTinDaoTaoFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private NetworkUtils mNetworkUtils;
    private int mValue;
    private RssItemHelper mRssItemHelper;
    private TieuDiemAdapter mTieuDiemAdapter;
   // private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   //SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy hh:mm aaa");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, " ON CREATE TIEU DIEM FRAGMENT");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongtindaotaofragment, container, false);
        //cofig show image
        Log.d(TAG, " ON CREATE TIEU DIEM FRAGMENT");
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()

                .cacheInMemory(true)
                .cacheOnDisk(true)

                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity())

                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
        //recycler view
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_links);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNetworkUtils = new NetworkUtils(getActivity());
        // mo database
        mRssItemHelper = new RssItemHelper(getActivity());
        try {
             mRssItemHelper.open();
        }catch (Exception e){
            e.printStackTrace();
        }
        //kiem tra
         if(mRssItemHelper.getCountThongTinDaoTao() == 0 && mNetworkUtils.isConnectingToInternet()){
             getActivity().runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     ReadXmlPullParse readXML = new ReadXmlPullParse();
                     readXML.execute("http://ued.udn.vn/rss/tin-dao-tao.rss");

                 }
             });
         }
        else{
             mTieuDiemAdapter = new TieuDiemAdapter(getActivity(), mRssItemHelper.getAllThongTinDaoTao(), onItemClickCallback);
             mRecyclerView.setAdapter(mTieuDiemAdapter);
             mTieuDiemAdapter.notifyDataSetChanged();
         }

        // nhan du lieu tu dang ki nhan tin
//        try {
//            Bundle bundle = this.getArguments();
//            mValue = bundle.getInt(Constant.key);
//            if(getArguments() == null){
//                Log.d(TAG, "null rooif");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        if (mValue == 1) {


//        } else {
//            Toast.makeText(getActivity().getApplicationContext(), "Moi ban chon dang ki", Toast.LENGTH_SHORT).show();
//        }
        // KIEM TRA DU LIEU NHAN
        //mRssItemHelper = new RssItemHelper(getActivity());

//        mListRssItemAdapter = new ListRssItemAdapter(getActivity(), items, onItemClickCallback);
//        mRecyclerView.setAdapter(mListRssItemAdapter);

        return view;

    }

    public class ReadXmlPullParse extends AsyncTask<String, Integer, List<RssItem>> {
        static final String KEY_ITEM = "item";
        static final String KEY_TITLE = "title";
        static final String KEY_LINK = "link";
        static final String KEY_PUB_DATE = "pubDate";
        static final String KEY_DESCRIPTION = "description";
        static final String KEY_THUMB = "thumb";


        @Override
        protected List<RssItem> doInBackground(String... strings) {
            HttpURLConnection httpURLConnection = null;
            // SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            Tools tools = new Tools();
            //  mRssItemDatabase = new RssItemDatabase(getContext());
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
                                //curItem.setImageUrl(getImage(curItem.getDescription()));
                                rssItems.add(curItem);
                            } else if (tagname.equalsIgnoreCase(KEY_TITLE)) {
                                curItem.setTitle(curText);
                                Log.i("", curText);
                            } else if (tagname.equalsIgnoreCase(KEY_LINK)) {
                                curItem.setLink(curText);
                            } else if (tagname.equals(KEY_PUB_DATE)) {
                                curItem.setDate2(curText);
                            } else if (tagname.equals(KEY_DESCRIPTION)) {
                                curItem.setDescription(curText);
                            } else if (tagname.equals(KEY_THUMB)) {
                                curItem.setImageUrl(curText);
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
            }

            return rssItems;
        }

        @Override
        public void onPostExecute(List<RssItem> rssItems) {
            super.onPostExecute(rssItems);

            for(int i=0 ; i <rssItems.size(); i++){
                mRssItemHelper.addNewThongTinDaoTao(rssItems.get(i));
            }
          //  mReadNewsListAdapter = new ReadNewsListAdapter(getActivity(), mRssItemDatabase.getAllItem());
//            swipeMenuListView.setAdapter(mReadNewsListAdapter);
            // mReadNewsListAdapter.notifyDataSetChanged();
            mTieuDiemAdapter = new TieuDiemAdapter(getActivity(), mRssItemHelper.getAllThongTinDaoTao(), onItemClickCallback );
            mRecyclerView.setAdapter(mTieuDiemAdapter);
            mTieuDiemAdapter.notifyDataSetChanged();
        }
    }
    private OnItemClickListener.OnItemClickCallback onItemClickCallback = new OnItemClickListener.OnItemClickCallback() {
        @Override
        public void onItemClicked(View view, int position) {
            ArrayList<RssItem> mArrayList = new ArrayList<>();
            mArrayList = mRssItemHelper.getAllThongTinDaoTao();
            if (mNetworkUtils.isConnectingToInternet()) {
//
                Bundle args = new Bundle();
                args.putString("link", mArrayList.get(position).getLink());

                Fragment fragment = new ReadRssFragmnet();
                fragment.setArguments(args);
                FragmentManager frgManager = getActivity().getSupportFragmentManager();
                frgManager.beginTransaction().replace(R.id.main_content, fragment)
                        .addToBackStack(null).commit();
            }
            else {
                Toast.makeText(getActivity().getApplicationContext(), "Không có mạng!!! Vui lòng kiểm tra mạng", Toast.LENGTH_SHORT).show();
            }
        }

    };

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "on resume ");
    }
}