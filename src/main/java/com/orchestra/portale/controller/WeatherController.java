/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.orchestra.portale.weather.WeatherService;
import com.orchestra.portale.weather.OwmWeatherService;
import com.orchestra.portale.weather.Weather;
import com.orchestra.portale.weather.OwmWeather;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 *
 * @author antonio
 */
@Controller
public class WeatherController {

    @RequestMapping(value = "/getCurrentWeather", method = RequestMethod.GET)
    public @ResponseBody
    String getCurrentWeather() {
       
        WeatherService weatherService = new OwmWeatherService();
        weatherService.getCurrentWeather();
        
        Gson weather = new Gson();
        return weather.toJson(weatherService.getCurrentWeather());
    }       
    
    
}
