package com.example.mobiletugas6.models.Tv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AiringResponse {
    @SerializedName("results")
    @Expose
    private List<AiringTodayModel> airing;

    public List<AiringTodayModel> getAirings() {
        return airing;
    }
}
