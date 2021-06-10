package com.example.mobiletugas6.models.Movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingResponse {
    @SerializedName("results")
    @Expose
    private List<NowPlayingModel> nowPlayings;

    public List<NowPlayingModel> getNowPlayings() {
        return nowPlayings;
    }
}
