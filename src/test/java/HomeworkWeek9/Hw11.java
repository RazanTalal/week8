package HomeworkWeek9;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class Hw11 {

    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12

    Note: Print using JsonPath: response.jsonPath().prettyPrint();

*/
    @Test
    public void HW11() {

        Response response = RestAssured.get("https://automationexercise.com/api/productsList");

        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();

        assertEquals(response.statusCode(), 200);

        List<String> WomensProducts = jsonPath.getList("products.findAll{it.category.usertype.usertype=='Women'}.name");
        System.out.println("Number for Women's = " + WomensProducts);
        assertEquals(WomensProducts.size(), 12);
        System.out.println("Women's Products = " + WomensProducts.size());
    }
}