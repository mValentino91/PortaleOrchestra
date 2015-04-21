/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.weather;

/**
 *
 * @author antonio
 */
public class OwmWeather implements Weather{
    private String weatherCode;
    private String weatherDescription;
    private String [] weatherCodeValues = {"01d", "01n", "02d", "02n", "03d", "03n", "04d", "04n", "09d", "09n", "10d", "10n", "11d", "11n", "13d", "13n", "50d", "50n"};
    private Integer temp;
    private Integer tempMax;
    private Integer tempMin;
    
    
    /**
     * @return the WeatherSlug
     */
    public String getWeatherCode() {
        return weatherCode;
    }

    /**
     * @param WeatherSlug the WeatherSlug to set
     */
    public void setWeatherCode(String WeatherSlug) {
        this.weatherCode = WeatherSlug;
    }

    /**
     * @return the WeatherDescription
     */
    public String getWeatherDescription() {
        return weatherDescription;
    }

    /**
     * @param WeatherDescription the WeatherDescription to set
     */
    public void setWeatherDescription(String WeatherDescription) {
        this.weatherDescription = WeatherDescription;
    }

    /**
     * @return the weatherCodeValues
     */
    public String[] getWeatherCodeValues() {
        return weatherCodeValues;
    }

    /**
     * @return the temp
     */
    public Integer getTemp() {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    /**
     * @return the tempMax
     */
    public Integer getTempMax() {
        return tempMax;
    }

    /**
     * @param tempMax the tempMax to set
     */
    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

    /**
     * @return the tempMin
     */
    public Integer getTempMin() {
        return tempMin;
    }

    /**
     * @param tempMin the tempMin to set
     */
    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }
    
    
    
}
