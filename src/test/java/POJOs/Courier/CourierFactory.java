package POJOs.Courier;

import Models.Courier;

import static Utils.Utils.randomString;

public class CourierFactory {
    public static Courier makeCourier() {
        return new Courier()
                .withLogin(randomString(7))
                .withPassword(randomString(4))
                .withFirstName(randomString(6));
    }

    public static Courier makeCourierWithoutLogin() {
        return new Courier()
                .withPassword(randomString(5))
                .withFirstName(randomString(8));
    }

    public static Courier makeCourierWithoutPassword() {
        return new Courier()
                .withLogin(randomString(6))
                .withFirstName(randomString(7));
    }
}
