package HomeworkWeek10;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Hw16 {
    //Write an automation test that will add a 'contact' then read, update and delete the created contact then negative assert the deleted contact using the "https://documenter.getpostman.com/view/4012288/TzK2bEa8" document.

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://gorest.co.in/public/v1";
        RestAssured.authentication = RestAssured.oauth2("YOUR_ACCESS_TOKEN_HERE");
    }

    @Test
    public void testContactLifecycle() {
        // Create a new Contact
        String contactData = "{ \"name\": \"Jane Doe\", \"email\": \"jane.doe" + Math.random() + "@example.com\", \"gender\": \"female\", \"status\": \"active\" }";
        String contactId = given()
                .contentType(ContentType.JSON)
                .body(contactData)
                .when()
                .post("/contacts")
                .then()
                .statusCode(201)
                .body("data.name", equalTo("Jane Doe"))
                .extract().jsonPath().getString("data.id");

        // Read the Contact
        given()
                .pathParam("contactId", contactId)
                .when()
                .get("/contacts/{contactId}")
                .then()
                .statusCode(200)
                .body("data.name", equalTo("Jane Doe"));

        // Update the Contact
        String updatedContactData = "{ \"name\": \"Jane Updated\", \"email\": \"jane.updated" + Math.random() + "@example.com\", \"gender\": \"female\", \"status\": \"active\" }";
        given()
                .contentType(ContentType.JSON)
                .body(updatedContactData)
                .pathParam("contactId", contactId)
                .when()
                .put("/contacts/{contactId}")
                .then()
                .statusCode(200)
                .body("data.name", equalTo("Jane Updated"));

        // Delete the Contact
        given()
                .pathParam("contactId", contactId)
                .when()
                .delete("/contacts/{contactId}")
                .then()
                .statusCode(204);

        // Verify Deletion with Negative Assertion
        given()
                .pathParam("contactId", contactId)
                .when()
                .get("/contacts/{contactId}")
                .then()
                .statusCode(404);
    }
}
