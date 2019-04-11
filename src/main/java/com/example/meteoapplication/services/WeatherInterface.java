package com.example.meteoapplication.services;

import com.example.meteoapplication.models.Global;
import com.example.meteoapplication.models.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {
    @GET("weather/")
    Call<Global> getWeather(@Query("lat") String latitude, @Query("lng") String longitude);
}
