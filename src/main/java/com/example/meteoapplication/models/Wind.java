package com.example.meteoapplication.models;

import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    private Float speed;

    public Float getSpeed() {
        return speed;
    }
}
