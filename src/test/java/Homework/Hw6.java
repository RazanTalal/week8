package Homework;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.Matchers.equalTo;

public class Hw6 {
    /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json; charset=utf-8"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */
    @Test
    public void HW6() {

//          https://reqres.in/api/unknown/3

//            User send a GET request to the URL
        Response response = RestAssured.get("https://reqres.in/api/unknown/3");//get() method will return Response
        response.prettyPrint();

//            HTTP Status Code should be 200
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

//            Response content type is "application/json; charset=utf-8"
        response.then().contentType("application/json; charset=utf-8");

//            Response body should be like;(Soft Assertion)
//        {
//        "data": {
//            "id": 3,
//            "name": "true red",
//            "year": 2002,
//            "color": "#BF1932",
//            "pantone_value": "19-1664"
//        },
//        "support": {
//            "url": "https://reqres.in/#support-heading",
//            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
//        }
//}

// 1st way: By extracting data outside the body with JSONPath
        //Convert Response to JsonPath object
        JsonPath jsonPath = response.jsonPath();

        //Retrieve the desired data by using JsonPath object
        int id = jsonPath.getInt("data.id");
        String name = jsonPath.getString("data.name");
        int year = jsonPath.getInt("data.year");
        String color = jsonPath.getString("data.color");
        String pantone_value = jsonPath.getString("data.pantone_value");

        String url = jsonPath.getString("support.url");
        String text = jsonPath.getString("support.text");

        //1st step: Create SoftAssert object
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(id, 3);//If this assertion fails, the subsequent lines will execute as well. Because this is Soft Assertion.
        softAssert.assertEquals(name, "true red");
        softAssert.assertEquals(year, 2002);
        softAssert.assertEquals(color, "#BF1932");
        softAssert.assertEquals(pantone_value, "19-1664");
        softAssert.assertEquals(url, "https://reqres.in/#support-heading");
        softAssert.assertEquals(text, "To keep ReqRes free, contributions towards server costs are appreciated!");

        //3rd step: Use assertAll() method.
        softAssert.assertAll();//In any failure case execution will stop here in soft assertion

        //2nd way: then() method with hamcrest matchers
        response
                .then()
                .body("data.id",equalTo(3))
                .body("data.name",equalTo("true red"))
                .body("data.year",equalTo(2002))
                .body("data.color",equalTo("#BF1932"))
                .body("data.pantone_value",equalTo("19-1664"))

                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }
}
