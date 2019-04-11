package com.example.meteoapplication.models;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    private Float temp;

    @SerializedName("pressure")
    private Float pressure;

    @SerializedName("humidity")
    private Float humidity;

    @SerializedName("temp_min")
    private Float temp_min;

    @SerializedName("temp_max")
    private Float temp_max;

    public Float getTemp() {
        return temp;
    }

    public Float getPressure() {
        return pressure;
    }

    public Float getHumidity() {
        return humidity;
    }

    public Float getTemp_min() {
        return temp_min;
    }

    public Float getTemp_max() {
        return temp_max;
    }
}
