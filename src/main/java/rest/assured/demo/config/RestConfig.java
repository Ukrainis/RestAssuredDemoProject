package rest.assured.demo.config;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class RestConfig {

    public RestAssuredConfig getDefaultConfig() {
        return RestAssured.config()
                .encoderConfig(encoderConfig()
                        .defaultCharsetForContentType("UTF-8", ContentType.XML)
                        .defaultCharsetForContentType("UTF-8", ContentType.JSON));
    }
}
