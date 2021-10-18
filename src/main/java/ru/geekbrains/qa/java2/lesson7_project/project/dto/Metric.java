package ru.geekbrains.qa.java2.lesson7_project.project.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "Metric")
public class Metric {
    private Double value;
    private String unit;
    private Integer unitType;
    @JsonGetter("Value")
    public Double getValue() {
        return value;
    }
    @JsonSetter("Value")
    public void setValue(Double value) {
        this.value = value;
    }
    @JsonGetter("Unit")
    public String getUnit() {
        return unit;
    }
    @JsonSetter("Unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }
    @JsonGetter("UnitType")
    public Integer getUnitType() {
        return unitType;
    }
    @JsonSetter("UnitType")
    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public Metric(Double value, String unit, Integer unitType) {
        this.value = value;
        this.unit = unit;
        this.unitType = unitType;
    }

    public Metric() {
    }
}
