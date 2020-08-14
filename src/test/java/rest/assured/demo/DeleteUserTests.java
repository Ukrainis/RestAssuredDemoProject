package rest.assured.demo;

import com.google.gson.JsonObject;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Test(priority = 4)
public class DeleteUserTests extends BaseClass {
    @Test(priority = 1)
    public void deleteUserSuccess() {
        given().spec(requestSpecifications.buildSimpleRequestSpecificationForTxtRequest())
                .when().delete("api/user/oleg.mykulskyi/delete")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(204));
    }

    @Test(priority = 2)
    public void deleteUserWithError() {
        given().spec(requestSpecifications.buildSimpleRequestSpecificationForTxtRequest())
                .when().delete("api/user/oleg.mykul/delete")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(404))
                .body("exception", equalTo("UserNotFoundException"))
                .body("message", equalTo("User not found. User name: oleg.mykul"));
        }

    @Test(priority = 3)
    public void deleteXMLUserSuccess() {
        given().spec(requestSpecifications.buildSimpleRequestSpecificationForTxtRequest())
                .when().delete("api/user/oleg.mykulskyi XML/delete")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(204));
    }
}