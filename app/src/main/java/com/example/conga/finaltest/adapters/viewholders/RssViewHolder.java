package com.example.conga.finaltest.adapters.viewholders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conga.finaltest.R;
import com.example.conga.finaltest.interfaces.IOnItemClickListener;
import com.example.conga.finaltest.models.RssItem;

/**
 * Created by ConGa on 3/04/2016.
 */
public class RssViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    public TextView tvSubject_rss_item;
    TextView tvPub_Date_rss_item;
    ImageView imageView_title_icon;
    ImageView imageView_mark_rss_item_important;
    ImageView imageView_mark_rss_item_done;


    public RssViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        tvSubject_rss_item = (TextView) itemView.findViewById(R.id.textView_subject_rss);
        //    tvPub_Date_rss_item = (TextView) itemView.findViewById(R.id.textView_pub_date_rss);
        imageView_title_icon = (ImageView) itemView.findViewById(R.id.imageView_title_icon);
        imageView_mark_rss_item_important = (ImageView) itemView.findViewById(R.id.imageView_mark_rss_item_important);
        imageView_mark_rss_item_done = (ImageView) itemView.findViewById(R.id.imageView_mark_readed_rss);

    }

    public void bind(final RssItem rssItem, final IOnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(rssItem);
            }
        });
    }
}
