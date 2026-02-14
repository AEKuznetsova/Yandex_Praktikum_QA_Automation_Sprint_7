package POJOs.Order;
import Models.Order;

public class OrderFactory {

    public static Order makeOrderWithGreyColor(){
        return new Order(
                "Иван",
                "Петров",
                "ул. Пушкина, д. 1",
                "Бульвар Рокоссовского",
                "+79998887766",
                "1",
                "2026-02-20",
                "Позвоните мне",
                new String[]{"GREY"}
        );
    }
    public static Order makeOrderWithBlackColor(){
        return new Order(
                "Владимир",
                "Иванов",
                "Льва Толстого, 15",
                "Кутузовская",
                "+79886665544",
                "2",
                "2026-02-20",
                "Жду с нетерпением",
                new String[]{"BLACK"});
    }
    public static Order makeOrderWithTwoColors(){
        return new Order("Александр",
                "Попов",
                "ул. Зеленая, д. 8",
                "Охотный ряд",
                "+79775554433",
                "3",
                "2026-02-20",
                "",
                new String[]{"GREY", "BLACK"});
    }

    public static Order makeOrderWithoutColor(){
        return new Order("Петр",
                "Михайлов",
                "Тургенева, 125/1",
                "Аэропорт",
                "+79443332211",
                "1",
                "2026-02-20",
                "Очень хочу самокат скорее уже",
                null);
    }

}
