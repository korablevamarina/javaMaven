import com.lesson4.ComlexSearchResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class AccountTests {
    String url = "https://api.spoonacular.com";
    String apiKey = "16de4eb422ec45b2982c57ad273351e1";

    ResponseSpecification responseSpecification = null;
    // RestAssured.responseSpecification = responseSpecification;

    @BeforeEach
    void beforeTest2() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                //.expectHeader("Access-Control-Allow-Credentials", "true")
                .build();
    }

    @Test
    void getOffsetTest() {
        given()
                .spec(requestSpecification)
                .expect()
                .body("offset", is(0))
                .when()
                .get(url + "/recipes/complexSearch")
                .then()
                .spec(responseSpecification);

    }

    RequestSpecification requestSpecification = null;
    // RestAssured.requestSpecification = requestSpecification;

    @BeforeEach
    void beforeTest() {
        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                //.addQueryParam("includeNutrition", "false")
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    void getCuisineTest() {
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pork roast with green beans")
                .formParam("ingredientList", "3 oz pork shoulder")
                .queryParam("language", "en")
                .expect()
                .body("cuisine", is("Italian"))
                .when()
                .post(url + "/recipes/cuisine")
                .prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getCuisineTest2() {
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Vegetarian Moussaka With Portabella")
                .formParam("ingredientList", "3 oz pork shoulder")
                .queryParam("language", "en")
                .expect()
                .body("cuisine", is("Greek"))
                .when()
                .post(url + "/recipes/cuisine")
                .prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getCuisineTest3() {
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Nigerian Snail Stew")
                .formParam("ingredientList", "3 oz pork shoulder")
                .queryParam("language", "en")
                .expect()
                .body("cuisine", is("Italian"))
                .when()
                .post(url + "/recipes/cuisine")
                .prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getCuisineTest4() {
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Red Kidney Bean Jambalaya")
                .formParam("ingredientList", "3 oz pork shoulder")
                .queryParam("language", "en")
                .expect()
                .body("cuisine", is("Cajun"))
                .when()
                .post(url + "/recipes/cuisine")
                .prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getCuisineTest5() {
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Slow Cooker Beef Stew")
                .formParam("ingredientList", "3 oz pork shoulder")
                .queryParam("language", "en")
                .expect()
                .body("cuisine", is("Italian"))
                .when()
                .post(url + "/recipes/cuisine")
                .prettyPeek()
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getSearchRecipesTest() {
        ComlexSearchResponse response =
        given()
                .spec(requestSpecification)
//                .expect()
//                .body("totalResults", is(1))
//                .body("results[0].id", is(664678))
//                .body("results[0].title", is("Vegetarian Moussaka With Portabella"))
                .when()
                .get(url + "/recipes/complexSearch?query=Vegetarian Moussaka With Portabella&cuisine=greek")
                .then()
//                .spec(responseSpecification)
                .extract()
                .body()
                .as(ComlexSearchResponse.class);

        assertThat(response.getTotalResults(), equalTo(1));
        assertThat(response.getResults().get(0).getId(), equalTo(664678));
        assertThat(response.getResults().get(0) .getTitle(), equalTo("Vegetarian Moussaka With Portabella"));

    }

    @Test
    void getSearchRecipesTest2() {
        ComlexSearchResponse response =
        given()
                .spec(requestSpecification)
                .queryParam("query", "Vegetarian Moussaka With Portabella")
                .queryParam("cuisine", "greek")
               // .expect()
               // .body("totalResults", is(1))
                //.body("results[0].id", is(664678))
                //.body("results[0].title", is("Vegetarian Moussaka With Portabella"))
                .when()
                .get(url + "/recipes/complexSearch")
                .then()
                //.spec(responseSpecification);
                .extract()
                .body()
        .as(ComlexSearchResponse.class);

        assertThat(response.getTotalResults(), equalTo(1));
        assertThat(response.getResults().get(0).getId(), equalTo(664678));
        assertThat(response.getResults().get(0) .getTitle(), equalTo("Vegetarian Moussaka With Portabella"));

    }

    @Test
    void getSearchRecipesTest3() {
        ComlexSearchResponse response =
        given()
                .spec(requestSpecification)
                .queryParam("query", "Nigerian Snail Stew")
                //.expect()
                //.body("totalResults", is(1))
                //.body("results[0].id", is(716381))
                //.body("results[0].title", is("Nigerian Snail Stew"))
                .when()
                .get(url + "/recipes/complexSearch")
                .then()
                //.spec(responseSpecification);
                .extract()
                .body()
                .as(ComlexSearchResponse.class);
        assertThat(response.getTotalResults(), equalTo(1));
        assertThat(response.getResults().get(0).getId(), equalTo(716381));
        assertThat(response.getResults().get(0) .getTitle(), equalTo("Nigerian Snail Stew"));

    }

    @Test
    void getSearchRecipesTest4() {
        ComlexSearchResponse response =
        given()
                .spec(requestSpecification)
                .queryParam("query", "Red Kidney Bean Jambalaya")
               // .expect()
               // .body("totalResults", is(1))
               // .body("results[0].id", is(782601))
               // .body("results[0].title", is("Red Kidney Bean Jambalaya"))
                .when()
                .get(url + "/recipes/complexSearch")
                .then()
               // .spec(responseSpecification);
                .extract()
                .body()
                .as(ComlexSearchResponse.class);

        assertThat(response.getTotalResults(), equalTo(1));
        assertThat(response.getResults().get(0).getId(), equalTo(782601));
        assertThat(response.getResults().get(0) .getTitle(), equalTo("Red Kidney Bean Jambalaya"));

    }

    @Test
    void getSearchRecipesTest5() {
        ComlexSearchResponse response =
        given()
                .spec(requestSpecification)
                .queryParam("query", "Slow Cooker Beef Stew")
                //.expect()
                //.body("totalResults", is(2))
               // .body("results[0].id", is(715446))
                //.body("results[0].title", is("Slow Cooker Beef Stew"))
                //.body("results[1].id", is(639569))
              //  .body("results[1].title", is("Classic Beef Stew"))
                .when()
                .get(url + "/recipes/complexSearch")
                .then()
                //.spec(responseSpecification);
                .extract()
                .body()
                .as(ComlexSearchResponse.class);
        assertThat(response.getTotalResults(), equalTo(2));
        assertThat(response.getResults().get(0).getId(), equalTo(715446));
        assertThat(response.getResults().get(0) .getTitle(), equalTo("Slow Cooker Beef Stew"));

        assertThat(response.getResults().get(1).getId(), equalTo(639569));
        assertThat(response.getResults().get(1) .getTitle(), equalTo("Classic Beef Stew"));

    }

}



