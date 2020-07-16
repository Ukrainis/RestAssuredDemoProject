package rest.assured.demo.specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecifications {
    private ResponseSpecification responseSpec;
    private ResponseSpecBuilder responseSpecBuilder;

    public ResponseSpecifications() {
        responseSpecBuilder = new ResponseSpecBuilder();
    }

    public ResponseSpecification buildJsonResponseSpecification(int statusCodeExpected) {
        responseSpec = responseSpecBuilder.expectStatusCode(statusCodeExpected)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        return responseSpec;
    }

    public ResponseSpecification build200XmlResponseSpecification(int statusCodeExpected) {
        responseSpec = responseSpecBuilder.expectStatusCode(statusCodeExpected)
                .expectContentType(ContentType.XML)
                .build();
        return responseSpec;
    }
}
