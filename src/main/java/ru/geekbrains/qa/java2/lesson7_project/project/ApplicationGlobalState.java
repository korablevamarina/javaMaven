package ru.geekbrains.qa.java2.lesson7_project.project;

public final class ApplicationGlobalState {

    private static ApplicationGlobalState INSTANCE;
    private String selectedCity = null;
    private final String API_KEY = "NeEXVm3xyuImscyAhYSzzTYTkwGGkPGH";//"0d1tNZJPfzzT3qGokM18FGGxAUpt7hpj"; //NeEXVm3xyuImscyAhYSzzTYTkwGGkPGH
    private final String DB_FILENAME = "application.db";

    private ApplicationGlobalState() {
    }

    // Непотокобезопасный код для упрощения
    public static ApplicationGlobalState getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ApplicationGlobalState();
        }

        return INSTANCE;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }

    public String getApiKey() {
        return this.API_KEY;
    }
    public String getDbFileName() {
        return DB_FILENAME;
    }
}
