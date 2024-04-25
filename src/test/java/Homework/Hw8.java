package Homework;

import base_urls.BookerBaseUrl;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Hw8 extends BookerBaseUrl {
/*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */

    @Test
    public void Task1() {
        RequestSpecification spec;
        given().spec(spec)
                .when()
                .get("todos/47900")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", equalTo(47900),
                        "user_id", equalTo(6861183),
                        "title", equalTo("Et minus libero aegrotatio teres quia."),
                        "due_on", equalTo("2024-04-25T00:00:00.000+05:30"),
                        "status", equalTo("pending"))
                .and()
                .extract().response().prettyPeek();  // Uses prettyPeek() to print the response in a formatted way
    }

}