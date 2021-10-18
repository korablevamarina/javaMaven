package ru.geekbrains.qa.java2.lesson7_project.project.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import ru.geekbrains.qa.java2.lesson7_project.project.dto.Temperature;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private String weatherText;
    private Temperature temperature;

    @JsonGetter("WeatherText")
    public String getWeatherText() {
        return weatherText;
    }

    @JsonSetter("WeatherText")
    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }
    @JsonGetter("Temperature")
    public Temperature getTemperature() {
        return temperature;
    }
    @JsonSetter("Temperature")
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public WeatherResponse(String weatherText, Temperature temperature) {
        this.weatherText = weatherText;
        this.temperature = temperature;
    }

    public WeatherResponse() {
    }
}

/*
[{
    "LocalObservationDateTime":"2021-10-10T21:20:00+03:00",
    "EpochTime":1633890000,
    "WeatherText":"Clear",
    "WeatherIcon":33,
    "HasPrecipitation":false,
    "PrecipitationType":null,
    "IsDayTime":false,
    "Temperature":{
        "Metric":{
            "Value":11.1,
            "Unit":"C",
            "UnitType":17
        },
        "Imperial":{
            "Value":52.0,
            "Unit":"F",
            "UnitType":18
        }
    },
    "MobileLink":"http://www.accuweather.com/en/ru/saint-petersburg/295212/current-weather/295212?lang=en-us",
    "Link":"http://www.accuweather.com/en/ru/saint-petersburg/295212/current-weather/295212?lang=en-us"
}]
 */
