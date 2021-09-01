package com.hishamlweis.fonte;

import com.hishamlweis.fonte.R;

public class Items {

    String name;
    String detail;
    int imageResourceId;

    public Items(String name, String detail, int imageResourceId) {
        this.name = name;
        this.detail = detail;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}