import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AccountTests {
    String url = "https://api.spoonacular.com";

    @Test
    void getOffsetTest() {
        given()
                .param("apiKey", "16de4eb422ec45b2982c57ad273351e1")
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
                .param("apiKey", "16de4eb422ec45b2982c57ad273351e1")
                .formParam("title", "Pork roast with green beans")
                .formParam("ingredientList", "3 oz pork shoulder")
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
                .param("apiKey", "16de4eb422ec45b2982c57ad273351e1")
                .expect()
                .body("totalResults", is(1))
                .body("results[0].id", is(664678))
                .body("results[0].title", is("Vegetarian Moussaka With Portabella"))
                .when()
                .get(url + "/recipes/complexSearch?query=Vegetarian Moussaka With Portabella&cuisine=greek")
                .then()
                .statusCode(200);
    }
}



