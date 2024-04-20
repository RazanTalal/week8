package Homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Hw3 {
     /*
       Given
           https://reqres.in/api/users/3
       When
           User sends a GET Request to the url
       Then
           HTTP Status Code should be 200
       And
           Content Type should be JSON
       And
           Status Line should be HTTP/1.1 200 OK
    */

    public static void main(String[] args) {

//        https://reqres.in/api/users/3

//        User sends a GET Request to the url
//        RestAssured library is used to send request and get the response
        Response response = RestAssured.get("https://reqres.in/api/users/3");//get() method will return Response
        response.prettyPrint();//To see body on console in pretty way we can use prettyPrint() method.

//        HTTP Status Code should be 200
//        Everything we need is in Response container
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

//        Content Type should be JSON
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);

//        Status Line should be HTTP/1.1 200 OK
        String statusLine = response.statusLine();
        System.out.println("statusLine = " + statusLine);

    }
}
