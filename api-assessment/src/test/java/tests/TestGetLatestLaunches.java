package tests;

import endpoints.XapiEndPoints;
import io.restassured.RestAssured;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class TestGetLatestLaunches {

    @DisplayName("Test case to validate the json schema")
    @Test
    public void getLatestLaunchesSchemaValidation(){
                             given()
                            .when()
                            .get("https://api.spacexdata.com/v4/launches/latest")
                            .then()
                            .body(matchesJsonSchemaInClasspath("SchemaOriginal.json"));
    }

    @DisplayName("Test case to get the ship information in fairings object")
    @Test
    public void getLatestLaunches_FairingsShips() {
        String jsonResponse = given()
                .when()
                .get("https://api.spacexdata.com/v4/launches/latest").asString();

        List<String> map_fairings = JsonPath.read(jsonResponse, "$.fairings.ships");
        System.out.println(map_fairings);
    }

    @DisplayName("Testcase Get the response code and validate")
    @Test
    public void getLatestLaunches_ResponseCode() {
                given()
                .when()
                .get("https://api.spacexdata.com/v4/launches/latest");


    }

    @DisplayName("Testcase to get the patch information from link object")
    @Test
    public void getLatestLaunches_getPatch() {
        String jsonResponse = given()
                              .when()
                              .get("https://api.spacexdata.com/v4/launches/latest").asString();

        Map<String, Object> map_links = JsonPath.read(jsonResponse, "$.links.patch");
        System.out.println(map_links);
    }

    @DisplayName("Test case to get the id from space api response")
    @Test
    public void getLatestLaunches_getId() {
        String jsonResponse = given()
                .when()
                .get("https://api.spacexdata.com/v4/launches/latest").asString();

        String strId = JsonPath.read(jsonResponse, "$.id");
        System.out.println(strId);
    }


}
