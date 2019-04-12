package com.example.meteoapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.meteoapplication.databinding.ActivitySearchedCityBinding;
import com.example.meteoapplication.models.Global;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class SearchedCity extends AppCompatActivity implements Callback<Global> {

    ActivitySearchedCityBinding binding;
    static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";


    public interface WeatherInterface2 {
        @GET("weather")
        Call<Global> getWeather(@Query("q") String city, @Query("appID") String appId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_searched_city);


        Intent i = getIntent();
        String name = i.getStringExtra("city");
        System.out.println(name);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        SearchedCity.WeatherInterface2 weatherInterface = retrofit.create(SearchedCity.WeatherInterface2.class);
        Call<Global> call = weatherInterface.getWeather(name, "e7635207e7a83be8fa9925c5fc57402a");
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<Global> call, Response<Global> response) {
        String city = response.body().getName();
        Integer temperature = Math.round(response.body().getMain().getTemp() - 273);
        String description = response.body().getWeather().get(0).getDescription();
        Integer temp_min = Math.round(response.body().getMain().getTemp_min() - 273);
        Integer temp_max = Math.round(response.body().getMain().getTemp_max() - 273);

        Float pression = response.body().getMain().getPressure();
        Float humidity = response.body().getMain().getHumidity();

        binding.textCityView.setText(city);
        binding.textDescriptionView.setText(description);
        binding.textTemperatureView.setText(temperature + "°");
        binding.textMoreView.setText("Temperature min : " + temp_min + "° Temperature max : " + temp_max + "°");
        binding.textViewPression.setText("Pression : " + pression + "Humidité : " + humidity);
    }

    @Override
    public void onFailure(Call<Global> call, Throwable t) {
        System.out.println(t);
    }
}
