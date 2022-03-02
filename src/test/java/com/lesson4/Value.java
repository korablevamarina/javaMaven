package com.lesson4;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "ingredients"
    })
    @Generated("jsonschema2pojo")
    public class Value {

        @JsonProperty("ingredients")
        public List<Ingredient> ingredients = new ArrayList<Ingredient>();

    }
