package HomeworkWeek9;

import base_urls.PetStoreBaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;

import static io.restassured.path.json.JsonPath.given;
import static org.testng.Assert.assertTrue;

public class Hw10 extends PetStoreBaseTest {


    //Using the https://petstore.swagger.io/ document, write an automation test that finds the number of "pets" with the status "available" and asserts that there are more than 100.

    @Test
    public void HW10(){

        Response response = given((InputStream) spec).get("https://petstore.swagger.io/v2");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        List<String> availablePets = jsonPath.getList("name");
        System.out.println("Available pets = " + availablePets.size());
        System.out.println("Available pets = " + availablePets.size());

        assertTrue(availablePets.size() >100 ,"more than 100 available pets: " + availablePets.size());

    }
}
