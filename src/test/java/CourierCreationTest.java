import models.Courier;
import models.CourierData;
import pojos.courier.CourierPojo;
import io.restassured.response.Response;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import static pojos.courier.CourierFactory.*;
import static utils.Utils.randomString;
import static org.junit.Assert.*;

public class CourierCreationTest {
    CourierPojo courierPojo = new CourierPojo();
    private String id;

    @Test
    @DisplayName("Можно создать курьера, ответ 201 'ok: true'")
    public void checkCreateCourier(){
        Courier courier = makeCourier();
        Response response = courierPojo.create(courier);
        Response loginResponse = courierPojo.login(CourierData.getData(courier));
        id = loginResponse.path("id").toString();
        assertEquals("Неверный статус код", 201, response.statusCode());
        assertEquals(true, response.body().path("ok"));
        assertEquals("Курьер не залогинен в системе", 200, loginResponse.statusCode());
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров, ответ 409")
    public void checkCannotCreateCourierWithSameLogin(){
        Courier courier = makeCourier();
        Response response = courierPojo.create(courier);
        Response loginResponse = courierPojo.login(CourierData.getData(courier));
        id = loginResponse.path("id").toString();
        Courier sameLoginCourier = new Courier()
                .withLogin(courier.getLogin())
                .withPassword(randomString(8))
                .withFirstName(randomString(8));
        Response finalResponse = courierPojo.create(sameLoginCourier);
        assertEquals("Код не соотвествует 409", 409, finalResponse.statusCode());
        // Текст ответа не соответствует документации ("Этот логин уже используется")
        assertEquals("Этот логин уже используется. Попробуйте другой.", finalResponse.body().path("message"));
    }

    @Test
    @DisplayName("Нельзя создать курьера без логина, ответ 400")
    public void checkCannotCreateCourierWithoutLogin(){
        Courier courier = makeCourierWithoutLogin();
        Response response = courierPojo.create(courier);
        assertEquals("Код не соотвествует ожидаемому 400", 400, response.statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", response.body().path("message"));
    }

    @Test
    @DisplayName("Нельзя создать курьера без пароля, ответ 400")
    public void checkCannotCreateCourierWithoutPassword(){
        Courier courier = makeCourierWithoutPassword();
        Response response = courierPojo.create(courier);
        assertEquals("Код не соотвествует ожидаемому 400", 400, response.statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", response.body().path("message"));
    }

    @After
    public void tearDown(){
        courierPojo.delete(id);
    }
}