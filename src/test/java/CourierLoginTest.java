import models.Courier;
import models.CourierData;
import pojos.courier.CourierPojo;
import io.restassured.response.Response;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import static pojos.courier.CourierFactory.makeCourier;
import static utils.Utils.randomString;
import static org.junit.Assert.*;


public class CourierLoginTest {
    CourierPojo courierPojo = new CourierPojo();
    private String id;

    @Test
    @DisplayName("Курьер может авторизоваться, успешный запрос возвращает его id")
    public void loginWithCorrectData() {
        Courier courier = makeCourier();
        Response createResponse = courierPojo.create(courier);
        Response loginResponse = courierPojo.login(CourierData.getData(courier));
        id = loginResponse.body().path("id").toString();
        assertEquals("Курьер не залогинен", 200, loginResponse.statusCode());
        assertNotNull(loginResponse.body().path("id"));
    }

    @Test
    @DisplayName("Если нет поля login, то запрос возвращает ошибку ")
    public void loginWithoutLoginData() {
        Courier courier = makeCourier();
        Response createResponse = courierPojo.create(courier);
        setCourierId(courier);
        Response loginResponse = courierPojo.login(CourierData.customLogin(courier, null));
        assertEquals("Неправильный код ответа", 400, loginResponse.statusCode());
        assertEquals("Недостаточно данных для входа", loginResponse.body().path("message"));
    }
    @Test
    @DisplayName("Если нет поля password, то запрос возвращает ошибку ")
    public void loginWithoutPasswordData() {
        Courier courier = makeCourier();
        Response createResponse = courierPojo.create(courier);
        setCourierId(courier);
        Response loginResponse = courierPojo.login(CourierData.customPassword(courier, ""));
        assertEquals("Неправильный код ответа", 400, loginResponse.statusCode());
        assertEquals("Недостаточно данных для входа", loginResponse.body().path("message"));
    }

    @Test
    @DisplayName("Система выдает ошибку, если неправильно указать логин")
    public void loginWithWrongLogin(){
        Courier courier = makeCourier();
        Response createResponse = courierPojo.create(courier);
        setCourierId(courier);
        Response loginResponse = courierPojo.login(CourierData.customLogin(courier, randomString(10)));
        assertEquals("Неправильный код ответа", 404, loginResponse.statusCode());
        assertEquals("Учетная запись не найдена", loginResponse.body().path("message"));
    }

    @Test
    @DisplayName("Система выдает ошибку, если неправильно указать пароль")
    public void loginWithWrongPassword(){
        Courier courier = makeCourier();
        Response createResponse = courierPojo.create(courier);
        setCourierId(courier);
        Response loginResponse = courierPojo.login(CourierData.customPassword(courier, randomString(10)));
        assertEquals("Неправильный код ответа", 404, loginResponse.statusCode());
        assertEquals("Учетная запись не найдена", loginResponse.body().path("message"));
    }

    @After
    public void tearDown(){
        courierPojo.delete(id);
    }

    private void setCourierId(Courier courier) {
        Response loginResponse = courierPojo.login(CourierData.getData(courier));
        id = loginResponse.body().path("id").toString();
    }
}

