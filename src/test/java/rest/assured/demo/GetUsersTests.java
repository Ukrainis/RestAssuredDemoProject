package rest.assured.demo;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

@Test(priority = 2)
public class GetUsersTests extends BaseClass {
    @Test(priority = 1)
    public void firstTest() {
        given()
                .spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .when().get("/api/users")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(200));
    }

    @Test(priority = 2)
    public void secondTest() {
        given()
                .spec(requestSpecifications.buildRequestSpecificationForXmlRequest())
                .when().get("/api/users")
                .then().spec(responseSpecifications.buildXmlResponseSpecification(200));
    }
}
