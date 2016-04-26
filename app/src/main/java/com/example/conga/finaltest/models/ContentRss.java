package com.example.conga.finaltest.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by ConGa on 26/04/2016.
 */
public class ContentRss implements Parcelable {
    private static String TAG = ContentRss.class.getSimpleName();
    public int id_content;
    public String title;
    public String pubDate;
    public String content;

    public ContentRss(String title, String content, String image) {
        this.pubDate = image;
        this.title = title;
        this.content = content;
    }

    public ContentRss() {

    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public ContentRss(int id_content, String title, String content) {
        this.id_content = id_content;
        this.title = title;
        this.content = content;
        Log.d(TAG, "CREATE CONTENT WEBPAGE");
    }


    public int getId_content() {
        return id_content;
    }

    public void setId_content(int id_content) {
        this.id_content = id_content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ContentRss{" +
                "id_content=" + id_content +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public static final Parcelable.Creator<ContentRss> CREATOR = new Creator<ContentRss>() {
        public ContentRss createFromParcel(Parcel source) {
            ContentRss mContentRss = new ContentRss();
            mContentRss.id_content = source.readInt();
            mContentRss.title = source.readString();

            mContentRss.content = source.readString();

            mContentRss.pubDate = source.readString();

            return mContentRss;
        }

        public ContentRss[] newArray(int size) {
            return new ContentRss[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id_content);
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(pubDate);

    }
}