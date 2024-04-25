package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class PetStoreBaseTest {
    protected RequestSpecification spec;

    @BeforeMethod
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io")
                .setContentType("application/json")
                .build();
    }
}
