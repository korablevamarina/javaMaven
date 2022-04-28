package ru.geekbrains.qa.lesson5;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.geekbrains.qa.lesson5.Product;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetCategoryResponse {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("products")
    private List<Product> products = new ArrayList<>();
}
