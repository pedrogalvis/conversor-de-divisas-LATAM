package com.example.currencyconverter;

import com.google.gson.annotations.SerializedName;

public class RateResponse {
    @SerializedName("conversion_rate") // Mapea el campo JSON "conversion_rate" al atributo "conversionRate"
    private double conversionRate;

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }
}