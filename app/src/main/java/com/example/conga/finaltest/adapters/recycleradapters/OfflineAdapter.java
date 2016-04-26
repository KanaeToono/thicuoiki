package com.example.conga.finaltest.adapters.recycleradapters;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.conga.finaltest.R;
import com.example.conga.finaltest.database.RssItemHelper;
import com.example.conga.finaltest.models.ContentRss;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by ConGa on 26/04/2016.
 */
public class OfflineAdapter extends BaseAdapter {
    private static String TAG = OfflineAdapter.class.getSimpleName();
    private ArrayList<ContentRss> mArrayListNews;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private RssItemHelper mRssItemHelper;


    public OfflineAdapter(Context mContext, ArrayList<ContentRss> mArrayListTasks) {
        this.mArrayListNews = mArrayListTasks;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        Log.d(TAG, "CONSTRUCTOR LISTLIKENEWSFRAG");

    }

    @Override
    public int getCount() {
        return mArrayListNews == null ? 0 : mArrayListNews.size();

    }

    @Override
    public Object getItem(int pos) {
        return mArrayListNews.get(pos);
    }

    // custom item (delete, open) on swipemenu
    public int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }


    // class ViewHolder
    class ViewHolder {
        TextView textViewTitleNews;
        TextView textViewPubDate;
        ImageView imageViewTitle;
        ProgressBar mProgressBar;
    }

    //getView
    @Override
    public View getView(final int pos, View viewcontainer, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (viewcontainer == null) {
            viewcontainer = mLayoutInflater.inflate(R.layout.customofflinelistfragment, null);
            viewHolder = new ViewHolder();

            viewHolder.textViewTitleNews = (TextView) viewcontainer.findViewById(R.id.textView_title_news);
            viewHolder.textViewPubDate = (TextView) viewcontainer.findViewById(R.id.textViewPubdate);
            viewHolder.imageViewTitle = (ImageView) viewcontainer.findViewById(R.id.imageView_title_like);
            viewHolder.mProgressBar = (ProgressBar) viewcontainer.findViewById(R.id.progressBar);

            //viewHolder.imageViewDeleteTask = (ImageView) viewcontainer.findViewById(R.id.imageView_delete_item_task);
            viewcontainer.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) viewcontainer.getTag();
        }

        viewHolder.textViewTitleNews.setText(mArrayListNews.get(pos).getTitle());
        viewHolder.textViewPubDate.setText(mArrayListNews.get(pos).getPubDate());
//        ImageLoader.getInstance().displayImage(mArrayListNews.
//                        get(pos).getImageUrl(), viewHolder.imageViewTitle,
//                new ImageLoadingListener() {
//                    @Override
//                    public void onLoadingStarted(String imageUri, View view) {
//                        viewHolder.mProgressBar.setVisibility(View.VISIBLE);
//                    }
//
//                    @Override
//                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                        viewHolder.mProgressBar.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                        viewHolder.mProgressBar.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onLoadingCancelled(String imageUri, View view) {
//                        viewHolder.mProgressBar.setVisibility(View.GONE);
//                    }
//                });


        return viewcontainer;
    }
}