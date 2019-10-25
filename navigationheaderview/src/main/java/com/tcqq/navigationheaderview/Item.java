package com.tcqq.navigationheaderview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by raphaelbussa on 14/01/17.
 */

public class Item implements Parcelable {

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
    private int id;
    private String title;

    private Item(int id, String title) {
        this.id = id;
        this.title = title;
    }

    private Item(Parcel in) {
        id = in.readInt();
        title = in.readString();
    }

    int getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
    }

    public static class Builder {

        private int id;
        private String title;

        public Builder() {
        }

        /**
         * @param id set id of item for match onClick result
         * @return current builder instance
         */
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        /**
         * @param title set title of item
         * @return current builder instance
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * @return build Item and return it
         */
        public Item build() {
            return new Item(id, title);
        }

    }

}
