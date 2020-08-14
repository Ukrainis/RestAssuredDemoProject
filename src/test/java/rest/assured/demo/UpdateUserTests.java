package rest.assured.demo;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Test(priority = 2)
public class UpdateUserTests extends BaseClass {
    @Test(priority = 1)
    public void addCompanyToUserJSONSuccess() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("bs", "Professional services company");
        jsonObject.addProperty("catchPhrase", "High performance Delivered");
        jsonObject.addProperty("name", "Accenture");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().put("api/user/oleg.mykulskyi/company")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(204));
    }

    @Test(priority = 2)
    public void addCompanyToUserJSONegative() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("bs", "Professional services company");
        jsonObject.addProperty("catchPhrase", "High performance Delivered");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().put("api/user/oleg.mykulskyi/company")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(400))
                .body("exception", equalTo("InvalidCompanyDataException"))
                .body("message", equalTo("Incorrect company. Please validate if all mandatory data is filled."));
    }

    @Test(priority = 3)
    public void addCompanyToUserXMLSuccess() {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<AddCompanyRequest>" +
                    "<bs>Professional services company XML</bs>" +
                    "<catchPhrase>High performance Delivered XML</catchPhrase>" +
                    "<name>Accenture XML</name>" +
                "</AddCompanyRequest>";

        given().contentType(ContentType.XML).and()
                .body(xmlString).when().put("api/user/oleg.mykulskyi XML/company")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(204));
    }

        @Test(priority = 4)
        public void addUserAddressJSONSuccess() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("city", "Riga");
            jsonObject.addProperty("street", "Krisjana Barona iela 23");
            jsonObject.addProperty("suite", "55");
            jsonObject.addProperty("zipcode", "LV-1011");

            given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                    .body(jsonObject).when().put("api/user/oleg.mykulskyi/address")
                    .then().spec(responseSpecifications.buildNoContentResponseSpecification(204));
        }

    @Test(priority = 5)
    public void addIncorrectAddressJSON() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("city", "Riga");
        jsonObject.addProperty("street", "Krisjana Barona iela 23");
        jsonObject.addProperty("suite", "55");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().put("api/user/oleg.mykulskyi/address")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(400))
                .body("exception", equalTo("InvalidAddressDataException"))
                .body("message", equalTo("Incorrect address. Please validate if all mandatory data is filled."));
    }

    @Test(priority = 6)
    public void addAddressToNonexistentUserJSON() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("city", "Riga");
        jsonObject.addProperty("street", "Krisjana Barona iela 23");
        jsonObject.addProperty("suite", "55");
        jsonObject.addProperty("zipcode", "LV-1011");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().put("api/user/oleg.mykul/address")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(404))
                .body("exception", equalTo("UserNotFoundException"))
                .body("message", equalTo("User not found. User name: oleg.mykul"));
    }

    @Test(priority = 7)
    public void addUserAddressXMLSuccess() {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<address>" +
                    "<city>Riga</city>" +
                    "<street>Krisjana Barona iela 23</street>" +
                    "<suite>55</suite>" +
                    "<zipcode>LV-1011</zipcode>" +
                "</address>";

        given().contentType(ContentType.XML).and()
                .body(xmlString).when().put("api/user/oleg.mykulskyi XML/address")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(204));
    }

    @Test(priority = 8)
    public void addGeoToUserAddressJSONSuccess() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("lat", "56.952710");
        jsonObject.addProperty("lng", "24.123850");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().put("api/user/oleg.mykulskyi/address/geo")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(204));
    }

    @Test(priority = 9)
    public void addWrongGeoToUserAddressJSON() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("lat", "56.952710");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().put("api/user/oleg.mykulskyi/address/geo")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(400))
                .body("exception", equalTo("InvalidGeoDataException"))
                .body("message", equalTo("Incorrect Geo coordinates. Please validate if all mandatory data is filled."));
    }

    @Test(priority = 10)
    public void addAddressGeoToNonexistentUserJSON() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("lat", "56.952710");
        jsonObject.addProperty("lng", "24.123850");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().put("api/user/oleg.mykul/address/geo")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(404))
                .body("exception", equalTo("UserNotFoundException"))
                .body("message", equalTo("User not found. User name: oleg.mykul"));
    }

    @Test(priority = 11)
    public void addAddressGeoToNonexistentAddressJSON() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("lat", "56.952710");
        jsonObject.addProperty("lng", "24.123850");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().put("api/user/oleg.mykulskyi/address/geo")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(400))
                .body("exception", equalTo("MissingAddressException"))
                .body("message", equalTo("Address for current user: oleg.mykulskyi is not defined."));
    }

    @Test(priority = 12)
    public void addGeoToUserAddressXMLSuccess() {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<address>" +
                    "<lat>56.952710</lat>" +
                    "<lng>24.123850</lng>" +
                "</address>";

        given().contentType(ContentType.XML).and()
                .body(xmlString).when().put("api/user/oleg.mykulskyi XML/address/geo")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(204));
    }


    //To get success result user should have assigned company, address and Geo data
    @Test(priority = 13)
    public void assignTodoSuccess() {

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .when().put("/api/user/oleg.mykulskyi/assignTodo/1")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(204));
    }

    //To get success result user should have assigned company, address and Geo data
    @Test(priority = 14)
    public void assignTodoForXMLUserSuccess() {

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .when().put("/api/user/oleg.mykulskyi XML/assignTodo/2")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(204));
    }

    @Test(priority = 15)
    public void assignTodoWrongUserNegative() {

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .when().put("/api/user/oleg.mykul/assignTodo/10")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(404))
                .body("exception", equalTo("UserNotFoundException"))
                .body("message", equalTo("User not found. User name: oleg.mykul"));
    }

    @Test(priority = 16)
    public void assignTodoIdNotFoundNegative() {

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .when().put("/api/user/oleg.mykulskyi/assignTodo/999")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(400))
                .body("exception", equalTo("TodoNotFoundException"))
                .body("message", equalTo("Todo with this id: 999 not found."));
    }

    @Test(priority = 17)
    public void addStatusForTodoSuccess() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", "In progress");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().put("/api/todo/1")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(204));
    }

    @Test(priority = 18)
    public void addStatusForUnexistingTodoNegative() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", "In progress");

        given().spec(requestSpecifications.buildRequestSpecificationForJsonRequest())
                .body(jsonObject).when().put("/api/todo/999")
                .then().spec(responseSpecifications.buildJsonResponseSpecification(400))
                .body("exception", equalTo("TodoNotFoundException"))
                .body("message", equalTo("Todo with this id: 999 not found."));
    }

    @Test(priority = 19)
    public void addStatusForXMLSuccess() {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<TodoStatusChangeRequest>" +
                "<status>In progress</status>" +
                "</TodoStatusChangeRequest>";

        given().contentType(ContentType.XML).and()
                .body(xmlString).when().put("/api/todo/2")
                .then().spec(responseSpecifications.buildNoContentResponseSpecification(204));
    }

}