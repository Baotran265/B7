package com.baotran.b7;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {
    private String title;
    private String content;
    private int img_cover;
    private int views;

    public Article(String title, String content, int img_cover, int views) {
        this.title = title;
        this.content = content;
        this.img_cover = img_cover;
        this.views = views;
    }

    protected Article(Parcel in) {
        title = in.readString();
        content = in.readString();
        img_cover = in.readInt();
        views = in.readInt();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(img_cover);
        dest.writeInt(views);
    }

    // Getters và Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getImg_cover() { return img_cover; }
    public void setImg_cover(int img_cover) { this.img_cover = img_cover; }

    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }
}