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
public interface Weather {
    public String getWeatherCode();
    public void setWeatherCode(String weatherCode);
    public String getWeatherDescription();
    public void setWeatherDescription(String weatherDescription);
    public Integer getTemp();
    public void setTemp(Integer temp);
    public Integer getTempMin();
    public void setTempMin(Integer tempMin);
    public Integer getTempMax();
    public void setTempMax(Integer tempMax);    
}
