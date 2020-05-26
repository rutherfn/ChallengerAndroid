package com.nicholasrutherford.challengerandroid.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Splash {

    @SerializedName("_active")
    @Expose
    public Boolean active;

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("primary_color")
    @Expose
    public String primaryColor;

    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("is_Active")
    @Expose
    public String isActive;

    public Splash() {

    }

    public Splash(Boolean active, int id, String name, String primaryColor, String url, String isActive) {
        this.active = active;
        this.id = id;
        this.name = name;
        this.primaryColor = primaryColor;
        this.url = url;
        this.isActive = isActive;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

}
