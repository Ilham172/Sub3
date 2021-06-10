package com.example.mobiletugas6.models.Movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularResponse {
    @SerializedName("results")
    @Expose
    private List<PopularModel> popular;

    public List<PopularModel> getPopulars() {
        return popular;
    }
}
