package com.lesson4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.processing.Generated;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "status",
            "id"
    })
    @Generated("jsonschema2pojo")
    public class AddMealResponse {

        @JsonProperty("status")
        public String status;
        @JsonProperty("id")
        public Integer id;

}
