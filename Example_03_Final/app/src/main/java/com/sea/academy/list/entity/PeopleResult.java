package com.sea.academy.list.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PeopleResult implements Parcelable {

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("next")
    @Expose
    private String next;

    @SerializedName("previous")
    @Expose
    private String previous;

    @SerializedName("results")
    @Expose
    private List<People> results = new ArrayList<People>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<People> getResults() {
        return results;
    }

    public void setResults(List<People> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeString(this.next);
        dest.writeString(this.previous);
        dest.writeTypedList(this.results);
    }

    public PeopleResult() {
    }

    protected PeopleResult(Parcel in) {
        this.count = in.readInt();
        this.next = in.readString();
        this.previous = in.readString();
        this.results = in.createTypedArrayList(People.CREATOR);
    }

    public static final Creator<PeopleResult> CREATOR = new Creator<PeopleResult>() {
        @Override
        public PeopleResult createFromParcel(Parcel source) {
            return new PeopleResult(source);
        }

        @Override
        public PeopleResult[] newArray(int size) {
            return new PeopleResult[size];
        }
    };

}
