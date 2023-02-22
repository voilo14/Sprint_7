package ru.praktikum_servies.qa_scooter.courier;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginCourierTest {

    CourierAssertions courierAssertions;
    int idCourier;
    private final CourierGenerator courierGenerator = new CourierGenerator();
    private Credentials credentials;
    private CourierClient courierClient;
    private Courier courier;

    @Before
    @Step("Precondition for login tests with creation courier")
    public void setUp() {
        courierClient = new CourierClient();
        courier = courierGenerator.getCourierRandom();
        courierClient.createCourier(courier);
        credentials = Credentials.from(courier);
        courierAssertions = new CourierAssertions();
    }

    @Test
    @DisplayName("Login courier with valid credentials")
    @Description("Check status code on not empty id")
    public void courierCanSuccessfullyLogin() {
        ValidatableResponse responseLoginCourier = courierClient.loginCourier(credentials);
        courierAssertions.LoginCourierSuccessfully(responseLoginCourier);
        idCourier = responseLoginCourier.extract().path("id");
    }

    @Test
    @DisplayName("Login courier without login field")
    @Description("Try to login courier without login field. Check status code and message.")
    public void courierLoginUnsuccessfullyWithoutLogin() {
        Credentials credentialsWithoutLogin = new Credentials("", courier.getPassword()); // c null тесты виснут
        ValidatableResponse responseLoginErrorMessage = courierClient.loginCourier(credentialsWithoutLogin).statusCode(400);
        responseLoginErrorMessage.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Login courier without password field")
    @Description("Try to login courier without password field. Check status code and message.")
    public void courierLoginUnsuccessfullyWithoutPassword() {
        Credentials credentialsWithoutLogin = new Credentials(courier.getLogin(), "");
        ValidatableResponse responsePasswordErrorMessage = courierClient.loginCourier(credentialsWithoutLogin).statusCode(400);
        responsePasswordErrorMessage.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Login courier without login and password fields")
    @Description("Try to login courier without login and password fields. Check status code and message.")
    public void courierLoginWithoutLoginAndPassword() {
        Credentials credentialsWithoutLoginAndPassword = new Credentials("", "");
        ValidatableResponse responseWithoutLoginAndPasswordMessage = courierClient.loginCourier(credentialsWithoutLoginAndPassword).statusCode(400);
        responseWithoutLoginAndPasswordMessage.assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Login courier with not existing login field")
    @Description("Try to login courier with not existing login field. Check status code and message.")
    public void courierLoginWithNotExistingLogin() {
        Credentials credentialsWithNotExistingLogin = new Credentials(RandomStringUtils.randomAlphanumeric(6), courier.getPassword());
        ValidatableResponse responseWithWithNotExistingLoginMessage = courierClient.loginCourier(credentialsWithNotExistingLogin).statusCode(404);
        responseWithWithNotExistingLoginMessage.assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    @Step("Delete test courier")
    public void deleteCourier() {
        if (idCourier != 0) {
            courierClient.deleteCourier(idCourier);
        }
    }
}
