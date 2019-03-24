package com.example.rachel.nicegrape.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Sticker implements Parcelable {
    private boolean activate;
    private String content;
    private Date createDate;

    public Sticker(boolean activate, String content, Date createDate) {
        this.activate = activate;
        this.content = content;
        this.createDate = createDate;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.activate ? (byte) 1 : (byte) 0);
        dest.writeString(this.content);
        dest.writeLong(this.createDate != null ? this.createDate.getTime() : -1);
    }

    protected Sticker(Parcel in) {
        this.activate = in.readByte() != 0;
        this.content = in.readString();
        long tmpCreateDate = in.readLong();
        this.createDate = tmpCreateDate == -1 ? null : new Date(tmpCreateDate);
    }

    public static final Parcelable.Creator<Sticker> CREATOR = new Parcelable.Creator<Sticker>() {
        @Override
        public Sticker createFromParcel(Parcel source) {
            return new Sticker(source);
        }

        @Override
        public Sticker[] newArray(int size) {
            return new Sticker[size];
        }
    };
}
