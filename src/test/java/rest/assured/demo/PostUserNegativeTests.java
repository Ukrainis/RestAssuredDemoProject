package rest.assured.demo;

import com.google.gson.JsonObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rest.assured.demo.pojo.CreateUserRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Test(priority = 1)
public class PostUserNegativeTests extends BaseClass {
    @Test(priority = 1, dataProvider = "userDataProvider")
    public void postUserNegativeTestsUsingJsonObject(String[] userData) {
        JsonObject jsonObject = getUserTestData(userData);

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().post("api/createUserPost")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(400))
                .body("exception", equalTo("InvalidUserDataException"))
                .body("message", equalTo("Incorrect user. Please validate if all mandatory data is filled."));
    }

    private JsonObject getUserTestData(String[] userData) {
        JsonObject jsonObject = new JsonObject();
        for(String data : userData) {
            jsonObject.addProperty(data, data);
        }
        return jsonObject;
    }

    @DataProvider(name = "userDataProvider")
    public Object[][] dataProviderMethod() {
        return new Object[][] {
                { "name" },
                { "name", "userName" },
                { "name", "userName", "email" },
                { "name", "userName", "email", "phone" }};
    }

    @Test
    public void postUserNegativeCaseUsingPojo() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setEmail("some email");
        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(userRequest).when().post("/api/createUserPost")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(400))
        .body("exception", equalTo("InvalidUserDataException"))
        .body("message", equalTo("Incorrect user. Please validate if all mandatory data is filled."));
    }
}
