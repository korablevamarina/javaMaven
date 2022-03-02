package com.lesson4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "image",
        "imageType"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("image")
    public String image;
    @JsonProperty("imageType")
    public String imageType;

}
