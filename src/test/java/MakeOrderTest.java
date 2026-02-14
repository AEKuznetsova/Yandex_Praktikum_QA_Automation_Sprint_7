import Models.Order;
import POJOs.Order.OrderPojo;
import POJOs.Order.OrderFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MakeOrderTest {
    private Order order;
    int orderTrack;
    private static final String ROOT_URL = "https://qa-scooter.praktikum-services.ru";
    public MakeOrderTest(Order order) {
        this.order = order;
    }
    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {OrderFactory.makeOrderWithBlackColor()},
                {OrderFactory.makeOrderWithGreyColor()},
                {OrderFactory.makeOrderWithTwoColors()},
                {OrderFactory.makeOrderWithoutColor()}
        };
    }
    @Before
    public void setUp(){
        RestAssured.baseURI = ROOT_URL;
    }
    OrderPojo orderPojo = new OrderPojo();
    @Test
    @DisplayName("Заказ успешно создается, код ответа 201, тело ответа содержит track")
    public void checkCreateOrder() {
        Response response = orderPojo.create(order);
        orderTrack = response.body().path("track");
        assertEquals("Код не соотвествует 201", 201, response.statusCode());
        assertNotNull(response.body().path("track"));
    }
    @After
    public void tearDown(){
        orderPojo.cancelOrder(orderTrack);
    }

}
