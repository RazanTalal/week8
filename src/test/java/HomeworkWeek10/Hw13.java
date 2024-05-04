package HomeworkWeek10;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import static org.hamcrest.Matchers.*;


public class Hw13 {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    //Write an automation test that will create a 'user' then read, update and delete the created user using the "https://petstore.swagger.io/" document. (Create a classes for each request.)

    @Test
    public void testCreateReadUpdateDeleteUser() {
        // Create User
        String username = "testuser";
        User user = new User(0, username, "Test", "User", "testuser@example.com", "password123", "1234567890", 0);
        given()
                .contentType("application/json")
                .body(user)
                .when()
                .post("/user")
                .then()
                .statusCode(200);

        // Read User
        given()
                .pathParam("username", username)
                .when()
                .get("/user/{username}")
                .then()
                .statusCode(200)
                .body("username", equalTo(username));

        // Update User
        user.setEmail("updated@example.com");
        given()
                .contentType("application/json")
                .body(user)
                .pathParam("username", username)
                .when()
                .put("/user/{username}")
                .then()
                .statusCode(200)
                .body("email", equalTo("updated@example.com"));

        // Delete User
        given()
                .pathParam("username", username)
                .when()
                .delete("/user/{username}")
                .then()
                .statusCode(200);

        // Verify Deletion
        given()
                .pathParam("username", username)
                .when()
                .get("/user/{username}")
                .then()
                .statusCode(404);
    }

    static class User {
        private int id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String phone;
        private int userStatus;

        public User(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
            this.id = id;
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.phone = phone;
            this.userStatus = userStatus;
        }

        // Getters and Setters
        public void setEmail(String email) {
            this.email = email;
        }
    }
}

