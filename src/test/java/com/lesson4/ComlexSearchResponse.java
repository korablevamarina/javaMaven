
package com.lesson4;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "results",
        "offset",
        "number",
        "totalResults"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComlexSearchResponse {

    @JsonProperty("results")
    public List<Result> results = new ArrayList<Result>();
    @JsonProperty("offset")
    public Integer offset;
    @JsonProperty("number")
    public Integer number;
    @JsonProperty("totalResults")
    public Integer totalResults;

}