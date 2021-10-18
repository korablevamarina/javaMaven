package ru.geekbrains.qa.java2.lesson7_project.project.dto;

public class CityResponse {
    String cityName;
    String countryName;
    String cityKey;

    public CityResponse(String cityName, String countryName, String cityKey) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.cityKey = cityKey;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCityKey() {
        return cityKey;
    }
}
