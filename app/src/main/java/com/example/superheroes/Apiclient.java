package com.example.superheroes;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiclient {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit=new Retrofit.Builder().
                baseUrl("https://akabab.github.io/superhero-api/api/").
                addConverterFactory(GsonConverterFactory.create()).
                client(okHttpClient).
                build();


        return retrofit;
    }

    public static api getapi1(){
        api api2= getRetrofit().create(api.class);

        return api2;
    }
}
