package com.example.covidtraker;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WorldApi {

    String WorldUrl = "https://corona.lmao.ninja/v2/";

    String IndiaUrl = "https://api.apify.com/v2/";

    @GET("all")
    Call<WorldModel> getWorldData();

    @GET("key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true")
    Call<IndiaModel> getIndianData();
}
