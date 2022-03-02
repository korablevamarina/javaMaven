package com.lesson4;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name"
    })
    @Generated("jsonschema2pojo")
    public class Ingredient {

        @JsonProperty("name")
        public String name;
}
