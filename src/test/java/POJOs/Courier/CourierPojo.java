package POJOs.Courier;

import Models.Courier;
import Models.CourierData;
import Utils.Client;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierPojo extends Client {
    private static final String COURIER_URL = "api/v1/courier";
    private static final String LOGIN_URL = "api/v1/courier/login";
    @Step("Создание курьера")
    public Response create(Courier courier) {
        return given()
                .spec(getSpecification())
                .and()
                .body(courier)
                .when()
                .post(COURIER_URL);
    }

    @Step("Авторизация курьера")
    public Response login(CourierData data) {
        return given()
                .spec(getSpecification())
                .and()
                .body(data)
                .when()
                .post(LOGIN_URL);
    }

    @Step("Удаление курьера")
    public Response delete(String id){
        return given()
                .spec(getSpecification())
                .when()
                .delete(COURIER_URL + "/" + id);
    }
}
