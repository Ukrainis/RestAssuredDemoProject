package rest.assured.demo;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

@Test(priority = 3)
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
                .then().spec(responseSpecifications.build200XmlResponseSpecification(200));
    }

    //To write this test right it should be a method which will get user-Id by user name
    @Test(priority = 3)
    public void findUserByIdSuccess() {
        given()
                .spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .when().get("/api/users/findById/10")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(200));
    }

    @Test(priority = 4)
    public void findUserByIdNegative() {
        given()
                .spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .when().get("/api/users/findById/999")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(404));
    }

    @Test(priority = 5)
    public void findUserByUserNameSuccess() {
        given()
                .spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .when().get("/api/users/findByUserName/oleg.mykulskyi")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(200));
    }

    @Test(priority = 6)
    public void findUserByUserNameNegative() {
        given()
                .spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .when().get("/api/users/findByUserName/oleg.mykul")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(404));
    }

    @Test(priority = 7)
    public void todosSuccess() {
        given()
                .spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .when().get("/api/todos")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(200));
    }
}
