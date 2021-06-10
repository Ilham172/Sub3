package com.example.mobiletugas6.networks;

import com.example.mobiletugas6.models.Movie.Movie;
import com.example.mobiletugas6.models.Movie.NowPlayingResponse;
import com.example.mobiletugas6.models.Movie.PopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiInterface {
    @GET("now_playing")
    Call<NowPlayingResponse> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}")
    Call<Movie> getMovie(
            @Path("movie_id") String id,
            @Query("api_key") String apiKey
    );

    @GET("popular")
    Call<PopularResponse> getPopular(
            @Query("api_key") String apiKey
    );
}
