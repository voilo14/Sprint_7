package ru.praktikum_servies.qa_scooter.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum_servies.qa_scooter.Client;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client {

    public static final String ORDER_PATH = "/api/v1/orders"; // создание заказа - метод POST * получение списка заказов - метод GET
    public static final String CANCEL_ORDER_PATH = "/api/v1/orders/cancel"; //отмена созданного заказха

    @Step("Creating new order")
    public ValidatableResponse createNewOrder(Order order) {
        return given().log().all()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step("Get order list request ")
    public ValidatableResponse getOrderList() {
        return given().log().all()
                .spec(getSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }

    @Step("Delete existing order by track")
    public ValidatableResponse cancelOrder(int track) {
        return given().log().all()
                .spec(getSpec())
                .body(track)
                .when()
                .put(CANCEL_ORDER_PATH)
                .then();
    }
}
