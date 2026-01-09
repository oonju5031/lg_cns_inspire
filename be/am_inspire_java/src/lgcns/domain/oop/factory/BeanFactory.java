package lgcns.domain.oop.factory;

import lgcns.domain.oop.LgTV;
import lgcns.domain.oop.SamsungTV;
import lgcns.domain.oop.util.Tv;

public class BeanFactory {
    private static BeanFactory instance;
    private Tv samsung;
    private Tv lg;

    private BeanFactory() {
        samsung = SamsungTV.getInstance();
        lg = LgTV.getInstance();
    }

    public static BeanFactory getInstance() {
        if (instance == null) {
            instance = new BeanFactory();
        }
        return instance;
    }

    public Tv getBrand(String brand) {
        if (brand.equalsIgnoreCase("lg")) {
            return lg;
        } else {
            return samsung;
        }
    }
}
