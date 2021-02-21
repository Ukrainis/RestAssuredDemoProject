package rest.assured.demo;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import rest.assured.demo.pojo.CreateUserRequest;
import rest.assured.demo.utils.XmlUtils;

import javax.xml.bind.JAXBException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostUserSuccessTests extends BaseClass {

    @Test(priority = 1)
    public void positivePostUserUsingXml() throws Exception {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("Valentins");
        request.setUserName("UkrainisXml");
        request.setEmail("ukrainis.v@gmail.com");
        request.setPhone("+37126872898");
        request.setWebsite("www.google.com");

        String xmlRequest = XmlUtils.buildCreateUserRequestXmlBody(request);

        given().spec(requestSpecifications.buildRequestSpecificationForXmlRequest())
                .body(xmlRequest).when().post("/api/createUserPost")
                .then().spec(responseSpecifications.buildXmlResponseSpecification(201))
                //.body("CreateUserResponse.id", equalTo("6"))
                .body("CreateUserResponse.userName", equalTo("UkrainisXml"));
    }

    @Test(priority = 2)
    public void positiveDeleteUserTest() {
        given().spec(requestSpecifications.buildRequestSpecificationsForEmptyRequest())
                .when().delete("api/user/UkrainisXml/delete")
                .then()
                .statusCode(204)
                .body(Matchers.blankOrNullString());
    }
}
