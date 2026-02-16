
import pojos.order.OrderPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class GetListOfOrdersTest {

    private static final String ROOT_URL = "https://qa-scooter.praktikum-services.ru";
    OrderPojo orderPojo = new OrderPojo();
    @Before
    public void setUp(){
        RestAssured.baseURI = ROOT_URL;
    }
    @Test
    public void checkGetListOfOrders() {
        Response response = orderPojo.getOrdersList();
        assertEquals("Код не соотвествует 200", 200, response.statusCode());
        assertNotNull(response.body().path("orders"));
    }
}
