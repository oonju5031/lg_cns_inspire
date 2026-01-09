package lgcns.domain.oop;

import lgcns.domain.oop.util.Tv;

public class SamsungTV implements Tv {

    private static final SamsungTV instance = new SamsungTV();
    private SamsungTV() {

    }
    public static SamsungTV getInstance() {
        return instance;
    }

    @Override
    public void powerOn() {
        System.out.println("SamsungTV powerOn()");
    }

    @Override
    public void powerOff() {
        System.out.println("SamsungTV powerOff()");
    }
    
}
