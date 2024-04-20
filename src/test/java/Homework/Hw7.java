package Homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Hw7 {
    /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */
    @Test
    public void HW6() {
//        https://reqres.in/api/unknown/

//        I send GET Request to the URL
        Response response = RestAssured.get("https://reqres.in/api/unknown/");//get() method will return Response
        response.prettyPrint();

//        1)Status code is 200
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

//        2)Print all pantone_values

//        3)Print all ids greater than 3 on the console
//        Assert that there are 3 ids greater than 3
//        4)Print all names whose ids are less than 3 on the console
//        Assert that the number of names whose ids are less than 3 is 2
    }
}
