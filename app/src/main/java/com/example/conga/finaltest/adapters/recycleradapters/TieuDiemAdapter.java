package com.example.conga.finaltest.adapters.recycleradapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conga.finaltest.R;
import com.example.conga.finaltest.adapters.viewholders.RecyclerLisLinksRssHolder;
import com.example.conga.finaltest.controllers.OnItemClickListener;
import com.example.conga.finaltest.database.RssItemHelper;
import com.example.conga.finaltest.models.ContentRss;
import com.example.conga.finaltest.models.RssItem;
import com.example.conga.finaltest.utils.NetworkUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import constants.ConstantRssItem;

/**
 * Created by ConGa on 26/04/2016.
 */
public class TieuDiemAdapter  extends   RecyclerView.Adapter<RecyclerLisLinksRssHolder> {
    private static String TAG = TieuDiemAdapter.class.getSimpleName();
    private List<RssItem> mArrayListRssItems;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private OnItemClickListener.OnItemClickCallback onItemClickCallback;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private NetworkUtils mNetworkUtils;
    private Activity mActivity;
    private RssItemHelper mRssItemHelper;



    public TieuDiemAdapter(Activity mContext, List<RssItem> mArrayListRssItems, OnItemClickListener.OnItemClickCallback onItemClickCallback) {
        this.mArrayListRssItems = mArrayListRssItems;
        this.mActivity = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.onItemClickCallback = onItemClickCallback;
        mNetworkUtils = new NetworkUtils(mContext);
    }


    @Override
    public RecyclerLisLinksRssHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customtieudiemfragment, parent, false);
        RecyclerLisLinksRssHolder myViewHolder = new RecyclerLisLinksRssHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerLisLinksRssHolder holder, final int position) {
        holder.textViewTitleRss.setText(mArrayListRssItems.get(position).getTitle());
        holder.textViewPubDate.setText(mArrayListRssItems.get(position).getDate2());
// xu li phan download de doc offline
        holder.imageViewDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNetworkUtils.isConnectingToInternet()) {
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //       String linkTag = mArrayListRssItems.get(position).getLinkTag();
                            new SaveContentRssAsyncTask().execute();
                        }
                    });
                }
                else {
                    Toast.makeText(mActivity, R.string.network_unvalable, Toast.LENGTH_SHORT).show();
                }
//                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                        SaveContentRssAsyncTask.cancel(true);
//                    }
//                });

            }

            class SaveContentRssAsyncTask extends AsyncTask<Void, Void, Void> {
                String respondString = "";
                static final String USER_AGENT_BROWER = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36";


                @Override
                protected Void doInBackground(Void... params) {
                    Connection.Response response = null;
                    try {
                        response = Jsoup.connect(mArrayListRssItems.get(position).getLink()).timeout(100 * 10000)
                                .method(Connection.Method.POST)
                                .userAgent(ConstantRssItem.USER_AGENT_WEB)
                                .ignoreHttpErrors(true).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Map<String, String> cookies = response.cookies();
                    Document document = null;
                    try {
                        document = Jsoup.connect(mArrayListRssItems.get(position).getLink()).timeout(100 * 100000).
                                userAgent(ConstantRssItem.USER_AGENT_WEB2).
                                ignoreHttpErrors(true).method(Connection.Method.POST).cookies(cookies).
                                get();
                            Elements content = document.select("div#content");
                            // Elements elements = document.select("html");
                            for (Element element : content) {
                                respondString += element.text();
                            }

                        Log.d(TAG, respondString);
                        // lay ve cai title cua bai bao
                        String title = document.title();
                        String pubDate = mArrayListRssItems.get(position).getDate2();
                        mRssItemHelper = new RssItemHelper(mActivity);
                        try {
                            mRssItemHelper.open();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // THEM VAO CSDL
                        ContentRss contentRss = new ContentRss(title, pubDate, respondString);
                        mRssItemHelper.addNewItemContent(contentRss);
                        mRssItemHelper.closeDatabase();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);
                    Toast.makeText(mActivity, R.string.respond_when_download_complete, Toast.LENGTH_SHORT).show();


                }
            }

        });
        ImageLoader.getInstance().displayImage(mArrayListRssItems.get(position).getImageUrl(), holder.imageViewImageTitle, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                holder.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                holder.progressBar.setVisibility(View.GONE);
            }
        });
        holder.mView.setOnClickListener(new OnItemClickListener(position, onItemClickCallback));
    }


    @Override
    public int getItemCount() {
        return mArrayListRssItems.size();
    }
}
