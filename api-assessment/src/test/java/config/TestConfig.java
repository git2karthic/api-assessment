package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public class TestConfig {
    public static RequestSpecification xapi_requestSpecification;
    public static ResponseSpecification xapi_responseSpecification;

    @BeforeAll
    public void Setup() {

        xapi_requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.spacexdata.com")
                .setBasePath("/v4/launches/")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        xapi_responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();


        RestAssured.requestSpecification = xapi_requestSpecification;
        RestAssured.responseSpecification = xapi_responseSpecification;


    }

}
