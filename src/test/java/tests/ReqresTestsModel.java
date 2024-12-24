package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.LoginBodyModel;
import models.LoginBodyResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpec.loginRequestSpec;
import static specs.LoginSpec.loginResponseSpec;

public class ReqresTestsModel {

    @Test
    void successfulLoginTestModal () {

        LoginBodyModel loginBodyModel = new LoginBodyModel();
        loginBodyModel.setEmail("eve.holt@reqres.in");
        loginBodyModel.setPassword("cityslicka");

        LoginBodyResponseModel response =
            given()
                    .log().uri()
                    .body(loginBodyModel)
                    .contentType(JSON)
                    .when()
                    .post("https://reqres.in/api/login")
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .extract().as(LoginBodyResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }

    @Test
    void successfulLoginTestModalAllure () {

        LoginBodyModel loginBodyModel = new LoginBodyModel();
        loginBodyModel.setEmail("eve.holt@reqres.in");
        loginBodyModel.setPassword("cityslicka");

        LoginBodyResponseModel response =
                given()
                        .filter(new AllureRestAssured())
                        .log().uri()
                        .body(loginBodyModel)
                        .contentType(JSON)
                        .when()
                        .post("https://reqres.in/api/login")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(LoginBodyResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }

    @Test
    void successfulLoginTestModalAllureSpecs () {

        LoginBodyModel loginBodyModel = new LoginBodyModel();
        loginBodyModel.setEmail("eve.holt@reqres.in");
        loginBodyModel.setPassword("cityslicka");

        LoginBodyResponseModel response =
                given(loginRequestSpec)
                        .body(loginBodyModel)
                        .when()
                        .post()
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginBodyResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }
}
