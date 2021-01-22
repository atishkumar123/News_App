package com.atish.navigationdrawer;

import com.atish.navigationdrawer.modals.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("posts")
    Call<List<PojoClass>> getposts();
    @GET("net")
    Call<List<PojoClass>> getnet();
    @GET("top-headlines")

    Call<News> getNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey

    );
}
