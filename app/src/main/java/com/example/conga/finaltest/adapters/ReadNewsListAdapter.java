package com.example.conga.finaltest.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.conga.finaltest.R;
import com.example.conga.finaltest.controllers.Dowloader;
import com.example.conga.finaltest.models.RssItem;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Created by ConGa on 4/04/2016.
 */
public class ReadNewsListAdapter extends BaseAdapter {
    private static String TAG = ReadNewsListAdapter.class.getSimpleName();
    private List<RssItem> mArrayListRssItems;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    private Dowloader mDowloader;
    ;


    // private Integer[] mImageAlarm;
    private Integer[] mImagePiorityTask;
    private Integer[] mImageDoneTask;


    public ReadNewsListAdapter(Context mContext, List<RssItem> mArrayListRssItems) {
        this.mArrayListRssItems = mArrayListRssItems;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        //  this.mImageAlarm = mImageAlarm;
        this.mImagePiorityTask = mImagePiorityTask;
        this.mImageDoneTask = mImageDoneTask;

    }

    @Override
    public int getCount() {
        return mArrayListRssItems.size();
    }

    @Override
    public Object getItem(int pos) {
        return mArrayListRssItems.get(pos);
    }

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
        ImageView imageViewImageTitle;
        ImageView imageViewDownLoad;
        TextView textViewTitleRss;

    }

    //getView
    @Override
    public View getView(final int pos, View viewcontainer, ViewGroup viewGroup) {
        int checkAlarmTask = 0;

        final ViewHolder viewHolder;
        if (viewcontainer == null) {
            viewcontainer = mLayoutInflater.inflate(R.layout.item_list_view_read_news_fragmnet, null);
            viewHolder = new ViewHolder();
            viewHolder.imageViewImageTitle = (ImageView) viewcontainer.findViewById(R.id.imageView_title_icon);
            viewHolder.imageViewDownLoad = (ImageView) viewcontainer.findViewById(R.id.imageView_download);
        //    viewHolder.imageViewMarkRssImportant = (ImageView) viewcontainer.findViewById(R.id.imageView_mark_rss_important);
            viewHolder.textViewTitleRss = (TextView) viewcontainer.findViewById(R.id.textView_title_news);

            viewcontainer.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) viewcontainer.getTag();
        }
        viewHolder.textViewTitleRss.setText(mArrayListRssItems.get(pos).getTitle());

        // check task done or not and set icon on listview
        if (mArrayListRssItems.get(pos).getStatus_check() == 1) {
            viewHolder.textViewTitleRss.setPaintFlags(viewHolder.textViewTitleRss.getPaintFlags()
                    | Paint.STRIKE_THRU_TEXT_FLAG);

        } else {
            viewHolder.textViewTitleRss.setPaintFlags(viewHolder.textViewTitleRss.getPaintFlags()
                    & (~Paint.STRIKE_THRU_TEXT_FLAG));

        }
        // handle click imageViewMarkDoneTask


        final ProgressBar progressBar = (ProgressBar) viewcontainer.findViewById(R.id.progressBar);
        ImageLoader.getInstance().displayImage(mArrayListRssItems.get(pos).getImageUrl(), viewHolder.imageViewImageTitle, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progressBar.setVisibility(View.GONE);
            }
        });
//        viewHolder.imageViewMarkReadRss.setImageResource(mImageDoneTask[checkAlarmTask]);
//        viewHolder.imageViewMarkReadRss.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if ((Integer) view.getTag() == R.drawable.ic_done_black_18dp) {
//                    mRssItemDatabase.upDateStatusChecked(1, mArrayListRssItems.get(pos).getId_rssItem());
//                    viewHolder.imageViewMarkReadRss.setImageResource(R.drawable.ic_clear_black_18dp);
//                    Toast.makeText(mContext.getApplicationContext(), R.string.mark_done_task, Toast.LENGTH_SHORT).show();
//                    view.setTag(R.drawable.ic_clear_black_18dp);
//                    viewHolder.textViewTitleRss.setPaintFlags(viewHolder.textViewTitleRss.getPaintFlags()
//                            | Paint.STRIKE_THRU_TEXT_FLAG);
//
//                } else if ((Integer) view.getTag() == R.drawable.ic_clear_black_18dp) {
//                    mRssItemDatabase.upDateStatusChecked(0, mArrayListRssItems.get(pos).getId_rssItem());
//                    viewHolder.imageViewMarkReadRss.setImageResource(R.drawable.ic_done_black_18dp);
//                    Toast.makeText(mContext.getApplicationContext(), R.string.cancle_mark_done_task, Toast.LENGTH_SHORT).show();
//                    view.setTag(R.drawable.ic_done_black_18dp);
//                    viewHolder.textViewTitleRss.setPaintFlags(viewHolder.textViewTitleRss.getPaintFlags()
//                            & (~Paint.STRIKE_THRU_TEXT_FLAG));
//
//                }
//            }
//        });
//

        //handle turn on alarm

        // set On listview icon of piority task
        switch (mArrayListRssItems.get(pos).getStatus_piority()) {
            case 0:
                checkAlarmTask = 0;
                break;
            case 1:
                checkAlarmTask = 1;
                break;
        }


        ////
//      //  final ProgressBar progressBar = (ProgressBar) viewcontainer.findViewById(R.id.progressBar);
//        ImageLoader.getInstance().displayImage(mArrayListRssItems.get(pos).getImageUrl(), viewHolder.imageViewImageTitle, new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String imageUri, View view) {
//                progressBar.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                progressBar.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                progressBar.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onLoadingCancelled(String imageUri, View view) {
//                progressBar.setVisibility(View.GONE);
//            }
//        });
        // viewHolder..setImageResource(mImagePiorityTask[checkAlarmTask]);
        // mTaskDatabaseAdapter.closeDB();
        return viewcontainer;
    }
// handle turn on alarm


}