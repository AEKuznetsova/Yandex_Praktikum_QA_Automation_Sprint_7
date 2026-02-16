package pojos.order;

import models.Order;
import utils.Client;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class OrderPojo extends Client {
    private static final String ORDER_URL = "api/v1/orders";

    @Step("Создание заказа")
    public Response create(Order order) {
        return given()
                .spec(getSpecification())
                .and()
                .body(order)
                .when()
                .post(ORDER_URL);
    }

    @Step("Получение списка заказов")
    public Response getOrdersList() {
        return given()
                .spec(getSpecification())
                .when()
                .get(ORDER_URL);
    }

    @Step("Отмена заказа")
    public Response cancelOrder(int trackId){
        return given()
                .spec(getSpecification())
                .and()
                .queryParam("track", trackId)
                .when()
                .put(ORDER_URL + "/cancel");
    }
}
