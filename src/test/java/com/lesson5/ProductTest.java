package com.lesson5;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.qa.lesson5.Category;
import ru.geekbrains.qa.lesson5.Product;
import ru.geekbrains.qa.lesson5.service.ProductService;
import ru.geekbrains.qa.lesson5.util.RetrofitUtils;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProductTest {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();

    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle(Category.FOOD.title)
                .withPrice((int) (Math.random() * 10000));
    }

    @Test
    @SneakyThrows
    void createProductInFoodCategoryTest() {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        String title = response.body().getTitle();
        assertThat(title, CoreMatchers.is(faker.food().ingredient()));
        String categoryTitle = response.body().getCategoryTitle();
        assertThat(categoryTitle, CoreMatchers.is(Category.FOOD.title));

    }
    @Test
    @SneakyThrows
    void updateProductTitle() {
        product.setTitle("new title");
        Response<Product> response = productService.createProduct(product)
                .execute();
        String title = response.body().getTitle();
        assertThat(title, CoreMatchers.is("new title"));
    }

    @Test
    @SneakyThrows
    void deleteProductInFoodCategoryTest() {
        Response<ResponseBody> response = productService.deleteProduct(product.getId())
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

    }


}
