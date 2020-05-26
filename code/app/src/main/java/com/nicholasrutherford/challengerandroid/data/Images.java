package com.nicholasrutherford.challengerandroid.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("category")
    @Expose
    public String category;

    @SerializedName("url")
    @Expose
    public String url;

    public Images() {
    }

    public Images(int id, String category, String url) {
        this.id = id;
        this.category = category;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
