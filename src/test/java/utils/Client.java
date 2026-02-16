package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Client {
    private static final String ROOT_URL = "http://qa-scooter.praktikum-services.ru";
    protected RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(ROOT_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
