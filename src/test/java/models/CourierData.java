package models;

public class CourierData {
    private String login;
    private String password;

    public CourierData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierData getData(Courier courier){
        return new CourierData(courier.getLogin(), courier.getPassword());

    }
    public static CourierData customLogin(Courier courier, String login){
        return new CourierData(login, courier.getPassword());

    }
    public static CourierData customPassword(Courier courier, String password){
        return new CourierData(courier.getLogin(), password);

    }
}
