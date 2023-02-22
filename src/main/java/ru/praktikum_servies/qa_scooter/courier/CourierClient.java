package ru.praktikum_servies.qa_scooter.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum_servies.qa_scooter.Client;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client {

    public static final String POST_COURIER_LOGIN_PATH = "/api/v1/courier/login"; //логин курьера в системе методом POST
    public static final String DELETE_COURIER_PATH = "/api/v1/courier/"; //для удаления курьера нужно его id (появляется после логина)
    private static final String POST_COURIER_CREATE_PATH = "/api/v1/courier"; // создание курьера методом POST

    @Step("Create new courier in system")
    public ValidatableResponse createCourier(Courier courier) {
        return given().log().all()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(POST_COURIER_CREATE_PATH)
                .then();
    }

    @Step("Login courier in system with login and password")
    public ValidatableResponse loginCourier(Credentials credentials) {
        return given().log().all()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(POST_COURIER_LOGIN_PATH)
                .then();
    }

    @Step("Delete courier by id")
    public ValidatableResponse deleteCourier(int idCourier) {
        return given().log().all()
                .spec(getSpec())
                .when()
                .delete(DELETE_COURIER_PATH + idCourier)
                .then();
    }
}
