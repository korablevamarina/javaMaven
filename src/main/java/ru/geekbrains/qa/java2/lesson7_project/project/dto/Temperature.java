package ru.geekbrains.qa.java2.lesson7_project.project.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import ru.geekbrains.qa.java2.lesson7_project.project.dto.Metric;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "Temperature")
public class Temperature {
    private Metric metric;
    @JsonGetter("Metric")
    public Metric getMetric() {
        return metric;
    }
    @JsonSetter("Metric")
    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public Temperature(Metric metric) {
        this.metric = metric;
    }

    public Temperature() {
    }
}
