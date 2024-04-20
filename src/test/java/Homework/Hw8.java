package Homework;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Hw8 extends JsonPlaceHolderBaseUrl {
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
    public void HW8() {

        //Set the Url
        spec.pathParams("first", "todos");

        //Set the expected data(Payload) --> Prepare it as Map
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("name","morpheus");
        expectedData.put("job","leader");
        expectedData.put("id",496);
        expectedData.put("createdAt","2022-10-04T15:18:56.372Z");

        System.out.println("expectedData = " + expectedData);


        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");

        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body("name", equalTo(expectedData.get("name")), //By map payload, we can get specific data from body. This is more dynamic.
                        "job", equalTo(expectedData.get("job")));

    }

}