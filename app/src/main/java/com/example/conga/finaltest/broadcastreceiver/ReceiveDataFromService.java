package com.example.conga.finaltest.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.conga.finaltest.models.RssItem;
import com.example.conga.finaltest.models.RssItemParent;

import java.util.ArrayList;

/**
 * Created by ConGa on 31/03/2016.
 */
public class ReceiveDataFromService extends BroadcastReceiver {
    ArrayList<RssItem> mRssItems ;

    @Override
    public void onReceive(Context context, Intent intent) {
        mRssItems= new ArrayList<RssItem>();
        mRssItems= intent.getParcelableArrayListExtra("itemRss");
        sendToNotificationFragment();
    }
    public void sendToNotificationFragment(){
        Intent intent = new Intent(RssItemParent.PARENT_RECEIVER_KEY);
        intent.putParcelableArrayListExtra("itemArrays", mRssItems);
         //getContext().sendBroadcast(intent);
    }
}
