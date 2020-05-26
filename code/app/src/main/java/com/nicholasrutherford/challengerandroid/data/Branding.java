package com.nicholasrutherford.challengerandroid.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branding {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("body")
    @Expose
    public String body;

    @SerializedName("primary_color")
    @Expose
    public String primaryColor;

    @SerializedName("secondary_color")
    @Expose
    public String secondaryColor;

    @SerializedName("slug")
    @Expose
    public String slug;

    public Branding() {
    }

    public Branding(Integer id, String title, String body, String primaryColor, String secondaryColor, String slug) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.slug = slug;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
