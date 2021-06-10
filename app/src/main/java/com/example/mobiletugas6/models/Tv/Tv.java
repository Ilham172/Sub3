package com.example.mobiletugas6.models.Tv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tv {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("backdrop_path")
    @Expose
    private String poster;

    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
    }
}
