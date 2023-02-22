package ru.praktikum_servies.qa_scooter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Client {

    public static final String BASE_URL = "http://qa-scooter.praktikum-services.ru/"; // URL сайта Самокат

    public RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
}
