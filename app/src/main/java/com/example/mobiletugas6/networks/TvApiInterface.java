package com.example.mobiletugas6.networks;

import com.example.mobiletugas6.models.Tv.AiringResponse;
import com.example.mobiletugas6.models.Tv.Tv;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvApiInterface {
    @GET("airing_today")
    Call<AiringResponse> getAiring(
            @Query("api_key") String apiKey
    );

    @GET("tv/{tv_id}")
    Call<Tv> getTv(
            @Path("tv_id") String id,
            @Query("api_key") String apiKey
    );
}
