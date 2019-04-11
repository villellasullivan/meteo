package com.example.meteoapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Global {
    @SerializedName("coord")
    private Coord coord;

    @SerializedName("Global")
    private List<Weather> weather;

    @SerializedName("main")
    private Main main;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("name")
    private String name;

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public String getName() {
        return name;
    }
}
