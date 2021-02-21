package rest.assured.demo.specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecifications {
    private ResponseSpecification responseSpec;
    private ResponseSpecBuilder responseSpecBuilder;

    public ResponseSpecification buildJsonResponseSpecification(int statusCodeExpected) {
        responseSpec = buildNewResponseBuilder().expectStatusCode(statusCodeExpected)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        return responseSpec;
    }

    public ResponseSpecification buildXmlResponseSpecification(int statusCodeExpected) {
        responseSpec = buildNewResponseBuilder().expectStatusCode(statusCodeExpected)
                .expectContentType(ContentType.XML)
                .log(LogDetail.ALL)
                .build();
        return responseSpec;
    }

    private ResponseSpecBuilder buildNewResponseBuilder() {
        responseSpecBuilder = null;
        responseSpecBuilder = new ResponseSpecBuilder();
        return responseSpecBuilder;
    }
}
