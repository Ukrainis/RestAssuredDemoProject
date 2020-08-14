package rest.assured.demo;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Test(priority = 1)
public class PostUserTests extends BaseClass {
    @Test(priority = 1, dataProvider = "userDataProvider")
    public void postUserNegativeTests(String[] userData) {
        JsonObject jsonObject = getUserTestData(userData);

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
               .body(jsonObject).when().post("api/user")
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

    @Test(priority = 2)
    public void postNewUserJSONSuccess() {
        JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("email", "oleg.mykulskyi@accenture.com");
            jsonObject.addProperty("name", "Oleg Mykulskyi");
            jsonObject.addProperty("phone", "+37120300655");
            jsonObject.addProperty("userName", "oleg.mykulskyi");
            jsonObject.addProperty("website", "https://my.accenture.lv/users/oleg_mykulskyi");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().post("api/user")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(201));
    }

    @Test(priority = 3)
    public void postDuplicatedUserNegative() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", "oleg.mykulskyi@accenture.com");
        jsonObject.addProperty("name", "Oleg Mykulskyi");
        jsonObject.addProperty("phone", "+37120300655");
        jsonObject.addProperty("userName", "oleg.mykulskyi");
        jsonObject.addProperty("website", "https://my.accenture.lv/users/oleg_mykulskyi");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().post("api/user")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(400))
                .body("exception", equalTo("DublicateUserNameException"))
                .body("message", equalTo("User with current username allready exists. Username: oleg.mykulskyi"));
    }

    @Test(priority = 4)
    public void postUserWithMissingPropertiesNegative() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", "amelia.mykulska@accenture.com");
        jsonObject.addProperty("name", "Amelia Mykulska");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().post("api/user")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(400))
                .body("exception", equalTo("InvalidUserDataException"))
                .body("message", equalTo("Incorrect user. Please validate if all mandatory data is filled."));
    }

    @Test(priority = 5)
    public void postNewUserXMLSuccess() {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
        "<newUser>" +
                "<name>Oleg Mykulskyi XML</name>" +
                "<userName>oleg.mykulskyi XML</userName>" +
                "<email>oleg.mykulskyi@accenture.com</email>" +
                "<phone>+37120300655</phone>" +
                "<website>https://my.accenture.lv/users/oleg_mykulskyi</website>" +
        "</newUser>";

        given().contentType(ContentType.XML).and()
                .body(xmlString).when().post("api/user")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(201));
    }

    @Test(priority = 6)
    public void createNewTodoSuccess() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", "TaskJSON");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().post("/api/todo")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(201));
    }

    @Test(priority = 7)
    public void createTodoWithExistingTitleNegative() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", "TaskJSON");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().post("/api/todo")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(400))
                .body("exception", equalTo("DublicatedTodoException"))
                .body("message", equalTo("This todo already exists: In progress"));
    }

    @Test(priority = 8)
    public void createTodoWithWrongFormatNegative() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", "TaskExtra");
        jsonObject.addProperty("status", "In progress");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().post("/api/todo")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(400));
    }

    @Test(priority = 9)
    public void createNewTodoXMLSuccess() {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<Todo>" +
                    "<title>TaskXML</title>" +
                "</Todo>";

        given().spec(requestSpecifications.buildRequestSpecificationForXmlRequest())
                .body(xmlString).when().post("/api/todo")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(201));
    }

}