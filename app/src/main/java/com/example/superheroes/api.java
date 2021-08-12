package com.example.superheroes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface api {


    @GET("all.json")
    Call<List<data>> getData();

}
