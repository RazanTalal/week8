package Homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class Hw4 {
    /*
     Given
         https://reqres.in/api/users/2
     When
         User send GET Request to the URL
     Then
         HTTP Status Code should be 200
     And
         Response format should be "application/json"
     And
         "email" is "janet.weaver@reqres.in",
     And
         "first_name" is "Janet"
     And
         "last_name" is "Weaver"
     And
         "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
  */
    public static void main(String[] args) {

//           https://reqres.in/api/users/2

//           User send GET Request to the URL
//        RestAssured library is used to send request and get the response
        Response response = RestAssured.get("https://reqres.in/api/users/2");//get() method will return Response
        response.prettyPrint();//To see body on console in pretty way we can use prettyPrint() method.


//           HTTP Status Code should be 200
//        Everything we need is in Response container
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

//           Response format should be "application/json"


//           "email" is "janet.weaver@reqres.in",

//           "first_name" is "Janet"

//           "last_name" is "Weaver"

//           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"

        response
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name",equalTo("Weaver"))
                .body("support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
}
