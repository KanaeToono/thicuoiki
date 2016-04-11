package com.example.conga.finaltest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conga.finaltest.R;
import com.example.conga.finaltest.adapters.viewholders.RssViewHolder;
import com.example.conga.finaltest.interfaces.IOnItemClickListener;
import com.example.conga.finaltest.models.RssItem;

import java.util.List;

/**
 * Created by ConGa on 29/03/2016.
 */
public class RSSAdapter extends RecyclerView.Adapter<RssViewHolder> {
    public List<RssItem> items;
    private final IOnItemClickListener listener;

    // public ArrayList<RssItemParent> items;
    public RSSAdapter(List<RssItem> items, IOnItemClickListener listener) {
        this.listener =listener;
        this.items = items;
    }

//    public RSSAdapter(ArrayList<RssItemParent> arrayParents, IOnItemClickListener listener) {
//
//
//        this.listener = listener;
//    }

    @Override
    public RssViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_recycler_notification_fragmment, parent, false);


        RssViewHolder myViewHolder = new RssViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RssViewHolder rssViewHolder, int position) {
        rssViewHolder.tvSubject_rss_item.setText(items.get(position).getTitle());
//        rssViewHolder.tvPub_Date_rss_item.setText(items.get(position).getPubDate());

        rssViewHolder.bind(items.get(position), listener);
    }

    public int getBasicItemCount() {
        return items == null ? 0 : items.size();
    }


    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


}
