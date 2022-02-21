import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AccountTests {
    String url = "https://api.spoonacular.com";
    String apiKey = "16de4eb422ec45b2982c57ad273351e1";

    @Test
    void getOffsetTest() {
        given()
                .param("apiKey", apiKey)
                .expect()
                .body("offset", is(0))
                .when()
                .get(url + "/recipes/complexSearch")
                .then()
                .statusCode(200);

    }

    @Test
    void getCuisineTest() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", apiKey)
                .formParam("title", "Pork roast with green beans")
                .formParam("ingredientList", "3 oz pork shoulder")
                .queryParam("language", "en")
                .expect()
                .body("cuisine", is("Mediterranean"))
                .when()
                .post(url + "/recipes/cuisine")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getCuisineTest2() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", apiKey)
                .formParam("title", "Vegetarian Moussaka With Portabella")
                .formParam("ingredientList", "3 oz pork shoulder")
                .queryParam("language", "en")
                .expect()
                .body("cuisine", is("Mediterranean"))
                .when()
                .post(url + "/recipes/cuisine")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getCuisineTest3() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", apiKey)
                .formParam("title", "Nigerian Snail Stew")
                .formParam("ingredientList", "3 oz pork shoulder")
                .queryParam("language", "en")
                .expect()
                .body("cuisine", is("Mediterranean"))
                .when()
                .post(url + "/recipes/cuisine")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getCuisineTest4() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", apiKey)
                .formParam("title", "Red Kidney Bean Jambalaya")
                .formParam("ingredientList", "3 oz pork shoulder")
                .queryParam("language", "en")
                .expect()
                .body("cuisine", is("Creole"))
                .when()
                .post(url + "/recipes/cuisine")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getCuisineTest5() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", apiKey)
                .formParam("title", "Slow Cooker Beef Stew")
                .formParam("ingredientList", "3 oz pork shoulder")
                .queryParam("language", "en")
                .expect()
                .body("cuisine", is("Mediterranean"))
                .when()
                .post(url + "/recipes/cuisine")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getSearchRecipesTest() {
        given()
                .param("apiKey", apiKey)
                .expect()
                .body("totalResults", is(1))
                .body("results[0].id", is(664678))
                .body("results[0].title", is("Vegetarian Moussaka With Portabella"))
                .when()
                .get(url + "/recipes/complexSearch?query=Vegetarian Moussaka With Portabella&cuisine=greek")
                .then()
                .statusCode(200);
    }

    @Test
    void getSearchRecipesTest2() {
        given()
                .param("apiKey", apiKey)
                .queryParam("query", "Vegetarian Moussaka With Portabella")
                .queryParam("cuisine", "greek")
                .expect()
                .body("totalResults", is(1))
                .body("results[0].id", is(664678))
                .body("results[0].title", is("Vegetarian Moussaka With Portabella"))
                .when()
                .get(url + "/recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void getSearchRecipesTest3() {
        given()
                .param("apiKey", apiKey)
                .queryParam("query", "Nigerian Snail Stew")
                .expect()
                .body("totalResults", is(1))
                .body("results[0].id", is(716381))
                .body("results[0].title", is("Nigerian Snail Stew"))
                .when()
                .get(url + "/recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void getSearchRecipesTest4() {
        given()
                .param("apiKey", apiKey)
                .queryParam("query", "Red Kidney Bean Jambalaya")
                .expect()
                .body("totalResults", is(1))
                .body("results[0].id", is(782601))
                .body("results[0].title", is("Red Kidney Bean Jambalaya"))
                .when()
                .get(url + "/recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void getSearchRecipesTest5() {
        given()
                .param("apiKey", apiKey)
                .queryParam("query", "Slow Cooker Beef Stew")
                .expect()
                .body("totalResults", is(2))
                .body("results[0].id", is(715446))
                .body("results[0].title", is("Slow Cooker Beef Stew"))
                .body("results[1].id", is(639569))
                .body("results[1].title", is("Classic Beef Stew"))
                .when()
                .get(url + "/recipes/complexSearch")
                .then()
                .statusCode(200);
    }
}



