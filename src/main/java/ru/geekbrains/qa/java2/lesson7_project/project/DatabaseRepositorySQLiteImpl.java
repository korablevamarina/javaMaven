package ru.geekbrains.qa.java2.lesson7_project.project;

import ru.geekbrains.qa.java2.lesson7_project.project.ApplicationGlobalState;
import ru.geekbrains.qa.java2.lesson7_project.project.DatabaseRepository;
import ru.geekbrains.qa.java2.lesson8.project.entity.WeatherData;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepositorySQLiteImpl implements DatabaseRepository {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    String filename = null;
    String createTableQuery = "CREATE TABLE IF NOT EXISTS weather (\n" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
        "city TEXT NOT NULL,\n" +
        "date_time TEXT NOT NULL,\n" +
        "weather_text TEXT NOT NULL,\n" +
        "temperature REAL NOT NULL\n" +
        ");";
    String insertWeatherQuery = "INSERT INTO weather (city, date_time, weather_text, temperature) VALUES (?,?,?,?)";
    String selectWeatherQuery = "SELECT city, date_time, weather_text, temperature from weather";

    public DatabaseRepositorySQLiteImpl() {
        filename = ApplicationGlobalState.getInstance().getDbFileName();
        createTableIfNotExists();
    }

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
        return connection;
    }

    private void createTableIfNotExists() {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute(createTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean saveWeatherData(WeatherData weatherData) throws SQLException {
        try (Connection connection = getConnection();
        PreparedStatement saveWeather = connection.prepareStatement(insertWeatherQuery)) {
            saveWeather.setString(1, weatherData.getCity());
            saveWeather.setString(2, weatherData.getLocalDate());
            saveWeather.setString(3, weatherData.getText());
            saveWeather.setDouble(4, weatherData.getTemperature());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Failure on saving weather object");
    }

    @Override
    public List<WeatherData> getAllSavedData() throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement selectWeather = connection.prepareStatement(selectWeatherQuery)){
            List<WeatherData> result = new ArrayList<>();
            ResultSet rs = selectWeather.executeQuery();
            while (rs.next()) {
                result.add(new WeatherData(rs.getString("city"),
                        rs.getString("date_time"),
                        rs.getString("weather_text"),
                        rs.getDouble("temperature")));
            }
            return result;
        }
    }
}
