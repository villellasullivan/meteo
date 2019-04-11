package com.example.meteoapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.meteoapplication.databinding.ActivityMainBinding;
import com.example.meteoapplication.models.Global;
import com.example.meteoapplication.models.Weather;
import com.example.meteoapplication.services.WeatherInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity implements LocationListener, Callback<Global> {

    LocationManager locationManager;
    private String latitude;
    private String longitude;
    static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    ActivityMainBinding binding;

    public interface WeatherInterface2 {
        @GET("weather")
        Call<Global> getWeather(@Query("lat") String latitude, @Query("lon") String longitude, @Query("appID") String appId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }else{
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = String.valueOf(location.getLongitude());
        latitude = String.valueOf(location.getLatitude());

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        WeatherInterface2 weatherInterface = retrofit.create(WeatherInterface2.class);
        Call<Global> call = weatherInterface.getWeather(latitude, longitude, "e7635207e7a83be8fa9925c5fc57402a");
        call.enqueue(this);
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onResponse(Call<Global> call, Response<Global> response) {
        binding.cityName.setText("Votre ville : " + response.body().getName());
        binding.temperature.setText("Vent : " + response.body().getWind().getSpeed().toString() + "km/h");
    }

    @Override
    public void onFailure(Call<Global> call, Throwable t) {
        System.out.println(t);
    }
}
