package com.rnergachev.proto.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.rnergachev.proto.BR;

/**
 * Merged post information from User, Post and Comments
 *
 * Created by rnergachev on 29/06/2017.
 */

public class DetailedPost extends BaseObservable implements Parcelable {
    private int id;
    private String title;
    private String body;
    private String userName;
    private int numberOfComments;
    private String imageUrl;

    public DetailedPost() {}

    public DetailedPost(int id, String title, String body, String userName, String imageUrl) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userName = userName;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getUserName() {
        return userName;
    }

    @Bindable
    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
        notifyPropertyChanged(BR.numberOfComments);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.numberOfComments);
        dest.writeString(this.title);
        dest.writeString(this.body);
        dest.writeString(this.userName);
        dest.writeString(this.imageUrl);
    }

    private DetailedPost(Parcel in) {
        this.id = in.readInt();
        this.numberOfComments = in.readInt();
        this.title = in.readString();
        this.body = in.readString();
        this.userName = in.readString();
        this.imageUrl = in.readString();
    }

    public static final Parcelable.Creator<DetailedPost> CREATOR = new Parcelable.Creator<DetailedPost>() {
        @Override
        public DetailedPost createFromParcel(Parcel source) {
            return new DetailedPost(source);
        }

        @Override
        public DetailedPost[] newArray(int size) {
            return new DetailedPost[size];
        }
    };
}
