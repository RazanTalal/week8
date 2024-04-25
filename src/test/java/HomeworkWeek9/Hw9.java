package HomeworkWeek9;

import base_urls.BookerBaseUrl;
import base_urls.PetStoreBaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Hw9 extends PetStoreBaseTest {
    /*
   Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
   */
    @Test
    public void HW9(){
        //Set the url
        spec.pathParams("first","booking");


        Map<String, String > expectedData  = new HashMap<>();//To prepare the test data here is not recommended. We will prepare a test data method for this.
        expectedData.put("id", 001);
        expectedData.put("username", "RazanAw");
        expectedData.put("firstName", "Razan");
        expectedData.put("lastName", "Talal");
        expectedData.put("email", "Razan@hotmail.com");
        expectedData.put("password", "razan1234");
        expectedData.put("phone", "050694483");
        expectedData.put("userStatus", 1);

        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();


    }

}
