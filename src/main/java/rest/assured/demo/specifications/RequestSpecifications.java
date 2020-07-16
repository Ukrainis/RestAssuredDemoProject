package rest.assured.demo.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import rest.assured.demo.utils.PropertiesUtils;

public class RequestSpecifications {
    private RequestSpecification requestSpec;
    private RequestSpecBuilder requestSpecBuilder;

    public RequestSpecifications() {
        requestSpecBuilder = new RequestSpecBuilder();
    }

    public RequestSpecification buildRequestSpecificationForJsonRequest() {
        requestSpec = setBaseUrl()
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        return requestSpec;
    }

    public RequestSpecification buildRequestSpecificationForXmlRequest() {
        requestSpec = setBaseUrl()
                .setAccept(ContentType.XML)
                .log(LogDetail.ALL)
                .build();

        return requestSpec;
    }

    private RequestSpecBuilder setBaseUrl() {
        return requestSpecBuilder.setBaseUri(PropertiesUtils.getPropertyValueByKey("baseUrl"))
                    .setPort(Integer.parseInt(PropertiesUtils.getPropertyValueByKey("port")));
    }

}
