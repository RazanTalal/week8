package HomeworkWeek10;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class Hw14 {
    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "Tatyana Fitzpatrick"
    And
        Total salary of all employees is 6,644,770
 */
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
    }

    @Test
    public void testEmployeeDetails() {
        Response response = given()
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("data", hasSize(24))
                .body("data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"))
                .extract().response();

        // Parse response to extract specific fields
        List<String> names = response.jsonPath().getList("data.employee_name");
        List<Integer> ages = response.jsonPath().getList("data.employee_age");
        List<Integer> salaries = response.jsonPath().getList("data.employee_salary");

        // Find maximum age
        int maxAge = ages.stream().max(Integer::compareTo).orElse(-1);
        assert maxAge == 66 : "Max age is not 66";

        // Find employee with lowest age and their name
        Optional<Integer> minAge = ages.stream().min(Integer::compareTo);
        if (minAge.isPresent()) {
            int index = ages.indexOf(minAge.get());
            String nameOfLowestAge = names.get(index);
            assert "Tatyana Fitzpatrick".equals(nameOfLowestAge) : "Lowest age name is not Tatyana Fitzpatrick";
        }

        // Calculate total salary
        int totalSalary = salaries.stream().mapToInt(Integer::intValue).sum();
        assert totalSalary == 6644770 : "Total salary is not correct";
    }
}
