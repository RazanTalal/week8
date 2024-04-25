package base_urls;

import HomeworkWeek9.Pet;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class PetStore {
    protected Pet testPet;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        testPet = new Pet(123456789, "Snowy", "available");
    }
}
