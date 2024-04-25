package HomeworkWeek9;

import base_urls.PetStore;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class Hw12  extends PetStore {

    //Write an automation test that will create, read, update, delete a 'pet' using the "https://petstore.swagger.io/" document (All actions must be done on same pet)
    // (Use Pojo)

        @Test(priority = 1)
        public void createPet() {
            given()
                    .contentType("application/json")
                    .body(testPet)
                    .when()
                    .post("/pet")
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(testPet.getName()))
                    .body("status", equalTo(testPet.getStatus()));
        }

        @Test(priority = 2)
        public void readPet() {
            given()
                    .pathParam("petId", testPet.getId())
                    .when()
                    .get("/pet/{petId}")
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(testPet.getName()));
        }

        @Test(priority = 3)
        public void updatePet() {
            testPet.setName("Snowy Updated");
            testPet.setStatus("pending");

            given()
                    .contentType("application/json")
                    .body(testPet)
                    .when()
                    .put("/pet")
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(testPet.getName()))
                    .body("status", equalTo(testPet.getStatus()));
        }

        @Test(priority = 4)
        public void deletePet() {
            given()
                    .pathParam("petId", testPet.getId())
                    .when()
                    .delete("/pet/{petId}")
                    .then()
                    .statusCode(200);
        }
}