import lgcns.domain.oop.factory.BeanFactory;
import lgcns.domain.oop.util.Tv;

public class TvApp {

    public static void main(String[] args) {
        BeanFactory factory = BeanFactory.getInstance();
        Tv tv = factory.getBrand("samsung");

        tv.powerOn();
        tv.powerOff();
    }
    
}
