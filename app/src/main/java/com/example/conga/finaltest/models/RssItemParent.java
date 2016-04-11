package com.example.conga.finaltest.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ConGa on 31/03/2016.
 */
public class RssItemParent implements Parcelable {
    private static String TAG = RssItemParent.class.getSimpleName();
    private ArrayList<RssItem> mArrayListRssItem;
    public static final String PARENT_RECEIVER_KEY = "ParentReceiver";

    public RssItemParent() {
        mArrayListRssItem = new ArrayList<RssItem>();
    }

    public RssItemParent(Parcel source) {
        this();
        source.readTypedList(mArrayListRssItem,RssItem.CREATOR);
    }

    public ArrayList<RssItem> getmArrayListRssItem() {
        return mArrayListRssItem;
    }

    public void setmArrayListRssItem(ArrayList<RssItem> mArrayListRssItem) {
        this.mArrayListRssItem = mArrayListRssItem;
    }
    public static  final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new RssItemParent(parcel);
        }
        @Override
        public RssItemParent[] newArray(int size) {
            return new RssItemParent[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mArrayListRssItem);

    }
}
