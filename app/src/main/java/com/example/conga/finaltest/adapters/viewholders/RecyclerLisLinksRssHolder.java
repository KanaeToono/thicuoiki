package com.example.conga.finaltest.adapters.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.conga.finaltest.R;

/**
 * Created by ConGa on 26/04/2016.
 */
public class RecyclerLisLinksRssHolder extends RecyclerView.ViewHolder {
    private static String TAG = RecyclerLisLinksRssHolder.class.getSimpleName();
    public ImageView imageViewImageTitle;
    public TextView textViewTitleRss;
    public TextView textViewPubDate;
    public Context mContext;
    public final View mView;
    public ProgressBar progressBar ;
    public ImageView imageViewDownload;
    public RecyclerLisLinksRssHolder(View view) {
        super(view);
        mView = view;
        imageViewImageTitle = (ImageView) view.findViewById(R.id.imageView_title_icon);
        textViewPubDate = (TextView) view.findViewById(R.id.textViewPubdate);
        textViewTitleRss = (TextView) view.findViewById(R.id.textView_title_news);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        imageViewDownload  = (ImageView) view.findViewById(R.id.imageView_download);

    }
}