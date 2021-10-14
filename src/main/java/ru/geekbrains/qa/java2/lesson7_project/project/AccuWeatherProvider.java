package ru.geekbrains.qa.java2.lesson7_project.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import ru.geekbrains.qa.java2.lesson7_project.project.dto.CityResponse;
import ru.geekbrains.qa.java2.lesson7_project.project.dto.WeatherResponse;
import ru.geekbrains.qa.java2.lesson7_project.project.enums.Periods;

import java.io.IOException;
import java.util.List;

public class AccuWeatherProvider implements WeatherProvider {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST_ENDPOINT = "forecasts";
    private static final String DAILY_ENDPOINT = "daily";
    private static final String DAYS5_ENDPOINT = "5day";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void getWeather(Periods periods) throws IOException {
        CityResponse cityResponse = detectCityKey();
        if (periods.equals(Periods.NOW)) {
            String respStr = requestWeather(cityResponse.getCityKey(), CURRENT_CONDITIONS_ENDPOINT);
            // получаем объект weatherResponse из строки
            List<WeatherResponse> weatherResponseList = objectMapper.readValue(respStr, new TypeReference<>() {
            });
            System.out.println(weatherResponseList.get(0).getTemperature().getMetric().getValue());
            System.out.println(weatherResponseList.get(0).getWeatherText());
        } else if (periods.equals(Periods.FIVE_DAYS)) {
            String respStr = requestWeather(cityResponse.getCityKey(), FORECAST_ENDPOINT);
            printNice(respStr, cityResponse.getCityName());
        }
    }

    private void printNice(String jsonResponse, String cityName) throws IOException {
        if (objectMapper.readTree(jsonResponse).size() > 0) {
            for (JsonNode node : objectMapper.readTree(jsonResponse).get("DailyForecasts")) {
                printCityDateTemp(cityName, node);
            }
        } else throw new IOException("Server returns 0 cities");
    }

    private void printCityDateTemp(String cityName, JsonNode node) {
        String date = node.at("/Date").asText();
        JsonNode tempNode = node.at("/Temperature");
        JsonNode minNode = tempNode.at("/Minimum");
        JsonNode maxNode = tempNode.at("/Maximum");
        float valueMin = minNode.at("/Value").floatValue();
        float valueMax = maxNode.at("/Value").floatValue();
        System.out.println("В городе " + cityName + " на дату " + date + " ожидается температура от " +
                valueMin + " до " + valueMax + " градусов фаренгейта.");
    }

    @NotNull
    private String requestWeather(String cityKey, String endpoint) throws IOException {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment(endpoint)
                .addPathSegment(API_VERSION);
        if (endpoint.equals(FORECAST_ENDPOINT)) {
            urlBuilder
                    .addPathSegment(DAILY_ENDPOINT)
                    .addPathSegment(DAYS5_ENDPOINT);
        }
        HttpUrl url = urlBuilder
                .addPathSegment(cityKey)
                .addQueryParameter("apikey", API_KEY)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public CityResponse detectCityKey() throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

        HttpUrl detectLocationURL = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("locations")
                .addPathSegment(API_VERSION)
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationURL)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Невозможно прочесть информацию о городе. " +
                    "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("Произвожу поиск города " + selectedCity);

        if (objectMapper.readTree(jsonResponse).size() > 0) {
            String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
            String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();
            String cityKey = objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
            System.out.println("Найден город " + cityName + " в стране " + countryName);
            return new CityResponse(cityName, countryName, cityKey);
        } else throw new IOException("Server returns 0 cities");

//        return objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
    }
}
