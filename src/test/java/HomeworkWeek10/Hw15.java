package HomeworkWeek10;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Hw15 {

    //Write an automation test that will create a 'user' then read, update and delete the created user using the "https://documenter.getpostman.com/view/4012288/TzK2bEa8" document.
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://gorest.co.in/public/v1";
    }

    @Test
    public void testCreateReadUpdateDeleteUser() {
        // User Data
        String userData = "{ \"name\": \"John Doe\", \"gender\": \"male\", \"email\": \"john.doe" + Math.random() + "@example.com\", \"status\": \"active\" }";

        // Create User
        String userId = given()
                .contentType(ContentType.JSON)
                .body(userData)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("data.name", equalTo("John Doe"))
                .extract().jsonPath().getString("data.id");

        // Read User
        given()
                .pathParam("userId", userId)
                .when()
                .get("/users/{userId}")
                .then()
                .statusCode(200)
                .body("data.name", equalTo("John Doe"));

        // Update User
        String updatedUserData = "{ \"name\": \"John Updated\", \"email\": \"john.updated" + Math.random() + "@example.com\", \"status\": \"active\" }";
        given()
                .contentType(ContentType.JSON)
                .body(updatedUserData)
                .pathParam("userId", userId)
                .when()
                .put("/users/{userId}")
                .then()
                .statusCode(200)
                .body("data.name", equalTo("John Updated"));

        // Delete User
        given()
                .pathParam("userId", userId)
                .when()
                .delete("/users/{userId}")
                .then()
                .statusCode(204);

        // Verify Deletion
        given()
                .pathParam("userId", userId)
                .when()
                .get("/users/{userId}")
                .then()
                .statusCode(404);
    }
}
