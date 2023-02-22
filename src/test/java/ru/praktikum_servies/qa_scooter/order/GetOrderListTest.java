package ru.praktikum_servies.qa_scooter.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderListTest {

    @Test
    @DisplayName("Get order list")
    @Description("Test for getting successfully response of order list is not empty")
    public void getOrderListIsOk() {
        OrderClient orderClient = new OrderClient();
        ValidatableResponse responseOrderList = orderClient.getOrderList();
        responseOrderList.assertThat()
                .statusCode(200)
                .body("orders", notNullValue());
    }

}
