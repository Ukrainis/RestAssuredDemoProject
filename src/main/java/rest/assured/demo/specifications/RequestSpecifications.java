package rest.assured.demo.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import rest.assured.demo.config.RestConfig;
import rest.assured.demo.utils.PropertiesUtils;

public class RequestSpecifications {
    private RequestSpecification requestSpec;
    private RestConfig config;

    public RequestSpecifications() {
        config = new RestConfig();
    }

    public RequestSpecification buildRequestSpecificationForJsonRequest() {
        requestSpec = setBaseUrl()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        return requestSpec;
    }

    public RequestSpecification buildRequestSpecificationForXmlRequest() {
        requestSpec = setBaseUrl()
                .setContentType(ContentType.XML)
                .setAccept(ContentType.XML)
                .log(LogDetail.ALL)
                .build();

        return requestSpec;
    }

    public RequestSpecification buildRequestSpecificationsForEmptyRequest() {
        requestSpec = null;
        requestSpec = setBaseUrl()
                .setConfig(config.getDefaultConfig())
                .log(LogDetail.ALL)
                .build();

        return requestSpec;
    }

    private RequestSpecBuilder setBaseUrl() {
        return buildNewRequestSpecBuilder().setBaseUri(PropertiesUtils.getPropertyValueByKey("baseUrl"));
    }

    private RequestSpecBuilder buildNewRequestSpecBuilder() {
        return new RequestSpecBuilder();
    }

}
