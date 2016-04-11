package com.example.conga.finaltest.interfaces;

import com.example.conga.finaltest.models.RssItem;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ConGa on 29/03/2016.
 */
public interface IRssItemListener {

        public void addRssItem(RssItem rssItemPDT);
        public ArrayList<RssItem> getAllItem();
        public int getRssItemCount();
        public Date getRssItemLastPubDate();
    public void upDateStatusChecked(int status , int id_rss_item);
    public void upDateStatusPiority(int status, int id_rss_item);
    public void deleteTask(int id_rss_item);

}
