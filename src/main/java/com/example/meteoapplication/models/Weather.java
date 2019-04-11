package com.example.meteoapplication.models;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("main")
    private String meteo;

    @SerializedName("description")
    private String description;

    public String getMeteo() {
        return meteo;
    }

    public String getDescription() {
        return description;
    }
}
